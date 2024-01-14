package ir.msob.jima.core.commons.annotation.patchparam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The 'PatchParams' annotation is used to annotate method parameters that represent multiple patch operations.
 * It is retained at runtime and can be used on method parameters.
 * The annotation has one element: 'patchParams', which is an array of 'PatchParam' annotations.
 * Each 'PatchParam' annotation represents a single patch operation.
 * By using the 'PatchParams' annotation, you can specify multiple patch operations in a single method parameter.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PatchParams {
    /**
     * The 'patchParams' element is an array of 'PatchParam' annotations.
     * Each 'PatchParam' annotation represents a single patch operation.
     *
     * @return An array of 'PatchParam' annotations.
     */
    PatchParam[] patchParams() default {};
}