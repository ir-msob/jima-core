package ir.msob.jima.core.commons.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * The {@code BaseIdModel} interface represents the basic structure for domain models.
 * It is generic, allowing for any type of ID that is both {@code Comparable} and {@code Serializable}.
 * 
 * Fields:
 * - ID: The type of the domain model's ID.
 * 
 * Methods:
 * - {@code getDomainId()}: Returns the domain ID of the model.
 * - {@code setDomainId(ID id)}: Sets the domain ID of the model.
 * - {@code getDomainIdName()}: Returns the name of the domain ID field.
 */
public interface BaseIdModel<ID extends Comparable<ID> & Serializable> {

    /**
     * Returns the domain ID of the model.
     *
     * @return The domain ID.
     */
    @Transient
    @JsonIgnore
    ID getDomainId();

    /**
     * Sets the domain ID of the model.
     *
     * @param id The domain ID.
     */
    void setDomainId(ID id);

    /**
     * Returns the domain ID field name of the model.
     *
     * @return The domain ID field name.
     */
    @Transient
    @JsonIgnore
    String getDomainIdName();

}