package ir.msob.jima.core.commons.childdomain;

import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteria;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ChildDomain {

    Class<? extends BaseChildDomain<?>> cdClass();

    Class<? extends BaseChildCriteria<?, ?>> ccClass();

}
