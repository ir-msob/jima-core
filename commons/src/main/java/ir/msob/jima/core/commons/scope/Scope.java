package ir.msob.jima.core.commons.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Scope} annotation is used to define specific scopes within the application.
 * It provides metadata about the scope type and the operations that can be performed
 * within that scope.
 *
 * <p>This annotation is particularly useful for marking methods that define the boundaries
 * of operations in a domain-driven design, allowing for better organization and management
 * of application logic.</p>
 *
 * <p>Properties:</p>
 * <ul>
 *     <li>{@code operation}: Specifies the type of operation that can be performed within
 *     the defined scope, such as "count", "count-all", "save", or "save-many". This
 *     property is mandatory.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * @Scope(operation = "save")
 * public void saveEntity(MyEntity entity) {
 *     // Implementation here
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Scope {

    /**
     * Specifies the type of operation that can be performed within the defined scope.
     * This value can represent various operation types, such as "count", "count-all",
     * "save", or "save-many". This property is required.
     *
     * @return the type of operation
     */
    String operation();
}