package ir.msob.jima.core.commons.model.scope;

import ir.msob.jima.core.commons.model.ResourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark a class as a Resource.
 * It provides two properties: value and type.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Resource {
    /**
     * This property represents the value of the Resource.
     * It is a required property.
     *
     * @return The value of the Resource.
     */
    String value();

    /**
     * This property represents the type of the Resource.
     * It is a required property.
     *
     * @return The type of the Resource.
     */
    ResourceType type();
}