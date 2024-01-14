package ir.msob.jima.core.commons.model.operation;

import ir.msob.jima.core.commons.annotation.ClassAnnotationInfo;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The 'ConditionalOnOperation' annotation is used to conditionally include or exclude a component based on the presence of certain operations.
 * The annotation is retained at runtime.
 * The annotation can be applied to types.
 * The annotation includes an 'operations' attribute and a 'value' attribute, both of which are arrays of strings.
 * The 'operations' attribute and the 'value' attribute are aliases for each other, which means that they can be used interchangeably.
 * The 'operations' attribute and the 'value' attribute default to an empty array of strings.
 * The annotation also includes an 'info' attribute of type 'ClassAnnotationInfo<ConditionalOnOperation>'.
 * The 'info' attribute is used to retrieve information about the 'ConditionalOnOperation' annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConditionalOnOperation {
    /**
     * The 'ClassAnnotationInfo' object for the 'ConditionalOnOperation' annotation.
     */
    ClassAnnotationInfo<ConditionalOnOperation> info = new ClassAnnotationInfo<>(ConditionalOnOperation.class);

    /**
     * The operations based on which the component is conditionally included or excluded.
     * This attribute is an alias for the 'value' attribute.
     *
     * @return The operations.
     */
    @AliasFor("value")
    String[] operations() default {};

    /**
     * The operations based on which the component is conditionally included or excluded.
     * This attribute is an alias for the 'operations' attribute.
     *
     * @return The operations.
     */
    @AliasFor("operations")
    String[] value() default {};
}