package ir.msob.jima.core.commons.domain;

import ir.msob.jima.core.commons.shared.annotation.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a Data Transfer Object (DTO) within a microservice architecture.
 * <p>
 * This annotation is retained at runtime and can only be applied to classes or interfaces.
 * It provides metadata about the DTO, including the microservice name and version.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DtoInfo {
    ClassAnnotationInfo<DtoInfo> info = new ClassAnnotationInfo<>(DtoInfo.class);

    /**
     * Returns the name of the microservice that owns this DTO.
     *
     * @return the microservice name as a String
     */
    String serviceName();

    /**
     * Returns the version of the DTO or domain API.
     *
     * @return the version as a String
     */
    String version();
}
