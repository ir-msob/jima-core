package ir.msob.jima.core.commons.logger;

import ir.msob.jima.core.commons.util.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Loggable {
    ClassAnnotationInfo<Loggable> info = new ClassAnnotationInfo<>(Loggable.class);

    LogMode mode() default LogMode.ALL_FIELD;

    LogFormat format() default LogFormat.KEY_VALUE;

    int depth() default -1; // -1 = unlimited

}
