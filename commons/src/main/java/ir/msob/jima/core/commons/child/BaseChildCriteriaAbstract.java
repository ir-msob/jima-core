package ir.msob.jima.core.commons.child;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseChildCriteriaAbstract<ID extends Comparable<ID> & Serializable, RM extends BaseChild<ID>> extends ir.msob.jima.core.commons.criteria.BaseChildCriteriaAbstract<ID> implements BaseChildCriteria<ID, RM> {


    @Override
    public boolean isMatching(RM relatedModel) {
        if (relatedModel == null) {
            throw new BadRequestException("RelatedModel is null");
        }
        if (Filter.isMatching(this.getId(), relatedModel.getId())) {
            return false;
        }
        return !Filter.isMatching(this.getParentId(), relatedModel.getParentId());
    }
}
