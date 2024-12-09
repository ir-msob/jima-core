package ir.msob.jima.core.commons.child.relatedobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.child.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfoFilters;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Base class representing the filters that can be applied when searching for child entities.
 * It implements the BaseFilters interface and provides filters for the child entity type, ID, role, referring type, status, enabled state, relation date, and expiration date.
 *
 * @param <ID> the type of the child entity ID, which must be comparable and serializable
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedObjectCriteriaAbstract<ID extends Comparable<ID> & Serializable, RID extends Comparable<RID> & Serializable, RM extends RelatedObjectAbstract<ID, RID>> extends BaseChildCriteriaAbstract<ID, RM> implements BaseFilters {

    /**
     * Filter for the name of the child object.
     */
    private Filter<String> name;

    /**
     * Filter for the ID of the child object.
     */
    private Filter<RID> relatedId;

    /**
     * Filter for the role of the child object.
     */
    private Filter<String> role;

    /**
     * Filter for the type of the object that referred to this child object.
     */
    private Filter<String> referringType;

    /**
     * Filter for the status of the child object.
     */
    private Filter<String> status;

    /**
     * Filter for the enabled state of the child object.
     */
    private Filter<Boolean> enabled;

    private TimePeriodFilters validFor;

    private AuditInfoFilters auditInfo;

    @Override
    public boolean isMatching(RM relatedModel) {
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
