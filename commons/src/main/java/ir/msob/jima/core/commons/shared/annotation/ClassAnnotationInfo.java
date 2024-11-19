package ir.msob.jima.core.commons.shared.annotation;

import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;

import java.lang.annotation.Annotation;

/**
 * The {@code ClassAnnotationInfo} class is a utility class for checking the presence or absence of a specific type of annotation on a class.
 * It provides functionality to search for annotations existing on a class, including its parent classes and ancestors.
 * <p>
 * This class is parameterized with the type of annotation to be checked.
 */

public class ClassAnnotationInfo<A extends Annotation> {
    // The type of annotation to check for
    private final Class<A> annotationClass;

    /**
     * Constructs a new ClassAnnotationInfo instance.
     *
     * @param annotationClass The type of annotation to be checked.
     */
    public ClassAnnotationInfo(Class<A> annotationClass) {
        this.annotationClass = annotationClass;
    }

    /**
     * Checks if the specified class has the annotation.
     *
     * @param modelClass The class to be checked for the annotation.
     * @return `true` if the annotation is found; otherwise, `false`.
     */
    public boolean hasAnnotation(Class<?> modelClass) {
        return getAnnotation(modelClass) != null;
    }

    /**
     * Retrieves the annotation from the specified class (or its parent classes and ancestors).
     *
     * @param modelClass The class from which the annotation should be retrieved.
     * @return The annotation if found; otherwise, `null`.
     * @throws CommonRuntimeException if the provided class is `null`.
     */
    public A getAnnotation(Class<?> modelClass) {
        if (modelClass == null)
            throw new CommonRuntimeException("The class should not be null");
        Class<?> type = modelClass;
        while (type != null && type != Object.class) {
            A annotation = type.getDeclaredAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
            type = type.getSuperclass();
        }
        return null;
    }
}