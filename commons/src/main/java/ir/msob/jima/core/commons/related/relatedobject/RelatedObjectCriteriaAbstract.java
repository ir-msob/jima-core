package ir.msob.jima.core.commons.related.relatedobject;

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
public abstract class RelatedObjectCriteriaAbstract<ID extends Comparable<ID> & Serializable, RID extends Comparable<RID> & Serializable> extends BaseCriteriaAbstract<ID> implements BaseFilters {

    /**
     * Filter for the name of the related object.
     */
    private Filter<String> name;

    /**
     * Filter for the ID of the related object.
     */
    private Filter<RID> relatedId;

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

    private TimePeriodFilters validFor;

    private AuditInfoFilters auditInfo;

    public <C extends RelatedObjectCriteriaAbstract<ID, RID>, DTO extends RelatedObjectAbstract<ID, RID>> boolean isMatching(C criteria, DTO dto) {
        if (!super.isMatching((BaseCriteria<ID>) criteria, (BaseIdModel<ID>) dto)) {
            return false;
        }
        if (Filter.isMatching(criteria.getName(), dto.getName())) {
            return false;
        }
        if (Filter.isMatching(criteria.getRelatedId(), dto.getRelatedId())) {
            return false;
        }
        if (Filter.isMatching(criteria.getRole(), dto.getRole())) {
            return false;
        }
        if (Filter.isMatching(criteria.getReferringType(), dto.getReferringType())) {
            return false;
        }
        if (Filter.isMatching(criteria.getStatus(), dto.getStatus())) {
            return false;
        }
        if (Filter.isMatching(criteria.getEnabled(), dto.getEnabled())) {
            return false;
        }
        if (TimePeriodFilters.isMatching(criteria.getValidFor(), dto.getValidFor())) {
            return false;
        }
        return !AuditInfoFilters.isMatching(criteria.getAuditInfo(), dto.getAuditInfo());
    }

}
