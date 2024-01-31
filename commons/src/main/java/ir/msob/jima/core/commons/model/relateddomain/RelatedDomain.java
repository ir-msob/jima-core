package ir.msob.jima.core.commons.model.relateddomain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a related domain with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 *
 * @param <ID> the type of the related domain ID, which must be comparable and serializable
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatedDomain<ID extends Comparable<ID> & Serializable> implements Comparable<RelatedDomain<ID>>, Serializable {

    /**
     * The type of the related domain.
     */
    @NotBlank
    private String relatedDomainType;

    /**
     * The ID of the related domain.
     */
    @NotNull
    private ID relatedDomainId;

    /**
     * The role of the related domain.
     */
    private String role;

    /**
     * The type of the entity that referred to this related domain.
     */
    private String referredType;

    /**
     * Compares this related domain with the specified related domain for order.
     * Returns a negative integer, zero, or a positive integer as this related domain is less than, equal to, or greater than the specified related domain.
     *
     * @param o the related domain to be compared
     * @return a negative integer, zero, or a positive integer as this related domain is less than, equal to, or greater than the specified related domain
     */
    @Override
    public int compareTo(RelatedDomain<ID> o) {
        if (this == o) {
            return 0;
        }

        int idCompare = Objects.compare(this.getRelatedDomainId(), o.getRelatedDomainId(), Comparable::compareTo);
        if (idCompare != 0) {
            return idCompare;
        }

        int typeCompare = Objects.compare(this.getRelatedDomainType(), o.getRelatedDomainType(), String::compareTo);
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

        if (o instanceof RelatedDomain<?> relatedDomain) {
            RelatedDomain<ID> that = (RelatedDomain<ID>) relatedDomain;
            return Objects.equals(this.getRelatedDomainId(), that.getRelatedDomainId())
                    && Objects.equals(this.getRelatedDomainType(), that.getRelatedDomainType())
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
        return Objects.hash(relatedDomainType, relatedDomainId, role, referredType);
    }

    /**
     * Enum representing the field names of the RelatedDomain class.
     */
    public enum FN {
        relatedDomainType, relatedDomainId, role, referredType
    }
}