package ir.msob.jima.core.ral.mongo.it.criteria;

import ir.msob.jima.core.commons.domain.BaseCriteriaAbstract;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Basic class for domains filter
 *
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
public abstract class ProjectCriteriaAbstract extends BaseCriteriaAbstract<String> implements ProjectCriteria {
    protected ProjectCriteriaAbstract() {
    }
}
