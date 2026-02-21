package ir.msob.jima.core.it.childdomain;

import ir.msob.jima.core.commons.childdomain.BaseChildDomainAbstract;
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
public abstract class ProjectChildDomainAbstract extends BaseChildDomainAbstract<String> implements ProjectChildDomain {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -6601527731070113825L;

    public ProjectChildDomainAbstract(String id, String parentId) {
        super(id, parentId);
    }

    public enum FN {
        id
    }
}
