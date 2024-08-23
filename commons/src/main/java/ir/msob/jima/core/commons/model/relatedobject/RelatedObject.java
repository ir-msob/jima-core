package ir.msob.jima.core.commons.model.relatedobject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
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
@NoArgsConstructor
@AllArgsConstructor
public class RelatedObject<ID extends Comparable<ID> & Serializable> implements Comparable<RelatedObject<ID>>, Serializable {

    /**
     * The type of the related object.
     */
    @NotBlank
    private String objectType;

    /**
     * The ID of the related object.
     */
    @NotNull
    private ID objectId;

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

        int idComparison = Objects.compare(this.getObjectId(), other.getObjectId(), Comparable::compareTo);
        if (idComparison != 0) {
            return idComparison;
        }

        int typeComparison = Objects.compare(this.getObjectType(), other.getObjectType(), String::compareTo);
        if (typeComparison != 0) {
            return typeComparison;
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
        return Objects.equals(objectId, that.objectId) &&
                Objects.equals(objectType, that.objectType) &&
                Objects.equals(role, that.role) &&
                Objects.equals(referringType, that.referringType) &&
                Objects.equals(status, that.status);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(objectType, objectId, role, referringType, status);
    }

    /**
     * Enum representing the field names of the RelatedObject class.
     */
    public enum FN {
        objectType, objectId, role, referringType, status
    }
}
