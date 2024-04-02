package ir.msob.jima.core.commons.operation;

import ir.msob.jima.core.commons.annotation.ClassAnnotationInfo;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code ConditionalOnOperation} annotation is used to conditionally include or exclude a component based on the presence of certain operations.
 * The annotation is retained at runtime and can be applied to types.
 * <p>
 * It includes two attributes: {@code operations} and {@code value}, both of which are arrays of strings.
 * These attributes are aliases for each other, meaning they can be used interchangeably.
 * They default to an empty array of strings.
 * <p>
 * Additionally, the annotation includes an {@code info} attribute of type {@code ClassAnnotationInfo<ConditionalOnOperation>},
 * which is used to retrieve information about the {@code ConditionalOnOperation} annotation.
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