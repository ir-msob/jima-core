package ir.msob.jima.core.ral.mongo.it.testchild;

import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.it.childcriteria.ProjectChildCriteriaAbstract;
import ir.msob.jima.core.it.criteria.ProjectCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
@NoArgsConstructor
public class TestChildCriteria extends ProjectChildCriteriaAbstract {
    @Serial
    private static final long serialVersionUID = -8938843863555450000L;
    private Filter<String> field;
}
