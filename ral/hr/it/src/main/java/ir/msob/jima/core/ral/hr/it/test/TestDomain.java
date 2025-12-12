package ir.msob.jima.core.ral.hr.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.DomainInfo;
import ir.msob.jima.core.it.domain.ProjectDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "test_domain")
@DomainInfo(domainName = TestDomain.DOMAIN_URI)
public class TestDomain implements ProjectDomain {

    @Transient
    public static final String DOMAIN_NAME = "TestDomain";

    @Transient
    public static final String DOMAIN_URI = "test-domain";

    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(name = "domain_field", nullable = false)
    private String domainField;

    public TestDomain(String id) {
        this.id = id;
    }

    public enum FN {
        domainField
    }
}
