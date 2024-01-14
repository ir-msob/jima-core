package ir.msob.jima.core.commons.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.msob.jima.core.commons.model.BaseModel;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The 'BaseDomain' interface represents the basic class for domain models.
 * It extends the 'BaseModel' interface and implements the 'Comparable' interface with a generic type 'BaseDomain<ID>'.
 * This means that the domain models can be compared based on their IDs.
 * The interface is a generic interface, with the generic type 'ID' extending 'Comparable' and 'Serializable'.
 * This means that the ID of the domain model can be of any type that is comparable and serializable.
 * The interface includes getter and setter methods for the domain ID and for the domain ID field name.
 * The interface also includes a 'compareTo' method for comparing domain models based on their IDs.
 *
 * @param <ID> The type of the ID of the domain model.
 *
 */
public interface BaseDomain<ID extends Comparable<ID> & Serializable> extends BaseModel, Comparable<BaseDomain<ID>> {

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

    /**
     * Compares this domain model with the specified domain model for order.
     * Returns a negative integer, zero, or a positive integer as this domain model is less than, equal to, or greater than the specified domain model.
     * The comparison is based on the domain IDs of the models.
     *
     * @param o The domain model to be compared.
     * @return A negative integer, zero, or a positive integer as this domain model is less than, equal to, or greater than the specified domain model.
     */
    @Override
    default int compareTo(BaseDomain<ID> o) {
        if (this == o) {
            return 0;
        }

        if (o != null && (this.getDomainId() != null && o.getDomainId() != null)) {
            return this.getDomainId().compareTo(o.getDomainId());

        }

        return Comparator
                .comparing(System::identityHashCode)
                .compare(this, o);
    }

}