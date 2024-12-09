package ir.msob.jima.core.commons.element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Element} annotation is used to define specific elements within the application
 * that can be associated with various operations. This annotation provides metadata about
 * the element type and the operations that can be performed on it.
 *
 * <p>Properties:</p>
 * <ul>
 *     <li>{@code element}: Specifies the type of element, such as "domain", "relatedParty",
 *     or "relatedAction".</li>
 *     <li>{@code operations}: Specifies the types of operations that can be performed on
 *     the defined element, such as "count", "count-all", "save", or "save-many".
 *     This property is optional and defaults to an empty array.</li>
 * </ul>
 *
 * <p>This annotation is retained at runtime and can be applied to methods to indicate
 * the operations that are allowed for the specified element.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Element {
    /**
     * Specifies the type of element.
     * This value can represent various element types, such as "domain", "relatedParty",
     * or "relatedAction".
     *
     * @return the type of element
     */
    String element();

    /**
     * Specifies the types of operations that can be performed on the defined element.
     * This value can represent various operation types, such as "count", "count-all",
     * "save", or "save-many". If no operations are specified, an empty array is returned.
     *
     * @return the types of operations
     */
    String[] operations() default {};
}