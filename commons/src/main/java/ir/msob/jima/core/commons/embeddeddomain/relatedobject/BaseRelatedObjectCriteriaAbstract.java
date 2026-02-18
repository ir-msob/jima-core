package ir.msob.jima.core.commons.embeddeddomain.relatedobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.embeddeddomain.criteria.BaseEmbeddedCriteriaAbstract;
import ir.msob.jima.core.commons.embeddeddomain.criteria.BaseEmbeddedCriteriaRelatedId;
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
 * Base class representing the filters that can be applied when searching for related object entities.
 * It implements the BaseFilters interface and provides filters for the related object entity type, ID, role, referring type, status, enabled state, relation date, and expiration date.
 *
 * @param <ID>  the type of the related object entity ID, which must be comparable and serializable
 * @param <RID> the type of the related ID, which must be comparable and serializable
 * @param <CD>  the type of the related object entity
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseRelatedObjectCriteriaAbstract<ID extends Comparable<ID> & Serializable, RID extends Comparable<RID> & Serializable, CD extends BaseRelatedObject<ID, RID>> extends BaseEmbeddedCriteriaAbstract<ID, CD> implements BaseEmbeddedCriteriaRelatedId<ID, RID, CD>, BaseFilters {

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

    /**
     * Filters for the valid time period of the related object.
     */
    private TimePeriodFilters validFor;

    /**
     * Filters for the audit information of the related object.
     */
    private AuditInfoFilters auditInfo;

    /**
     * Checks if the given related model matches the criteria defined by the filters.
     *
     * @param relatedModel the related model to be checked
     * @return true if the related model matches the criteria, false otherwise
     */
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