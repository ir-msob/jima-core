package ir.msob.jima.core.commons.domain;

import ir.msob.jima.core.commons.shared.BaseModel;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The {@code BaseDomain} interface represents the basic class for domain models.
 * It extends the {@code BaseModel} interface and implements the {@code Comparable} interface
 * with a generic type {@code BaseDomain<ID>}, enabling domain models to be compared based on their IDs.
 * <p>
 * This interface is generic, with the generic type {@code ID} extending {@code Comparable} and {@code Serializable}.
 * It means that the ID of the domain model can be of any type that is comparable and serializable.
 * <p>
 * The interface includes getter and setter methods for the domain ID and for the domain ID field name.
 * Additionally, it provides a {@code compareTo} method for comparing domain models based on their IDs.
 *
 * @param <ID> The type of the ID of the domain model.
 */
public interface BaseDomain<ID extends Comparable<ID> & Serializable> extends BaseModel, Comparable<BaseDomain<ID>>, BaseIdModel<ID> {


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