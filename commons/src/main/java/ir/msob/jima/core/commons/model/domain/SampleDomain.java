package ir.msob.jima.core.commons.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.annotation.domain.DomainService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'SampleDomain' class is a concrete implementation of the 'BaseDomainAbstract' class.
 * It extends 'BaseDomainAbstract' with a generic type 'ID' that extends 'Comparable' and 'Serializable'.
 * This means that the ID of the domain model can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the domain ID and for the domain ID field name.
 * The class also includes a no-argument constructor.
 * The class is annotated with '@JsonInclude(JsonInclude.Include.NON_NULL)', which means that null fields will not be included in the JSON output.
 * The class is also annotated with '@DomainService', which indicates that it is a domain service with a specified service name, version, and domain name.
 *
 * @param <ID> The type of the ID of the domain model.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@DomainService(serviceName = "sample-service", version = "v1", domainName = "sample-domain")
public class SampleDomain<ID extends Comparable<ID> & Serializable> extends BaseDomainAbstract<ID> {

}