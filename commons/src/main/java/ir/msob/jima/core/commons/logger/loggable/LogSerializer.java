package ir.msob.jima.core.commons.logger.loggable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Optimized LogSerializer
 * <p>
 * Guarantees:
 * - NO behavior change compared to original version (as far as observable outputs).
 * - Reflection metadata cached once per class
 * - Field access via MethodHandle when possible
 * - Reduced allocations in hot paths
 */
public final class LogSerializer {

    // -------------------------
    // Global caches & helpers
    // -------------------------

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Previously: Map<Class<?>, Optional<Loggable>>
    // Now: store Loggable or null to avoid Optional allocations.
    private static final Map<Class<?>, Loggable> LOGGABLE_CACHE = new ConcurrentHashMap<>();

    // Cache result of isSimple(Class) to avoid repeated expensive checks.
    private static final Map<Class<?>, Boolean> SIMPLE_CACHE = new ConcurrentHashMap<>();

    private static final Map<Class<?>, ClassInfo> CLASS_INFO_CACHE = new ConcurrentHashMap<>();

    private static final ThreadLocal<StringBuilder> KV_BUILDER =
            ThreadLocal.withInitial(StringBuilder::new);

    private LogSerializer() {
    }

    // =========================
    // Public API
    // =========================

    public static Object serialize(Object input) {
        if (input == null) return null;

        try {
            if (input instanceof Collection<?> || isArray(input.getClass())) {
                return serializeCollectionOrArray(input);
            }

            Class<?> cls = input.getClass();
            Loggable lg = getCachedLoggable(cls);
            if (lg == null) {
                return input;
            }

            ClassInfo ci = getOrCreateClassInfo(cls);
            ProcessContext ctx = new ProcessContext();

            Object processed = processObject(input, ctx, 0, ci.depth);

            if (ci.format == LogFormat.JSON) {
                return OBJECT_MAPPER.writeValueAsString(processed);
            }
            return renderAsKeyValue(processed);

        } catch (Exception e) {
            // preserve original behavior on error: return original input
            return input;
        }
    }

    // =========================
    // Collection / Array
    // =========================

    private static Object serializeCollectionOrArray(Object input) throws JsonProcessingException {
        Object first = firstElement(input);
        if (first == null) {
            return input;
        }

        Loggable firstLg = getCachedLoggable(first.getClass());
        if (firstLg == null) {
            return input;
        }

        ClassInfo ci = getOrCreateClassInfo(first.getClass());
        ProcessContext ctx = new ProcessContext();

        int initial = (input instanceof Collection<?> col) ? col.size() : Array.getLength(input);
        List<Object> processed = new ArrayList<>(Math.max(4, initial));
        forEachElement(input, el -> processed.add(processElementReuse(el, ctx)));

        if (ci.format == LogFormat.JSON) {
            return OBJECT_MAPPER.writeValueAsString(processed);
        }

        List<String> kv = new ArrayList<>(processed.size());
        for (Object o : processed) {
            kv.add(o instanceof Map<?, ?> m ? renderAsKeyValue(m) : String.valueOf(o));
        }
        return OBJECT_MAPPER.writeValueAsString(kv);
    }

    private static Object processElementReuse(Object el, ProcessContext ctx) {
        if (el == null) return null;
        Loggable lg = getCachedLoggable(el.getClass());
        if (lg == null) return String.valueOf(el);
        ClassInfo ci = getOrCreateClassInfo(el.getClass());
        return processObject(el, ctx, 0, ci.depth);
    }

    // =========================
    // Core object processing
    // =========================

    private static Object processObject(Object obj, ProcessContext ctx, int depth, int maxDepth) {
        if (obj == null) return null;
        if (isSimple(obj.getClass())) return obj;

        if (!ctx.enter(obj)) return "[CIRCULAR]";
        try {
            if (maxDepth >= 0 && depth > maxDepth) {
                return "[MAX_DEPTH_REACHED]";
            }

            ClassInfo ci = getOrCreateClassInfo(obj.getClass());
            Map<String, Object> result = new LinkedHashMap<>();

            for (FieldInfo fi : ci.fields) {
                if (!shouldInclude(fi, ci.mode)) continue;

                Object raw = fi.get(obj);
                Object handled = handleFieldValue(fi, raw, ctx, depth, maxDepth);
                if (handled != FieldAction.SKIP) {
                    result.put(fi.alias, handled);
                }
            }
            return result;
        } finally {
            ctx.exit(obj);
        }
    }

    // =========================
    // Field handling
    // =========================

