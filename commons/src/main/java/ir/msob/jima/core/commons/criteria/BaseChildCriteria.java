package ir.msob.jima.core.commons.criteria;

import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.dto.BaseChildDto;

import java.io.Serializable;

/**
 * The {@code BaseChildCriteria} interface represents the basic class for child criteria models.
 * It extends the {@code BaseCriteria} interface with a generic type {@code BaseChildCriteria<ID>},
 * allowing child criteria models to be compared based on their IDs.
 * <p>
 * This interface is generic, with the generic type {@code ID} extending {@code Comparable} and {@code Serializable}.
 * It means that the ID of the child criteria model can be of any type that is comparable and serializable.
 * <p>
 * The interface includes getter and setter methods for the parent criteria ID.
 *
 * @param <ID> the type of the identifier for the criteria model. It must be comparable and serializable.
 */

public interface BaseChildCriteria<ID extends Comparable<ID> & Serializable> extends BaseCriteria<ID> {

    /**
     * Returns the parent criteria ID of the model.
     *
     * @return The parent criteria ID.
     */
    Filter<ID> getParentId();

    /**
     * Sets the parent criteria ID of the model.
     *
     * @param id The parent criteria ID to be set.
     */
    void setParentId(Filter<ID> id);

    default <C extends BaseChildCriteria<ID>, DTO extends BaseChildDto<ID>> boolean isMatching(C criteria, DTO dto) {
        if (!BaseCriteria.super.isMatching(criteria, dto)) {
            return false;
        }
        return !Filter.isMatching(criteria.getParentId(), dto.getParentId());
    }
}