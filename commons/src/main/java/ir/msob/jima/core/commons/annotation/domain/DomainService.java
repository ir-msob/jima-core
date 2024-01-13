package ir.msob.jima.core.commons.annotation.domain;

import ir.msob.jima.core.commons.annotation.ClassAnnotationInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainService {
    ClassAnnotationInfo<DomainService> info = new ClassAnnotationInfo<>(DomainService.class);

    String serviceName();

    String version();

    String domainName();

    String parentDomainName() default "";

}