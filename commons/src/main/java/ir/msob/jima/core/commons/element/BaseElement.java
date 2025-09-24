package ir.msob.jima.core.commons.element;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * The {@code BaseElement} interface represents the basic structure for domain models.
 * It is generic, allowing for any type of ID that is both {@code Comparable} and {@code Serializable}.
 * <p>
 * Fields:
 * - ID: The type of the domain model's ID.
 * <p>
 * Methods:
 * - {@code getId()}: Returns the domain ID of the model.
 * - {@code setId(ID id)}: Sets the domain ID of the model.
 * - {@code getIdName()}: Returns the name of the domain ID field.
 */
public interface BaseElement<ID extends Comparable<ID> & Serializable> extends Serializable {

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
    @JsonIgnore
    default String getIdName() {
        return "id";
    }

}