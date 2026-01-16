package ir.msob.jima.core.commons.safemodify;

import ir.msob.jima.core.commons.util.FieldAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UniqueField {
    /**
     * Information about the field annotation.
     */
    FieldAnnotationInfo<UniqueField> info = new FieldAnnotationInfo<>(UniqueField.class);
}