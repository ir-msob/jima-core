package ir.msob.jima.core.commons.model.domain;

import java.io.Serializable;

/**
 * The 'BaseChildDomain' interface represents the basic class for child domain models.
 * It extends the 'BaseDomain' interface with a generic type 'BaseChildDomain<ID>'.
 * This means that the child domain models can be compared based on their IDs.
 * The interface is a generic interface, with the generic type 'ID' extending 'Comparable' and 'Serializable'.
 * This means that the ID of the child domain model can be of any type that is comparable and serializable.
 * The interface includes getter and setter methods for the parent domain ID.
 *
 * @param <ID> the type of the identifier for the domain model. It must be comparable and serializable.
 */
public interface BaseChildDomain<ID extends Comparable<ID> & Serializable> extends BaseDomain<ID> {

    /**
     * Returns the parent domain ID of the model.
     *
     * @return The parent domain ID.
     */
    ID getParentId();

    /**
     * Sets the parent domain ID of the model.
     *
     * @param id The parent domain ID to be set.
     */
    void setParentId(ID id);

}