package ir.msob.jima.core.commons.operation;

import ir.msob.jima.core.commons.element.Element;
import ir.msob.jima.core.commons.shared.annotation.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code ConditionalOnOperation} annotation is used to conditionally include or exclude
 * a component based on the presence of certain operations. This annotation is particularly
 * useful in scenarios where components should only be active if specific operations are
 * available in the application context.
 *
 * <p>This annotation is retained at runtime and can be applied to types (classes or interfaces).</p>
 *
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code operations}: An array of strings that specifies the types of operations
 *     that can be performed. This attribute is optional and defaults to an empty array.</li>
 *     <li>{@code child}: An array of {@link Element} that defines child elements
 *     that may influence the conditional inclusion or exclusion of the component. This
 *     attribute is also optional and defaults to an empty array.</li>
 *     <li>{@code info}: A {@link ClassAnnotationInfo} object that provides metadata about
 *     the {@code ConditionalOnOperation} annotation itself.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * @ConditionalOnOperation(operations = {"save", "update"})
 * public class MyService {
 *     // Service implementation
 * }
 * }
 * </pre>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConditionalOnOperation {
    /**
     * The 'ClassAnnotationInfo' object for the 'ConditionalOnOperation' annotation.
     * This object holds metadata about the annotation itself.
     *
     * @return The ClassAnnotationInfo for this annotation.
     */
    ClassAnnotationInfo<ConditionalOnOperation> info = new ClassAnnotationInfo<>(ConditionalOnOperation.class);

    /**
     * Specifies the types of operations that can be performed within the defined scope.
     * This value can represent various operation types, such as "count", "count-all",
     * "save", or "save-many". If no operations are specified, an empty array is returned.
     *
     * @return The types of operations.
     */
    String[] operations() default {};

    /**
     * Specifies the child elements that may influence the conditional inclusion or
     * exclusion of the component. This attribute is an alias for the 'value' attribute.
     *
     * @return The child elements.
     */
    Element[] related() default {};
}