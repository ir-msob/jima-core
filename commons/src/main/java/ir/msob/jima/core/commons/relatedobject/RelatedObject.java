package ir.msob.jima.core.commons.relatedobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Base class representing a related object with a type, an ID, a role, and a referring type.
 * Implements Comparable interface to provide natural ordering of instances.
 *
 * @param <ID> the type of the related object ID, which must be comparable and serializable
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedObject<ID extends Comparable<ID> & Serializable> implements Comparable<RelatedObject<ID>>, Serializable {

    /**
     * The name of the related object.
     */
    @NotBlank
    private String name;

    /**
     * The ID of the related object.
     */
    @NotNull
    private ID relatedId;

    /**
     * The role of the related object.
     */
    private String role;

    /**
     * The type of the object that referred to this related object.
     */
    private String referringType;

    /**
     * The status of the related object.
     */
    private String status;

    /**
     * Indicates whether the related object is enabled.
     */
    private Boolean enabled;

    /**
     * The date of the relationship with the related object.
     */
    private Instant relationDate;

    /**
     * The expiration date of the related object.
     */
    private Instant expirationDate;

    /**
     * Checks if the related object is expired.
     *
     * @return true if the related object is expired, false if it is not expired,
     * and null if the expiration date is not set.
     */
    public Boolean isExpired() {
        if (expirationDate == null) return null;
        return Instant.now().isAfter(expirationDate);
    }

    /**
     * Compares this related object with the specified related object for order.
     *
     * @param other the related object to be compared
     * @return a negative integer, zero, or a positive integer as this related object is less than, equal to, or greater than the specified related object
     */
    @Override
    public int compareTo(RelatedObject<ID> other) {
        if (this == other) {
            return 0;
        }

        int idComparison = Objects.compare(this.getRelatedId(), other.getRelatedId(), Comparable::compareTo);
        if (idComparison != 0) {
            return idComparison;
        }

        int nameComparison = Objects.compare(this.getName(), other.getName(), String::compareTo);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int roleComparison = Objects.compare(this.getRole(), other.getRole(), String::compareTo);
        if (roleComparison != 0) {
            return roleComparison;
        }

        return Objects.compare(this.getReferringType(), other.getReferringType(), String::compareTo);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        RelatedObject<?> that = (RelatedObject<?>) obj;
        return Objects.equals(relatedId, that.relatedId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(role, that.role) &&
                Objects.equals(referringType, that.referringType) &&
                Objects.equals(status, that.status) &&
                enabled == that.enabled &&
                Objects.equals(relationDate, that.relationDate) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, relatedId, role, referringType, status, enabled, relationDate, expirationDate);
    }

    /**
     * Enum representing the field names of the RelatedObject class.
     */
    public enum FN {
        name, relatedId, role, referringType, status, enabled, relationDate, expirationDate
    }
}
