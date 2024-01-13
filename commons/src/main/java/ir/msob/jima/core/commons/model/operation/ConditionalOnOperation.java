package ir.msob.jima.core.commons.model.operation;

import ir.msob.jima.core.commons.annotation.ClassAnnotationInfo;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConditionalOnOperation {
    ClassAnnotationInfo<ConditionalOnOperation> info = new ClassAnnotationInfo<>(ConditionalOnOperation.class);

    @AliasFor("value")
    String[] operations() default {};

    @AliasFor("operations")
    String[] value() default {};
}