package ir.msob.jima.core.commons.child.auditdomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.child.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.child.relatedobject.relatedparty.RelatedPartyAbstract;
import ir.msob.jima.core.commons.child.relatedobject.relatedparty.RelatedPartyCriteriaAbstract;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

/**
 * The {@code AuditDomainCriteriaAbstract} class represents a set of filters for querying audit domains.
 * It includes filters for the child party ID, action date, and action type.
 * This class implements the {@code BaseFilters} interface and includes getter and setter methods for each filter.
 * Additionally, it provides a no-argument constructor and a {@code toString} method that calls the superclass's {@code toString} method.
 * The class utilizes the {@code JsonInclude} annotation to specify that null fields should not be included in the JSON representation of an instance.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AuditDomainCriteriaAbstract<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>, RPF extends RelatedPartyCriteriaAbstract<ID, RP>, RM extends AuditDomainAbstract<ID, RP>> extends BaseChildCriteriaAbstract<ID, RM> implements BaseFilters {
    /**
     * Filter for the child party.
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

    @Override
    public boolean isMatching(RM relatedModel) {
        if (!super.isMatching(relatedModel)) {
            return false;
        }
        if (this.getRelatedParty() != null) {
            if (!this.getRelatedParty().isMatching(relatedModel.getRelatedParty())) {
                return false;
            }
        }
        if (Filter.isMatching(this.getActionDate(), relatedModel.getActionDate())) {
            return false;
        }
        return !Filter.isMatching(this.getActionType(), relatedModel.getActionType());
    }
}