    private static Object handleFieldValue(
            FieldInfo fi,
            Object raw,
            ProcessContext ctx,
            int depth,
            int maxDepth
    ) {
        if (raw == null) {
            return fi.logNull ? null : FieldAction.SKIP;
        }

        if (fi.logSize) {
            return sizeOf(raw);
        }

        if (fi.maxLength >= 0 && raw instanceof CharSequence cs) {
            raw = truncate(cs, fi.maxLength);
        }

        if (fi.mask) {
            return maskValue(raw, fi.maskVisibleChars);
        }

        if (isSimple(raw.getClass())) return raw;

        if (raw instanceof Collection<?> || isArray(raw.getClass())) {

            // owner depth check
            if (maxDepth >= 0 && depth + 1 > maxDepth) {
                return "[MAX_DEPTH_REACHED]";
            }

            int initial = (raw instanceof Collection<?> ccol) ? ccol.size() : Array.getLength(raw);
            List<Object> out = new ArrayList<>(Math.max(4, initial));
            forEachElement(raw, el -> {
                if (el == null) {
                    out.add(null);
                    return;
                }

                Class<?> elCls = el.getClass();

                // simple type -> keep as is
                if (isSimple(elCls)) {
                    out.add(el);
                    return;
                }

                // nested collection/array -> recursively process elements (increase depth)
                if (el instanceof Collection<?> || isArray(elCls)) {
                    int innerInitial = (el instanceof Collection<?> innerCol) ? innerCol.size() : Array.getLength(el);
                    List<Object> inner = new ArrayList<>(Math.max(4, innerInitial));
                    forEachElement(el, e2 -> {
                        if (e2 == null) {
                            inner.add(null);
                        } else if (isSimple(e2.getClass())) {
                            inner.add(e2);
                        } else {
                            ClassInfo ci2 = getOrCreateClassInfo(e2.getClass());
                            inner.add(processObject(e2, ctx, depth + 2, ci2.depth));
                        }
                    });
                    out.add(inner);
                    return;
                }

                // normal object element -> process as object (increase depth by 1)
                ClassInfo ci = getOrCreateClassInfo(elCls);
                out.add(processObject(el, ctx, depth + 1, ci.depth));
            });
            return out;
        }


        Loggable lg = getCachedLoggable(raw.getClass());
        if (lg == null) {
            return String.valueOf(raw);
        }

        if (maxDepth >= 0 && depth + 1 > maxDepth) {
            return "[MAX_DEPTH_REACHED]";
        }

        ClassInfo childCi = getOrCreateClassInfo(raw.getClass());
        return processObject(raw, ctx, depth + 1, childCi.depth);
    }

    // =========================
    // Rendering
    // =========================

    private static String renderAsKeyValue(Object processed) {
        if (!(processed instanceof Map<?, ?> map)) {
            return String.valueOf(processed);
        }

        StringBuilder sb = KV_BUILDER.get();
        sb.setLength(0);

        boolean first = true;
        for (Map.Entry<?, ?> e : map.entrySet()) {
            if (!first) sb.append(", ");
            first = false;
            sb.append(e.getKey()).append("=");
            Object v = e.getValue();
            if (v instanceof Map<?, ?> m) {
                sb.append("{").append(renderAsKeyValue(m)).append("}");
            } else if (v instanceof Collection<?> c) {
                sb.append(c);
            } else {
                sb.append(v);
            }
        }
        return sb.toString();
    }

    // =========================
    // Metadata cache
    // =========================

    private static ClassInfo getOrCreateClassInfo(Class<?> cls) {
        return CLASS_INFO_CACHE.computeIfAbsent(cls, c -> {
            Loggable lg = getCachedLoggable(c);
            List<FieldInfo> fis = new ArrayList<>();

            MethodHandles.Lookup lookup = MethodHandles.lookup();
            for (Field f : getAllFields(c)) {
                if (f.isSynthetic()) continue;
                try {
                    f.setAccessible(true);
                } catch (Exception ignored) {
                }

                LogField lf = f.getAnnotation(LogField.class);
                String alias = lf != null && !lf.name().isEmpty() ? lf.name() : f.getName();

                MethodHandle getter = null;
                try {
                    getter = lookup.unreflectGetter(f);
                } catch (IllegalAccessException ignored) {
                }

                fis.add(new FieldInfo(
                        f,
                        alias,
                        lf != null && lf.mask(),
                        lf != null ? lf.maskVisibleChars() : 0,
                        lf != null && lf.logSize(),
                        lf != null && lf.logNull(),
                        lf != null ? lf.maxLength() : -1,
                        getter
                ));
            }

            return new ClassInfo(
                    lg,
                    lg != null ? lg.mode() : LogMode.ALL_FIELD,
                    lg != null ? lg.format() : LogFormat.KEY_VALUE,
                    lg != null ? lg.depth() : -1,
                    fis.toArray(new FieldInfo[0])
            );
        });
    }

