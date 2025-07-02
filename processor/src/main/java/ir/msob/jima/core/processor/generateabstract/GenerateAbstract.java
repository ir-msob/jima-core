package ir.msob.jima.core.processor.generateabstract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify an interface that should be converted to an abstract class.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateAbstract {
    /**
     * The abstract class that the interface should be converted to.
     *
     * @return the abstract class
     */
    Class<?> value() default Object.class;
}