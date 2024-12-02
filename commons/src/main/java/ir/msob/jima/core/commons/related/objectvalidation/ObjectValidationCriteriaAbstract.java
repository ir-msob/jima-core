package ir.msob.jima.core.commons.related.objectvalidation;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.commons.criteria.BaseCriteriaAbstract;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.domain.BaseIdModel;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The {@code ObjectValidationCriteria} class represents a set of filters for object validations.
 * It implements the {@link BaseFilters} interface.
 * <p>
 * Fields:
 * - {@code name}: A filter for the name of the object validation. It is of type {@link Filter<String>}.
 * - {@code status}: A filter for the status of the object validation. It is of type {@link Filter<String>}.
 * - {@code enabled}: A filter indicating whether the object validation is enabled. It is of type {@link Filter<Boolean>}.
 * - {@code validFor}: A filter representing the validity period for the object validation. It is of type {@link TimePeriodFilters}.
 * <p>
 * Methods:
 * - {@code getName()}: Returns the filter for the name of the object validation.
 * - {@code setName(Filter<String> name)}: Sets the filter for the name of the object validation.
 * - {@code getStatus()}: Returns the filter for the status of the object validation.
 * - {@code setStatus(Filter<String> status)}: Sets the filter for the status of the object validation.
 * - {@code getEnabled()}: Returns the filter indicating whether the object validation is enabled.
 * - {@code setEnabled(Filter<Boolean> enabled)}: Sets the filter indicating whether the object validation is enabled.
 * - {@code getValidFor()}: Returns the filter representing the validity period for the object validation.
 * - {@code setValidFor(TimePeriodFilters validFor)}: Sets the filter representing the validity period for the object validation.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ObjectValidationCriteriaAbstract<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> implements BaseFilters {
    private Filter<String> name;
    private Filter<String> status;
    private Filter<Boolean> enabled;
    private TimePeriodFilters validFor;

    public <C extends ObjectValidationCriteriaAbstract<ID>, DTO extends ObjectValidationAbstract<ID>> boolean isMatching(C criteria, DTO dto) {
        if (!super.isMatching((BaseCriteria<ID>) criteria, (BaseIdModel<ID>) dto)) {
            return false;
        }
        if (Filter.isMatching(criteria.getName(), dto.getName())) {
            return false;
        }
        if (Filter.isMatching(criteria.getStatus(), dto.getStatus())) {
            return false;
        }
        if (Filter.isMatching(criteria.getEnabled(), dto.getEnabled())) {
            return false;
        }
        return !TimePeriodFilters.isMatching(criteria.getValidFor(), dto.getValidFor());
    }
}