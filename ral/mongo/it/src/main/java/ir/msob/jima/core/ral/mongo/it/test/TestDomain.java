package ir.msob.jima.core.ral.mongo.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.DomainInfo;
import ir.msob.jima.core.ral.mongo.it.Microservices;
import ir.msob.jima.core.ral.mongo.it.domain.ProjectDomainAbstract;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = TestDomain.DOMAIN_NAME)
@DomainInfo(serviceName = Microservices.TEST_MICROSERVICE, version = Microservices.VERSION, domainName = TestDomain.DOMAIN_URI)
public class TestDomain extends ProjectDomainAbstract {
    @Transient
    public static final String DOMAIN_NAME = "TestDomain";
    @Transient
    public static final String DOMAIN_URI = "test-domain";

    @NotBlank
    private String domainField;

    public TestDomain(ObjectId objectId) {
        super(objectId);
    }

    public enum FN {
        domainField
    }
}
