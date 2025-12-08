package ir.msob.jima.core.ral.sql.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.DomainInfo;
import ir.msob.jima.core.it.domain.ProjectDomainAbstract;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table
@DomainInfo(domainName = TestDomain.DOMAIN_URI)
public class TestDomain extends ProjectDomainAbstract {
    @Transient
    public static final String DOMAIN_NAME = "TestDomain";
    @Transient
    public static final String DOMAIN_URI = "test-domain";

    @NotBlank
    private String domainField;

    public TestDomain(String id) {
        super(id);
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public enum FN {
        domainField
    }
}
