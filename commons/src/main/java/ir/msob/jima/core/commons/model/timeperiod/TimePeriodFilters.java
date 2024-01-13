package ir.msob.jima.core.commons.model.timeperiod;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimePeriodFilters implements BaseFilters {
    private Filter<Instant> start;
    private Filter<Instant> end;
}
