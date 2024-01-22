package ir.msob.jima.core.commons.model.criteria;

import ir.msob.jima.core.commons.model.criteria.filter.Filter;

import java.io.Serializable;

/**
 * The 'BaseChildCriteria' interface represents the basic class for child criteria models.
 * It extends the 'BaseCriteria' interface with a generic type 'BaseChildCriteria<ID>'.
 * This means that the child criteria models can be compared based on their IDs.
 * The interface is a generic interface, with the generic type 'ID' extending 'Comparable' and 'Serializable'.
 * This means that the ID of the child criteria model can be of any type that is comparable and serializable.
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

}