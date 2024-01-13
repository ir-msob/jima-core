package ir.msob.jima.core.commons.model.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditDomainFilters<ID extends Comparable<ID> & Serializable> implements BaseFilters {
    private Filter<ID> relatedPartyId;
    private Filter<Instant> actionDate;
    private Filter<AuditDomainActionType> actionType;
}
