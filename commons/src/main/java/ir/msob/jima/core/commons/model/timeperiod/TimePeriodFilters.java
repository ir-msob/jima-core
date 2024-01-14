package ir.msob.jima.core.commons.model.timeperiod;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * This class represents the filters that can be applied when searching for time periods.
 * It implements the BaseFilters interface and provides filters for the start and end instants of a time period.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimePeriodFilters implements BaseFilters {
    /**
     * Filter for the start instant of the time period.
     */
    private Filter<Instant> start;

    /**
     * Filter for the end instant of the time period.
     */
    private Filter<Instant> end;
}