package ir.msob.jima.core.commons.objectvalidation;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.shared.criteria.filter.Filter;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The {@code ObjectValidationFilters} class represents a set of filters for object validations.
 * It implements the {@link BaseFilters} interface.
 * 
 * Fields:
 * - {@code name}: A filter for the name of the object validation. It is of type {@link Filter<String>}.
 * - {@code status}: A filter for the status of the object validation. It is of type {@link Filter<String>}.
 * - {@code enabled}: A filter indicating whether the object validation is enabled. It is of type {@link Filter<Boolean>}.
 * - {@code validFor}: A filter representing the validity period for the object validation. It is of type {@link TimePeriodFilters}.
 * 
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
public class ObjectValidationFilters implements BaseFilters {
    private Filter<String> name;
    private Filter<String> status;
    private Filter<Boolean> enabled;
    private TimePeriodFilters validFor;
}