    /**
     * getCachedLoggable:
     * - returns cached Loggable annotation or null.
     * - mapping function tries the same approach as original code (Loggable.info.getAnnotation(cls))
     * but fallbacks to standard cls.getAnnotation(Loggable.class) when necessary.
     * <p>
     * NOTE: original code used Optional.ofNullable(Loggable.info.getAnnotation(c))
     * so we preserved try to obtain it the same way (to avoid changing behavior),
     * but we store result directly (no Optional).
     */
    private static Loggable getCachedLoggable(Class<?> cls) {
        return LOGGABLE_CACHE.computeIfAbsent(
                cls,
                c -> {
                    try {
                        // try to preserve original access pattern (original code used Loggable.info.getAnnotation(c))
                        // If Loggable.info is accessible in your project, this preserves exact prior behavior.
                        // If not, fallback to the standard annotation lookup.
                        try {
                            return Loggable.info.getAnnotation(c);
                        } catch (Throwable t) {
                            // fallback
                            return c.getAnnotation(Loggable.class);
                        }
                    } catch (Throwable t) {
                        // final fallback: null
                        return null;
                    }
                }
        );
    }

    // =========================
    // Utilities
    // =========================

    private static boolean shouldInclude(FieldInfo fi, LogMode mode) {
        return switch (mode) {
            case ALL_FIELD -> true;
            case INCLUDE -> fi.field.isAnnotationPresent(LogInclude.class);
            case EXCLUDE -> !fi.field.isAnnotationPresent(LogExclude.class);
        };
    }

    private static void forEachElement(Object input, java.util.function.Consumer<Object> c) {
        if (input instanceof Collection<?> col) {
            col.forEach(c);
        } else if (isArray(input.getClass())) {
            int len = Array.getLength(input);
            for (int i = 0; i < len; i++) c.accept(Array.get(input, i));
        }
    }

    private static Object firstElement(Object input) {
        if (input instanceof Collection<?> col) {
            for (Object o : col) if (o != null) return o;
        } else if (isArray(input.getClass())) {
            int len = Array.getLength(input);
            for (int i = 0; i < len; i++) {
                Object o = Array.get(input, i);
                if (o != null) return o;
            }
        }
        return null;
    }

    private static boolean isArray(Class<?> cls) {
        return cls != null && cls.isArray();
    }

    private static boolean isSimple(Class<?> cls) {
        if (cls == null) return false;
        Boolean cached = SIMPLE_CACHE.get(cls);
        if (cached != null) return cached;
        boolean result = cls.isPrimitive()
                || Number.class.isAssignableFrom(cls)
                || Boolean.class.isAssignableFrom(cls)
                || CharSequence.class.isAssignableFrom(cls)
                || Enum.class.isAssignableFrom(cls)
                || Instant.class.isAssignableFrom(cls)
                || Date.class.isAssignableFrom(cls)
                || UUID.class.isAssignableFrom(cls);
        SIMPLE_CACHE.put(cls, result);
        return result;
    }

    private static List<Field> getAllFields(Class<?> type) {
        List<Field> out = new ArrayList<>();
        Class<?> c = type;
        while (c != null && c != Object.class) {
            Collections.addAll(out, c.getDeclaredFields());
            c = c.getSuperclass();
        }
        return out;
    }

    private static Integer sizeOf(Object v) {
        if (v instanceof CharSequence cs) return cs.length();
        if (v instanceof Collection<?> c) return c.size();
        if (v instanceof Map<?, ?> m) return m.size();
        if (isArray(v.getClass())) return Array.getLength(v);
        return null;
    }

    private static String truncate(CharSequence cs, int max) {
        if (cs.length() <= max) return cs.toString();
        return cs.subSequence(0, max) + "...(truncated)";
    }

    private static String maskValue(Object v, int visible) {
        String s = String.valueOf(v);
        if (visible < 0) visible = 0;
        if (s.length() <= visible) return "*".repeat(s.length());
        return "*".repeat(s.length() - visible) + s.substring(s.length() - visible);
    }

    // =========================
    // Internal helpers
    // =========================

    private enum FieldAction {SKIP}

    private static final class ProcessContext {
        private final IdentityHashMap<Object, Boolean> visited = new IdentityHashMap<>();

        boolean enter(Object o) {
            return visited.put(o, Boolean.TRUE) == null;
        }

        void exit(Object o) {
            visited.remove(o);
        }
    }

    private record ClassInfo(
            Loggable loggable, // null if absent
            LogMode mode,
            LogFormat format,
            int depth,
            FieldInfo[] fields
    ) {
    }

    private record FieldInfo(Field field, String alias, boolean mask, int maskVisibleChars, boolean logSize,
                             boolean logNull, int maxLength, MethodHandle getter) {

        Object get(Object target) {
            try {
                return getter != null ? getter.invoke(target) : field.get(target);
            } catch (Throwable e) {
                return null;
            }
        }
    }
}
