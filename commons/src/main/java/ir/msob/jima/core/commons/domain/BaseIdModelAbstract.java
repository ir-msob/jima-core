package ir.msob.jima.core.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

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
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseIdModelAbstract<ID extends Comparable<ID> & Serializable> implements BaseIdModel<ID> {

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