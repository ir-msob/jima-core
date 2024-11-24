package ir.msob.jima.core.commons.relatedobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.audit.auditinfo.AuditInfoFilters;
import ir.msob.jima.core.commons.shared.criteria.BaseCriteriaAbstract;
import ir.msob.jima.core.commons.shared.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.shared.criteria.filter.Filter;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

/**
 * Base class representing the filters that can be applied when searching for related entities.
 * It implements the BaseFilters interface and provides filters for the related entity type, ID, role, referring type, status, enabled state, relation date, and expiration date.
 *
 * @param <ID> the type of the related entity ID, which must be comparable and serializable
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedObjectCriteriaAbstract<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> implements BaseFilters {

    /**
     * Filter for the name of the related object.
     */
    private Filter<String> name;

    /**
     * Filter for the ID of the related object.
     */
    private Filter<ID> relatedId;

    /**
     * Filter for the role of the related object.
     */
    private Filter<String> role;

    /**
     * Filter for the type of the object that referred to this related object.
     */
    private Filter<String> referringType;

    /**
     * Filter for the status of the related object.
     */
    private Filter<String> status;

    /**
     * Filter for the enabled state of the related object.
     */
    private Filter<Boolean> enabled;

    /**
     * Filter for the relation date of the related object.
     */
    private Filter<Instant> relationDate;

    /**
     * Filter for the expiration date of the related object.
     */
    private Filter<Instant> expirationDate;

    private TimePeriodFilters validFor;

    private AuditInfoFilters auditInfo;

}
