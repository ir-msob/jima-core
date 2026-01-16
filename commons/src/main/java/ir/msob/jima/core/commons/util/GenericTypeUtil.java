package ir.msob.jima.core.commons.util;

import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import org.springframework.core.GenericTypeResolver;

/**
 * Utility class for resolving type arguments for a given class and generic interface.
 */
public class GenericTypeUtil {
    private GenericTypeUtil() {
    }

    /**
     * Resolves the type arguments of a generic interface for a given class at the specified index.
     *
     * @param clazz      The class for which type arguments should be resolved.
     * @param genericIfc The generic interface or class for which type arguments should be resolved.
     * @param index      The index of the type argument to be resolved.
     * @return The resolved type argument class.
     * @throws CommonRuntimeException if the type argument cannot be detected at the specified index.
     */
    public static Class<?> resolveTypeArguments(Class<?> clazz, Class<?> genericIfc, int index) {
        Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(clazz, genericIfc);
        if (classes.length <= index) {
            throw new CommonRuntimeException("Can not detect generic type {} in class {} at index {}", genericIfc, clazz, index);
        }
        return classes[index];
    }
}
