package ir.msob.jima.core.ral.mongo.it.criteria;

import ir.msob.jima.core.commons.criteria.BaseCriteriaAbstract;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

/**
 * Basic class for domains filter
 *
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
public abstract class ProjectCriteriaAbstract extends BaseCriteriaAbstract<ObjectId> implements ProjectCriteria {
    protected ProjectCriteriaAbstract() {
    }
}
