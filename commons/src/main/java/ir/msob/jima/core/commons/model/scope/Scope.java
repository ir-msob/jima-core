package ir.msob.jima.core.commons.model.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark a method as a Scope.
 * It provides a single property: value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Scope {
    /**
     * This property represents the value of the Scope.
     * It is a required property.
     *
     * @return The value of the Scope.
     */
    String value();
}