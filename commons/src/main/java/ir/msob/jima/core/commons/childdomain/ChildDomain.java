package ir.msob.jima.core.commons.childdomain;

import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteria;
import ir.msob.jima.core.commons.util.FieldAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field as a child domain.
 * This annotation is retained at runtime and can be applied to fields.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ChildDomain {

    /**
     * Information about the field annotation.
     */
    FieldAnnotationInfo<ChildDomain> info = new FieldAnnotationInfo<>(ChildDomain.class);

    /**
     * Specifies the class of the child domain.
     *
     * @return the class of the child domain
     */
    Class<? extends BaseChildDomain<?>> cdClass();

    /**
     * Specifies the class of the child criteria.
     *
     * @return the class of the child criteria
     */
    Class<? extends BaseChildCriteria<?, ?>> ccClass();

}