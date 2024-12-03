package ir.msob.jima.core.commons.domain;

import java.io.Serializable;

/**
 * The {@code BaseIdModel} interface represents the basic structure for domain models.
 * It is generic, allowing for any type of ID that is both {@code Comparable} and {@code Serializable}.
 * <p>
 * Fields:
 * - ID: The type of the domain model's ID.
 * <p>
 * Methods:
 * - {@code getDomainId()}: Returns the domain ID of the model.
 * - {@code setDomainId(ID id)}: Sets the domain ID of the model.
 * - {@code getDomainIdName()}: Returns the name of the domain ID field.
 */
public interface BaseIdModel<ID extends Comparable<ID> & Serializable> extends Serializable {

    /**
     * Returns the domain ID of the model.
     *
     * @return The domain ID.
     */
    ID getId();

    /**
     * Sets the domain ID of the model.
     *
     * @param id The domain ID.
     */
    void setId(ID id);

    /**
     * Returns the domain ID field name of the model.
     *
     * @return The domain ID field name.
     */
    default String getIdName() {
        return "id";
    }

}