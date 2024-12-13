package ir.msob.jima.core.ral.mongo.it.domain;

import ir.msob.jima.core.commons.domain.BaseDomainAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.io.Serial;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
public abstract class ProjectDomainAbstract extends BaseDomainAbstract<ObjectId> implements ProjectDomain {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -6601527731070113825L;

    public ProjectDomainAbstract(ObjectId objectId) {
        super(objectId);
    }

    public enum FN {
        id
    }
}
