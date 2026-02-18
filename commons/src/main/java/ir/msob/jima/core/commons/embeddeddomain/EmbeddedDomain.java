package ir.msob.jima.core.commons.embeddeddomain;

import ir.msob.jima.core.commons.embeddeddomain.criteria.BaseEmbeddedCriteria;
import ir.msob.jima.core.commons.util.FieldAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field as a embedded domain.
 * This annotation is retained at runtime and can be applied to fields.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmbeddedDomain {

    /**
     * Information about the field annotation.
     */
    FieldAnnotationInfo<EmbeddedDomain> info = new FieldAnnotationInfo<>(EmbeddedDomain.class);

    /**
     * Specifies the class of the embedded domain.
     *
     * @return the class of the embedded domain
     */
    Class<? extends BaseEmbeddedDomain<?>> cdClass();

    /**
     * Specifies the class of the embedded criteria.
     *
     * @return the class of the embedded criteria
     */
    Class<? extends BaseEmbeddedCriteria<?, ?>> ccClass();

}