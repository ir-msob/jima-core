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

/**
 * The 'AuditDomainFilters' class represents a set of filters for querying audit domains.
 * It includes filters for the related party ID, action date, and action type.
 * The class implements the 'BaseFilters' interface and includes getter and setter methods for each filter.
 * It also includes a no-argument constructor and a 'toString' method that calls the superclass's 'toString' method.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 *
 * @param <ID> The type of ID.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditDomainFilters<ID extends Comparable<ID> & Serializable> implements BaseFilters {
    /**
     * Filter for the related party ID.
     */
    private Filter<ID> relatedPartyId;
    /**
     * Filter for the action date.
     */
    private Filter<Instant> actionDate;
    /**
     * Filter for the action type.
     */
    private Filter<AuditDomainActionType> actionType;
}