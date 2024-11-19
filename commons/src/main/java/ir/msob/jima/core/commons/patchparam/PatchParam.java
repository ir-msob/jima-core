package ir.msob.jima.core.commons.patchparam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The 'PatchParam' annotation is used to annotate method parameters that represent patch operations.
 * It is retained at runtime and can be used on method parameters.
 * The annotation has two elements: 'path' and 'value'.
 * 'path' represents the path of the property to be patched.
 * 'value' represents the new value to be assigned to the property.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PatchParam {
    /**
     * The 'path' element represents the path of the property to be patched.
     *
     * @return The path of the property to be patched.
     */
    String path();

    /**
     * The 'value' element represents the new value to be assigned to the property.
     *
     * @return The new value to be assigned to the property.
     */
    String value();
}