package ir.msob.jima.core.commons.model.relatedparty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a related party with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatedParty implements Comparable<RelatedParty>, Serializable {

    /**
     * The type of the related party.
     */
    @NotBlank
    private String relatedPartyType;

    /**
     * The ID of the related party.
     */
    @NotNull
    private String relatedPartyId;

    /**
     * The role of the related party.
     */
    private String role;

    /**
     * The type of the entity that referred to this related party.
     */
    private String referredType;

    /**
     * Compares this related party with the specified related party for order.
     * Returns a negative integer, zero, or a positive integer as this related party is less than, equal to, or greater than the specified related party.
     *
     * @param o the related party to be compared
     * @return a negative integer, zero, or a positive integer as this related party is less than, equal to, or greater than the specified related party
     */
    @Override
    public int compareTo(RelatedParty o) {
        if (this == o) {
            return 0;
        }

        int idCompare = Objects.compare(this.getRelatedPartyId(), o.getRelatedPartyId(), Comparable::compareTo);
        if (idCompare != 0) {
            return idCompare;
        }

        int typeCompare = Objects.compare(this.getRelatedPartyType(), o.getRelatedPartyType(), String::compareTo);
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

        if (o instanceof RelatedParty that) {
            return Objects.equals(this.getRelatedPartyId(), that.getRelatedPartyId())
                    && Objects.equals(this.getRelatedPartyType(), that.getRelatedPartyType())
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
        return Objects.hash(relatedPartyType, relatedPartyId, role, referredType);
    }

    /**
     * Enum representing the field names of the RelatedParty class.
     */
    public enum FN {
        relatedPartyType, relatedPartyId, role, referredType
    }
}