package ir.msob.jima.core.commons.childdomain.relatedobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaRelatedId;
import ir.msob.jima.core.commons.filter.BaseFilters;
import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfoFilters;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Base class representing the filters that can be applied when searching for childdomain entities.
 * It implements the BaseFilters interface and provides filters for the childdomain entity type, ID, role, referring type, status, enabled state, relation date, and expiration date.
 *
 * @param <ID> the type of the childdomain entity ID, which must be comparable and serializable
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedObjectCriteriaAbstract<ID extends Comparable<ID> & Serializable, RID extends Comparable<RID> & Serializable, CD extends RelatedObjectAbstract<ID, RID>> extends BaseChildCriteriaAbstract<ID, CD> implements BaseChildCriteriaRelatedId<ID, RID, CD>, BaseFilters {

    /**
     * Filter for the name of the childdomain object.
     */
    private Filter<String> name;

    /**
     * Filter for the ID of the childdomain object.
     */
    private Filter<RID> relatedId;

    /**
     * Filter for the role of the childdomain object.
     */
    private Filter<String> role;

    /**
     * Filter for the type of the object that referred to this childdomain object.
     */
    private Filter<String> referringType;

    /**
     * Filter for the status of the childdomain object.
     */
    private Filter<String> status;

    /**
     * Filter for the enabled state of the childdomain object.
     */
    private Filter<Boolean> enabled;

    private TimePeriodFilters validFor;

    private AuditInfoFilters auditInfo;

    @Override
    public boolean isMatching(CD relatedModel) {
        if (!super.isMatching(relatedModel)) {
            return false;
        }
        if (Filter.isMatching(this.getName(), relatedModel.getName())) {
            return false;
        }
        if (Filter.isMatching(this.getRelatedId(), relatedModel.getRelatedId())) {
            return false;
        }
        if (Filter.isMatching(this.getRole(), relatedModel.getRole())) {
            return false;
        }
        if (Filter.isMatching(this.getReferringType(), relatedModel.getReferringType())) {
            return false;
        }
        if (Filter.isMatching(this.getStatus(), relatedModel.getStatus())) {
            return false;
        }
        if (Filter.isMatching(this.getEnabled(), relatedModel.getEnabled())) {
            return false;
        }
        if (TimePeriodFilters.isMatching(this.getValidFor(), relatedModel.getValidFor())) {
            return false;
        }
        return !AuditInfoFilters.isMatching(this.getAuditInfo(), relatedModel.getAuditInfo());
    }

}
