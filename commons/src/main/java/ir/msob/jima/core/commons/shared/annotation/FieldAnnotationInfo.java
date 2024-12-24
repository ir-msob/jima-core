package ir.msob.jima.core.commons.shared.annotation;

import ir.msob.jima.core.commons.util.ReflectionUtil;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class FieldAnnotationInfo<A extends Annotation> {

    // The type of annotation to check for
    private final Class<A> annotationClass;

    /**
     * Constructs a new ClassAnnotationInfo instance.
     *
     * @param annotationClass The type of annotation to be checked.
     */
    public FieldAnnotationInfo(Class<A> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public Collection<Tuple2<Method, A>> getGetterMethodHasAnnotation(Class<?> clazz) {
        Collection<Tuple2<Method, A>> result = new ArrayList<>();
        // Iterate through all declared fields of the current class
        for (var field : ReflectionUtil.getFields(clazz)) {
            // Check if the field is annotated with ChildDomain
            if (field.isAnnotationPresent(annotationClass)) {
                result.add(Tuples.of(ReflectionUtil.getGetter(clazz, field), field.getAnnotation(annotationClass)));
            }
        }
        return result;
    }


    public Collection<Tuple2<Field, A>> getFieldsHasAnnotation(Class<?> clazz) {
        Collection<Tuple2<Field, A>> result = new ArrayList<>();
        // Iterate through all declared fields of the current class
        for (var field : ReflectionUtil.getFields(clazz)) {
            // Check if the field is annotated with ChildDomain
            if (field.isAnnotationPresent(annotationClass)) {
                result.add(Tuples.of(field, field.getAnnotation(annotationClass)));
            }
        }
        return result;
    }

    public Field getFirstFieldHasAnnotation(Class<?> clazz) {
        // Iterate through all declared fields of the current class
        for (var field : ReflectionUtil.getFields(clazz)) {
            // Check if the field is annotated with ChildDomain
            if (field.isAnnotationPresent(annotationClass)) {
                return field;
            }
        }
        return null;
    }

    public A getFirstAnnotation(Class<?> clazz) {
        // Iterate through all declared fields of the current class
        Field field = getFirstFieldHasAnnotation(clazz);
        if (field != null) {
            return field.getAnnotation(annotationClass);
        }
        return null;
    }

    public boolean hasAnyAnnotation(Class<?> clazz) {
        return getFirstAnnotation(clazz) != null;
    }

}
