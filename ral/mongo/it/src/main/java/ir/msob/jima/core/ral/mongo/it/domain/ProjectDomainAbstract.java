package ir.msob.jima.core.ral.mongo.it.domain;

import ir.msob.jima.core.commons.domain.BaseDomainAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class ProjectDomainAbstract extends BaseDomainAbstract<String> implements ProjectDomain {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -6601527731070113825L;

    public ProjectDomainAbstract(String id) {
        super(id);
    }

    public enum FN {
        id
    }
}
