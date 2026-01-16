package ir.msob.jima.core.commons.util;

import java.util.Iterator;
import java.util.Objects;

public class Strings {
    private Strings() {
    }

    public static boolean isBlank(final String s) {
        if (s != null && !s.isEmpty()) {
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (!Character.isWhitespace(c)) {
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.isEmpty();
    }

    public static boolean isNotBlank(final String s) {
        return !isBlank(s);
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String join(final Iterable<?> iterable, final char separator) {
        return iterable == null ? null : join(iterable.iterator(), separator);
    }

    public static String join(final Iterator<?> iterator, final char separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return Objects.toString(first, "");
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(first);
                }

                while (iterator.hasNext()) {
                    buf.append(separator);
                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }

                return buf.toString();
            }
        }
    }
}
