package ir.msob.jima.core.ral.hr.it.test;

import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.it.criteria.ProjectCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
@NoArgsConstructor
public class TestCriteria extends ProjectCriteriaAbstract {
    @Serial
    private static final long serialVersionUID = -8938843863555450000L;
    private Filter<String> field;
}
