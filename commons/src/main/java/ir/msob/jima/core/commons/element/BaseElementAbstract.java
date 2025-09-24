package ir.msob.jima.core.commons.element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * The {@code BaseElementAbstract} class provides a base implementation for domain models.
 * It extends the {@code BaseElement} interface and includes common functionality for domain models.
 * <p>
 * This abstract class is generic, with the generic type {@code ID} extending {@code Comparable} and {@code Serializable}.
 * It means that the ID of the domain model can be of any type that is comparable and serializable.
 * <p>
 * The class includes fields for the domain ID and methods for getting and setting the domain ID.
 * Additionally, it provides a method for getting the domain ID field name and an enum representing the field names of the domain model.
 *
 * @param <ID> The type of the ID of the domain model.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseElementAbstract<ID extends Comparable<ID> & Serializable> implements BaseElement<ID> {

    /**
     * The ID of the domain model.
     */
    private ID id;

    /**
     * Returns the domain ID field name of the model.
     *
     * @return The domain ID field name.
     */
    @Override
    @JsonIgnore
    public String getIdName() {
        return FN.id.name();
    }

    /**
     * The 'FN' enum represents the field names of the domain model.
     */
    public enum FN {
        id
    }

}