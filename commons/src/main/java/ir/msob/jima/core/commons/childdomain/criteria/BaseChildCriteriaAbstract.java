package ir.msob.jima.core.commons.childdomain.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.BaseChildDomain;
import ir.msob.jima.core.commons.domain.BaseCriteriaAbstract;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * The {@code BaseChildCriteriaAbstract} class is an abstract implementation of the {@code BaseChildCriteria} interface.
 * It extends the {@code BaseCriteriaAbstract} class with a generic type {@code BaseChildCriteriaAbstract<ID>},
 * enabling childdomain criteria models to be compared based on their IDs.
 * <p>
 * This class is generic, with the generic type {@code ID} extending {@code Comparable} and {@code Serializable}.
 * It means that the ID of the childdomain criteria model can be of any type that is comparable and serializable.
 * <p>
 * The class includes getter and setter methods for the parent criteria ID.
 * Additionally, it includes a static final long {@code serialVersionUID} which represents the version control for the class.
 * <p>
 * The class is annotated with {@code JsonInclude} to control the serialization of null values.
 *
 * @param <ID> the type of the identifier for the criteria model. It must be comparable and serializable.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseChildCriteriaAbstract<ID extends Comparable<ID> & Serializable, RM extends BaseChildDomain<ID>> extends BaseCriteriaAbstract<ID> implements BaseChildCriteria<ID, RM> {


    /**
     * The version control for the class.
     */
    @Serial
    private static final long serialVersionUID = -2881143746650108596L;

    /**
     * The parent criteria ID of the model.
     */
    private Filter<ID> parentId;

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
