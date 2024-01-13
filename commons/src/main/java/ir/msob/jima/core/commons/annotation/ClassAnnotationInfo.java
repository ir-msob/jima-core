package ir.msob.jima.core.commons.annotation;

import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;

import java.lang.annotation.Annotation;

/**
 * This class provides a utility for checking the presence or absence of a specific type of annotation on a class.
 * It allows you to find annotations that exist on a class (including its parent classes and ancestors).
 * Some key concepts of the class include:
 * <p>
 * - `annotationClass`: This field represents the type of annotation you want to check.
 * <p>
 * - `hasAnnotation(Class<?> modelClass)`: This method is used to check the presence of the annotation on a class.
 * If the annotation exists, it returns `true`.
 * <p>
 * - `getAnnotation(Class<?> modelClass)`: This method is used to retrieve the annotation from a class (or its parent classes and ancestors).
 * If the annotation exists, it is returned. If the annotation is not found, it returns `null`.
 * <p>
 * This class is useful when you need to examine the presence or properties of annotations on classes.
 */
public class ClassAnnotationInfo<A extends Annotation> {
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
