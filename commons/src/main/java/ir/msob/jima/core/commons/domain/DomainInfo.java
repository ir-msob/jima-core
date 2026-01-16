package ir.msob.jima.core.commons.domain;

import ir.msob.jima.core.commons.util.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as representing a domain within a microservice architecture.
 * <p>
 * This annotation is retained at runtime and can only be applied to classes or interfaces.
 * It provides metadata about the domain, including its name and optionally its parent domain name.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainInfo {
    ClassAnnotationInfo<DomainInfo> info = new ClassAnnotationInfo<>(DomainInfo.class);

    /**
     * Returns the name of the domain.
     *
     * @return the domain name as a String
     */
    String domainName();

    /**
     * Returns the name of the parent domain, if any.
     * <p>
     * This is optional and defaults to an empty string if not specified.
     * </p>
     *
     * @return the parent domain name as a String, or an empty string if none
     */
    String parentDomainName() default "";
}
