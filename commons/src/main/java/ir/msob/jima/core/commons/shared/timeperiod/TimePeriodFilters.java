package ir.msob.jima.core.commons.shared.timeperiod;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
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
    private Filter<Instant> startDate;

    /**
     * Filter for the end instant of the time period.
     */
    private Filter<Instant> endDate;

    public static boolean isMatching(TimePeriodFilters filter, TimePeriod timePeriod) {
        if (filter != null) {
            return filter.isMatching(timePeriod);
        }
        return true;
    }

    public boolean isMatching(TimePeriod timePeriod) {
        if (this.getStartDate() != null) {
            if (timePeriod == null) {
                if (!this.getStartDate().isMatching(null)) {
                    return false;
                }
            } else {
                if (!this.getStartDate().isMatching(timePeriod.getStartDate())) {
                    return false;
                }
            }
        }
        if (this.getEndDate() != null) {
            if (timePeriod == null) {
                return this.getEndDate().isMatching(null);
            } else {
                return this.getEndDate().isMatching(timePeriod.getEndDate());
            }
        }
        return true;
    }
}