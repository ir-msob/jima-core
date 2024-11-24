package ir.msob.jima.core.commons.shared.audit.auditdomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.relatedobject.relatedparty.RelatedPartyCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * The {@code AuditDomainFiltersAbstract} class represents a set of filters for querying audit domains.
 * It includes filters for the related party ID, action date, and action type.
 * This class implements the {@code BaseFilters} interface and includes getter and setter methods for each filter.
 * Additionally, it provides a no-argument constructor and a {@code toString} method that calls the superclass's {@code toString} method.
 * The class utilizes the {@code JsonInclude} annotation to specify that null fields should not be included in the JSON representation of an instance.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AuditDomainFiltersAbstract<RPF extends RelatedPartyCriteriaAbstract> implements BaseFilters {
    /**
     * Filter for the related party.
     */
    private RPF relatedParty;
    /**
     * Filter for the action date.
     */
    private Filter<Instant> actionDate;
    /**
     * Filter for the action type.
     */
    private Filter<String> actionType;
}