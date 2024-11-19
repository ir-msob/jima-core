package ir.msob.jima.core.commons.shared.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This custom annotation, `@Param`, is used to provide additional information for method parameters.
 * <p>
 * It can be applied to method parameters to specify a descriptive name or value associated
 * with the parameter. This information can be used by tools or frameworks to improve code
 * readability and documentation.
 *
 * @author (your name here)  // Add your name as the author (optional)
 * @since (version of the code where this annotation was introduced)  // Add the version (optional)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Param {

    /**
     * The value associated with the annotated parameter.
     * <p>
     * This value can be used to provide a descriptive name, identifier, or other relevant information
     * for the parameter.
     *
     * @return The string value associated with the parameter.
     */
    String value();
}