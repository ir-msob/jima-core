package ir.msob.jima.core.it.childcriteria;

import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.domain.BaseCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public abstract class ProjectChildCriteriaAbstract extends BaseChildCriteriaAbstract<String> implements ProjectChildCriteria {

}
