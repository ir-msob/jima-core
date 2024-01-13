package ir.msob.jima.core.commons.util;

import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;

import java.util.Set;

/**
 * Utility class for registering classes based on package scanning and class hierarchy.
 * <p>
 * This class provides methods to scan a specified package for classes that are subtypes
 * of a given class and register them for further processing. It is commonly used in
 * scenarios where class registration is required for various purposes.
 */
public class NativeUtil {
    // Logger for logging registration and other messages
    private static final Logger log = LoggerFactory.getLog(NativeUtil.class);

    private NativeUtil() {
        // Private constructor to prevent instantiation; this class is meant to be used statically.
    }

    /**
     * Register classes that are subtypes of a specified class within a package.
     *
     * @param hints         The runtime hints or context to perform the registration.
     * @param packagePrefix The package prefix to scan for subtypes of the specified class.
     * @param clazz         The base class or interface to find subtypes of.
     * @param <T>           The type of the base class or interface.
     */
    public static <T> void registerBySupper(RuntimeHints hints, String packagePrefix, Class<T> clazz) {
        // Initialize a Reflections instance to scan the specified package
        Reflections reflections = new Reflections(packagePrefix);

        // Get all classes that are subtypes of the provided base class or interface
        Set<Class<? extends T>> subClasses = reflections.getSubTypesOf(clazz);

        // Register each subtype
        subClasses.forEach(sub -> register(hints, sub));

        // Register the base class or interface itself
        register(hints, clazz);
    }

    /**
     * Register a specific class for further processing.
     *
     * @param hints The runtime hints or context to perform the registration.
     * @param clazz The class to register.
     */
    @SneakyThrows
    public static void register(RuntimeHints hints, Class<?> clazz) {
        // Log the registration of the class
        log.info("Register class of {}", clazz.toString());

        // Register the class for various member categories using hints
        hints.reflection().registerType(clazz,
                MemberCategory.PUBLIC_FIELDS,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.INTROSPECT_PUBLIC_CONSTRUCTORS,
                MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INTROSPECT_PUBLIC_METHODS,
                MemberCategory.INTROSPECT_DECLARED_METHODS,
                MemberCategory.INVOKE_PUBLIC_METHODS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.PUBLIC_CLASSES,
                MemberCategory.DECLARED_CLASSES
        );
    }
}
