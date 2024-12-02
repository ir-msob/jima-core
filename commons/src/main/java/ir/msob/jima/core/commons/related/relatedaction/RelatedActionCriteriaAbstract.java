package ir.msob.jima.core.commons.related.relatedaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.commons.criteria.BaseCriteriaAbstract;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.domain.BaseIdModel;
import ir.msob.jima.core.commons.shared.audit.auditinfo.AuditInfoFilters;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The {@code RelatedActionCriteria} class represents a set of filters for querying related actions.
 * It implements the {@link BaseFilters} interface, allowing for flexible filtering of related actions
 * based on various criteria.
 *
 * <p>Fields:</p>
 * - {@code name}: A filter for the name of the related action. It is of type {@link Filter<String>}
 * and can be used to search for actions by their names.
 * - {@code status}: A filter for the status of the related action. It is of type {@link Filter<String>}
 * and allows filtering based on the action's current status.
 * - {@code mandatory}: A filter indicating whether the related action is mandatory. It is of type
 * {@link Filter<Boolean>} and can be used to include or exclude mandatory actions in the results.
 * - {@code validFor}: A filter representing the validity period for the related action. It is of type
 * {@link TimePeriodFilters} and can be used to filter actions based on their validity duration.
 * - {@code auditInfo}: A filter for audit-related information associated with the related action.
 * It is of type {@link AuditInfoFilters} and can be used to filter actions based on audit criteria.
 *
 * <p>Methods:</p>
 * - {@code getName()}: Returns the filter for the name of the related action.
 * - {@code setName(Filter<String> name)}: Sets the filter for the name of the related action.
 * - {@code getStatus()}: Returns the filter for the status of the related action.
 * - {@code setStatus(Filter<String> status)}: Sets the filter for the status of the related action.
 * - {@code getMandatory()}: Returns the filter indicating whether the related action is mandatory.
 * - {@code setMandatory(Filter<Boolean> mandatory)}: Sets the filter indicating whether the related action is mandatory.
 * - {@code getValidFor()}: Returns the filter representing the validity period for the related action.
 * - {@code setValidFor(TimePeriodFilters validFor)}: Sets the filter representing the validity period for the related action.
 * - {@code getAuditInfo()}: Returns the filter for audit-related information associated with the related action.
 * - {@code setAuditInfo(AuditInfoFilters auditInfo)}: Sets the filter for audit-related information associated with the related action.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedActionCriteriaAbstract<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> implements BaseFilters {
    private Filter<String> name;
    private Filter<String> status;
    private Filter<Boolean> mandatory;
    private TimePeriodFilters validFor;
    private AuditInfoFilters auditInfo;

    public <C extends RelatedActionCriteriaAbstract<ID>, DTO extends RelatedActionAbstract<ID>> boolean isMatching(C criteria, DTO dto) {
        if (!super.isMatching((BaseCriteria<ID>) criteria, (BaseIdModel<ID>) dto)) {
            return false;
        }
        if (Filter.isMatching(criteria.getName(), dto.getName())) {
            return false;
        }
        if (Filter.isMatching(criteria.getStatus(), dto.getStatus())) {
            return false;
        }
        if (Filter.isMatching(criteria.getMandatory(), dto.getMandatory())) {
            return false;
        }
        if (TimePeriodFilters.isMatching(criteria.getValidFor(), dto.getValidFor())) {
            return false;
        }
        return !AuditInfoFilters.isMatching(criteria.getAuditInfo(), dto.getAuditInfo());
    }
}