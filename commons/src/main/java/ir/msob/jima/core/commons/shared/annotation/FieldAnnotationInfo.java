package ir.msob.jima.core.commons.shared.annotation;

import ir.msob.jima.core.commons.util.ReflectionUtil;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code FieldAnnotationInfo} class provides utility methods to work with annotations on fields.
 * It allows checking for the presence of a specific annotation on fields and methods within a class.
 *
 * @param <A> The type of annotation to check for.
 */
public class FieldAnnotationInfo<A extends Annotation> {

    // The type of annotation to check for
    private final Class<A> annotationClass;

    /**
     * Constructs a new {@code FieldAnnotationInfo} instance.
     *
     * @param annotationClass The type of annotation to be checked.
     */
    public FieldAnnotationInfo(Class<A> annotationClass) {
        this.annotationClass = annotationClass;
    }

    /**
     * Retrieves a collection of getter methods that have the specified annotation.
     *
     * @param clazz The class to check for annotated getter methods.
     * @return A collection of tuples containing the getter method and the annotation.
     */
    public Collection<Tuple2<Method, A>> getGetterMethodHasAnnotation(Class<?> clazz) {
        Collection<Tuple2<Method, A>> result = new ArrayList<>();
        // Iterate through all declared fields of the current class
        for (var field : ReflectionUtil.getFields(clazz)) {
            // Check if the field is annotated with the specified annotation
            if (field.isAnnotationPresent(annotationClass)) {
                result.add(Tuples.of(ReflectionUtil.getGetter(clazz, field), field.getAnnotation(annotationClass)));
            }
        }
        return result;
    }

    /**
     * Retrieves a collection of fields that have the specified annotation.
     *
     * @param clazz The class to check for annotated fields.
     * @return A collection of tuples containing the field and the annotation.
     */
    public Collection<Tuple2<Field, A>> getFieldsHasAnnotation(Class<?> clazz) {
        Collection<Tuple2<Field, A>> result = new ArrayList<>();
        // Iterate through all declared fields of the current class
        for (var field : ReflectionUtil.getFields(clazz)) {
            // Check if the field is annotated with the specified annotation
            if (field.isAnnotationPresent(annotationClass)) {
                result.add(Tuples.of(field, field.getAnnotation(annotationClass)));
            }
        }
        return result;
    }

    /**
     * Retrieves the first field that has the specified annotation.
     *
     * @param clazz The class to check for annotated fields.
     * @return The first field that has the specified annotation, or null if none found.
     */
    public Field getFirstFieldHasAnnotation(Class<?> clazz) {
        // Iterate through all declared fields of the current class
        for (var field : ReflectionUtil.getFields(clazz)) {
            // Check if the field is annotated with the specified annotation
            if (field.isAnnotationPresent(annotationClass)) {
                return field;
            }
        }
        return null;
    }

    /**
     * Retrieves the first instance of the specified annotation on a field.
     *
     * @param clazz The class to check for annotated fields.
     * @return The first instance of the specified annotation, or null if none found.
     */
    public A getFirstAnnotation(Class<?> clazz) {
        // Iterate through all declared fields of the current class
        Field field = getFirstFieldHasAnnotation(clazz);
        if (field != null) {
            return field.getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     * Checks if any field in the class has the specified annotation.
     *
     * @param clazz The class to check for annotated fields.
     * @return {@code true} if any field has the specified annotation, {@code false} otherwise.
     */
    public boolean hasAnyAnnotation(Class<?> clazz) {
        return getFirstAnnotation(clazz) != null;
    }

}