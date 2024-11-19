package ir.msob.jima.core.commons.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'BaseDomainAbstract' class is an abstract class that provides a basic implementation for domain models.
 * It implements the 'BaseDomain' interface with a generic type 'ID' that extends 'Comparable' and 'Serializable'.
 * This means that the ID of the domain model can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the domain ID and for the domain ID field name.
 * The class also includes a no-argument constructor.
 * The class includes an inner enum 'FN' which represents the field names of the domain model.
 *
 * @param <ID> The type of the ID of the domain model.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseDomainAbstract<ID extends Comparable<ID> & Serializable> implements BaseDomain<ID> {

    /**
     * The ID of the domain model.
     */
    private ID id;

    /**
     * Returns the domain ID of the model.
     *
     * @return The domain ID.
     */
    @Override
    public ID getDomainId() {
        return id;
    }

    /**
     * Sets the domain ID of the model.
     *
     * @param id The domain ID.
     */
    @Override
    public void setDomainId(ID id) {
        this.id = id;
    }

    /**
     * Returns the domain ID field name of the model.
     *
     * @return The domain ID field name.
     */
    @Override
    public String getDomainIdName() {
        return FN.id.name();
    }

    /**
     * The 'FN' enum represents the field names of the domain model.
     */
    public enum FN {
        id
    }
}