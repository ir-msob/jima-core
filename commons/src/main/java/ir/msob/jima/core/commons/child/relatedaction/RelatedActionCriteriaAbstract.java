package ir.msob.jima.core.commons.child.relatedaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.child.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfoFilters;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The {@code RelatedActionCriteria} class represents a set of filters for querying child actions.
 * It implements the {@link BaseFilters} interface, allowing for flexible filtering of child actions
 * based on various criteria.
 *
 * <p>Fields:</p>
 * - {@code name}: A filter for the name of the child action. It is of type {@link Filter<String>}
 * and can be used to search for actions by their names.
 * - {@code status}: A filter for the status of the child action. It is of type {@link Filter<String>}
 * and allows filtering based on the action's current status.
 * - {@code mandatory}: A filter indicating whether the child action is mandatory. It is of type
 * {@link Filter<Boolean>} and can be used to include or exclude mandatory actions in the results.
 * - {@code validFor}: A filter representing the validity period for the child action. It is of type
 * {@link TimePeriodFilters} and can be used to filter actions based on their validity duration.
 * - {@code auditInfo}: A filter for audit-child information associated with the child action.
 * It is of type {@link AuditInfoFilters} and can be used to filter actions based on audit criteria.
 *
 * <p>Methods:</p>
 * - {@code getName()}: Returns the filter for the name of the child action.
 * - {@code setName(Filter<String> name)}: Sets the filter for the name of the child action.
 * - {@code getStatus()}: Returns the filter for the status of the child action.
 * - {@code setStatus(Filter<String> status)}: Sets the filter for the status of the child action.
 * - {@code getMandatory()}: Returns the filter indicating whether the child action is mandatory.
 * - {@code setMandatory(Filter<Boolean> mandatory)}: Sets the filter indicating whether the child action is mandatory.
 * - {@code getValidFor()}: Returns the filter representing the validity period for the child action.
 * - {@code setValidFor(TimePeriodFilters validFor)}: Sets the filter representing the validity period for the child action.
 * - {@code getAuditInfo()}: Returns the filter for audit-child information associated with the child action.
 * - {@code setAuditInfo(AuditInfoFilters auditInfo)}: Sets the filter for audit-child information associated with the child action.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedActionCriteriaAbstract<ID extends Comparable<ID> & Serializable, RM extends RelatedActionAbstract<ID>> extends BaseChildCriteriaAbstract<ID, RM> implements BaseFilters {
    private Filter<String> name;
    private Filter<String> status;
    private Filter<Boolean> mandatory;
    private TimePeriodFilters validFor;
    private AuditInfoFilters auditInfo;

    @Override
    public boolean isMatching(RM relatedModel) {
        if (!super.isMatching(relatedModel)) {
            return false;
        }
        if (Filter.isMatching(this.getName(), relatedModel.getName())) {
            return false;
        }
        if (Filter.isMatching(this.getStatus(), relatedModel.getStatus())) {
            return false;
        }
        if (Filter.isMatching(this.getMandatory(), relatedModel.getMandatory())) {
            return false;
        }
        if (TimePeriodFilters.isMatching(this.getValidFor(), relatedModel.getValidFor())) {
            return false;
        }
        return !AuditInfoFilters.isMatching(this.getAuditInfo(), relatedModel.getAuditInfo());
    }
}