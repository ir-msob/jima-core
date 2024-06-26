package ir.msob.jima.core.commons.model.audit;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * The {@code AuditDomain} class represents a domain object for auditing purposes.
 * It includes fields for the related party ID, action date, action type, and version.
 * This class provides several constructors for creating an instance of the model with different sets of parameters.
 * Additionally, it overrides the {@code compareTo}, {@code equals}, and {@code hashCode} methods from the {@code Object} class to provide custom comparison and hashing behavior.
 * The {@code FN} enum is used to represent the field names of the {@code AuditDomain} class.
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDomain implements Comparable<AuditDomain>, Serializable {
    /**
     * The ID of the related party.
     */
    @NotNull
    private String relatedPartyId;
    /**
     * The date of the action.
     */
    @NotNull
    private Instant actionDate;
    /**
     * The type of the action.
     */
    @NotNull
    private AuditDomainActionType actionType;
    /**
     * The version of the audit domain.
     */
    private Long version = 0L;

    /**
     * Compares this audit domain with the specified audit domain for order.
     *
     * @param o The audit domain to be compared.
     * @return A negative integer, zero, or a positive integer as this audit domain is less than, equal to, or greater than the specified audit domain.
     */
    @Override
    public int compareTo(AuditDomain o) {
        if (this == o) {
            return 0;
        }
        int compare = Objects.compare(this.getVersion(), o.getVersion(), Long::compareTo);
        if (compare != 0) {
            return compare;
        }

        compare = Objects.compare(this.getActionDate(), o.getActionDate(), Instant::compareTo);
        if (compare != 0) {
            return compare;
        }

        compare = Objects.compare(this.getRelatedPartyId(), o.getRelatedPartyId(), String::compareTo);
        if (compare != 0) {
            return compare;
        }

        return Objects.compare(this.getActionType(), o.getActionType(), AuditDomainActionType::compareTo);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof AuditDomain that) {
            return Objects.equals(this.getRelatedPartyId(), that.getRelatedPartyId())
                    && Objects.equals(this.getActionDate(), that.getActionDate())
                    && Objects.equals(this.getActionType(), that.getActionType())
                    && Objects.equals(this.getVersion(), that.getVersion());
        }
        return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(relatedPartyId, actionDate, actionType, version);
    }

    /**
     * The 'FN' enum represents the field names of the 'AuditDomain' class.
     */
    public enum FN {
        relatedPartyId, actionDate, actionType, version
    }
}