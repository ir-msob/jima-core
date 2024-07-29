package ir.msob.jima.core.commons.model.relatedobject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class representing a related entity with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 *
 * @param <ID> the type of the related entity ID, which must be comparable and serializable
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RelatedObject<ID extends Comparable<ID> & Serializable> implements Comparable<RelatedObject<ID>>, Serializable {

    /**
     * The type of the related entity.
     */
    @NotBlank
    private String relatedType;

    /**
     * The ID of the related entity.
     */
    @NotNull
    private ID relatedId;

    /**
     * The role of the related entity.
     */
    private String role;

    /**
     * The type of the entity that referred to this related entity.
     */
    private String referredType;

    /**
     * Compares this related entity with the specified related entity for order.
     * Returns a negative integer, zero, or a positive integer as this related entity is less than, equal to, or greater than the specified related entity.
     *
     * @param o the related entity to be compared
     * @return a negative integer, zero, or a positive integer as this related entity is less than, equal to, or greater than the specified related entity
     */
    @Override
    public int compareTo(RelatedObject<ID> o) {
        if (this == o) {
            return 0;
        }

        int idCompare = Objects.compare(this.getRelatedId(), o.getRelatedId(), Comparable::compareTo);
        if (idCompare != 0) {
            return idCompare;
        }

        int typeCompare = Objects.compare(this.getRelatedType(), o.getRelatedType(), String::compareTo);
        if (typeCompare != 0) {
            return typeCompare;
        }

        int roleCompare = Objects.compare(this.getRole(), o.getRole(), String::compareTo);
        if (roleCompare != 0) {
            return roleCompare;
        }

        return Objects.compare(this.getReferredType(), o.getReferredType(), String::compareTo);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof RelatedObject<?> that) {
            return Objects.equals(this.getRelatedId(), that.getRelatedId())
                    && Objects.equals(this.getRelatedType(), that.getRelatedType())
                    && Objects.equals(this.getRole(), that.getRole())
                    && Objects.equals(this.getReferredType(), that.getReferredType());
        }
        return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(relatedType, relatedId, role, referredType);
    }

    /**
     * Enum representing the field names of the RelatedEntity class.
     */
    public enum FN {
        relatedType, relatedId, role, referredType
    }
}