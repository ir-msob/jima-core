package ir.msob.jima.core.commons.model.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark a method as a Scope Initializer.
 * It provides a single property: value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ScopeInitializer {
    /**
     * This property represents the value of the Scope Initializer.
     * It is a required property.
     *
     * @return The value of the Scope Initializer.
     */
    String value();
}