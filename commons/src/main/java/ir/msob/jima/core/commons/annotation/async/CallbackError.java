package ir.msob.jima.core.commons.annotation.async;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark methods that need to handle callback errors.
 * It is retained at runtime and can be applied to methods only.
 * The value of the annotation is a string that provides additional information about the error.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallbackError {
    /**
     * This method is used to set the value of the CallbackError annotation.
     * The value is a string that provides additional information about the error.
     *
     * @return A string that represents the value of the CallbackError annotation.
     */
    String value();
}