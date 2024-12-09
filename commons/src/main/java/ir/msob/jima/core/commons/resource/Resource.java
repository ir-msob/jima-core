package ir.msob.jima.core.commons.resource;

import ir.msob.jima.core.commons.shared.ResourceType;
import ir.msob.jima.core.commons.shared.annotation.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Resource} annotation is used to mark a class as a Resource within the application.
 * It provides essential metadata that defines the characteristics of the resource, including
 * its value and type.
 *
 * <p>This annotation is retained at runtime and can be used for various purposes, such as
 * dependency injection, resource management, and configuration.</p>
 *
 * <p>Properties:</p>
 * <ul>
 *     <li>{@code value}: A required property that represents the unique identifier or name
 *     of the Resource.</li>
 *     <li>{@code type}: A required property that specifies the type of the Resource,
 *     represented by the {@link ResourceType} enumeration.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * @Resource(value = "myResource", type = ResourceType.SOME_TYPE)
 * public class MyResourceClass {
 *     // Class implementation
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Resource {
    /**
     * The 'ClassAnnotationInfo' object for the 'Resource' annotation.
     * This object holds metadata about the annotation itself.
     */
    ClassAnnotationInfo<Resource> info = new ClassAnnotationInfo<>(Resource.class);

    /**
     * This property represents the value of the Resource.
     * It is a required property that must be provided when using the annotation.
     *
     * @return The value of the Resource.
     */
    String value();

    /**
     * This property represents the type of the Resource.
     * It is a required property that must be provided when using the annotation.
     *
     * @return The type of the Resource, as defined by the {@link ResourceType} enumeration.
     */
    ResourceType type();
}