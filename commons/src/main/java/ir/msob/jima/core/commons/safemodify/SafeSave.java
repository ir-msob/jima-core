package ir.msob.jima.core.commons.safemodify;

import ir.msob.jima.core.commons.util.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SafeSave {
    ClassAnnotationInfo<SafeSave> info = new ClassAnnotationInfo<>(SafeSave.class);
}