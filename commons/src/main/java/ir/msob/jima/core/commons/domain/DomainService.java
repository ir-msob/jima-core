package ir.msob.jima.core.commons.domain;

import ir.msob.jima.core.commons.shared.annotation.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark classes that represent domain services.
 * It is retained at runtime and can be applied to types only.
 * The annotation has several elements that provide additional information about the domain.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainService {
    ClassAnnotationInfo<DomainService> info = new ClassAnnotationInfo<>(DomainService.class);

    /**
     * This method is used to set the name of the microservice.
     *
     * @return A string that represents the name of the microservice.
     */
    String serviceName();

    /**
     * This method is used to set the version of the domain API.
     *
     * @return A string that represents the version of the domain API.
     */
    String version();

    /**
     * This method is used to set the name of the domain.
     *
     * @return A string that represents the name of the domain.
     */
    String domainName();

    /**
     * This method is used to set the name of the parent domain.
     * It is optional and defaults to an empty string if not provided.
     *
     * @return A string that represents the name of the parent domain.
     */
    String parentDomainName() default "";

}