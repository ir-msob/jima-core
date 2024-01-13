package ir.msob.jima.core.commons.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.annotation.domain.DomainService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@DomainService(serviceName = "sample-service", version = "v1", domainName = "sample-domain")
public class SampleDomain<ID extends Comparable<ID> & Serializable> extends BaseDomainAbstract<ID> {

}
