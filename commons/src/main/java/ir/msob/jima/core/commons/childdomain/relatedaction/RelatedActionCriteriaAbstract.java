package ir.msob.jima.core.commons.childdomain.relatedaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaName;
import ir.msob.jima.core.commons.filter.BaseFilters;
import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfoFilters;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The {@code RelatedActionCriteria} class represents a set of filters for querying childdomain actions.
 * It implements the {@link BaseFilters} interface, allowing for flexible filtering of childdomain actions
 * based on various criteria.
 *
 * <p>Fields:</p>
 * - {@code name}: A filter for the name of the childdomain action. It is of type {@link Filter<String>}
 * and can be used to search for actions by their names.
 * - {@code status}: A filter for the status of the childdomain action. It is of type {@link Filter<String>}
 * and allows filtering based on the action's current status.
 * - {@code mandatory}: A filter indicating whether the childdomain action is mandatory. It is of type
 * {@link Filter<Boolean>} and can be used to include or exclude mandatory actions in the results.
 * - {@code validFor}: A filter representing the validity period for the childdomain action. It is of type
 * {@link TimePeriodFilters} and can be used to filter actions based on their validity duration.
 * - {@code auditInfo}: A filter for audit-childdomain information associated with the childdomain action.
 * It is of type {@link AuditInfoFilters} and can be used to filter actions based on audit criteria.
 *
 * <p>Methods:</p>
 * - {@code getName()}: Returns the filter for the name of the childdomain action.
 * - {@code setName(Filter<String> name)}: Sets the filter for the name of the childdomain action.
 * - {@code getStatus()}: Returns the filter for the status of the childdomain action.
 * - {@code setStatus(Filter<String> status)}: Sets the filter for the status of the childdomain action.
 * - {@code getMandatory()}: Returns the filter indicating whether the childdomain action is mandatory.
 * - {@code setMandatory(Filter<Boolean> mandatory)}: Sets the filter indicating whether the childdomain action is mandatory.
 * - {@code getValidFor()}: Returns the filter representing the validity period for the childdomain action.
 * - {@code setValidFor(TimePeriodFilters validFor)}: Sets the filter representing the validity period for the childdomain action.
 * - {@code getAuditInfo()}: Returns the filter for audit-childdomain information associated with the childdomain action.
 * - {@code setAuditInfo(AuditInfoFilters auditInfo)}: Sets the filter for audit-childdomain information associated with the childdomain action.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedActionCriteriaAbstract<ID extends Comparable<ID> & Serializable, CD extends RelatedActionAbstract<ID>> extends BaseChildCriteriaAbstract<ID, CD> implements BaseChildCriteriaName<ID, CD>, BaseFilters {
    private Filter<String> name;
    private Filter<String> status;
    private Filter<Boolean> mandatory;
    private TimePeriodFilters validFor;
    private AuditInfoFilters auditInfo;

    @Override
    public boolean isMatching(CD relatedModel) {
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