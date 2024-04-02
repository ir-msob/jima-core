package ir.msob.jima.core.commons.model.domain;

import java.io.Serializable;

/**
 * The {@code BaseChildDomain} interface represents the basic class for child domain models.
 * It extends the {@code BaseDomain} interface with a generic type {@code BaseChildDomain<ID>},
 * indicating that child domain models can be compared based on their IDs.
 * <p>
 * This interface is generic, with the generic type {@code ID} extending {@code Comparable<ID>} and {@code Serializable}.
 * This allows the ID of the child domain model to be of any type that is comparable and serializable.
 * <p>
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