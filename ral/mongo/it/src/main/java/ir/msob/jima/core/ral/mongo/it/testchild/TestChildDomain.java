package ir.msob.jima.core.ral.mongo.it.testchild;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.DomainInfo;
import ir.msob.jima.core.it.childdomain.ProjectChildDomainAbstract;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = TestChildDomain.DOMAIN_NAME)
@DomainInfo(domainName = TestChildDomain.DOMAIN_URI)
public class TestChildDomain extends ProjectChildDomainAbstract {
    @Transient
    public static final String DOMAIN_NAME = "TestChildDomain";
    @Transient
    public static final String DOMAIN_URI = "test-child-domain";

    @NotBlank
    private String domainField;

    public TestChildDomain(String id, String parentId) {
        super(id, parentId);
    }

    public enum FN {
        domainField
    }
}
