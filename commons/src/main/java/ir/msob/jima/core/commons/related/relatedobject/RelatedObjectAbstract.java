package ir.msob.jima.core.commons.related.relatedobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseIdModelAbstract;
import ir.msob.jima.core.commons.shared.audit.auditinfo.AuditInfo;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * The {@code RelatedObjectAbstract} class represents a related object with a type, an ID, a role,
 * and a referring type. It implements the {@link Comparable} interface to provide natural
 * ordering of instances based on specific fields.
 *
 * <p>Fields:</p>
 * - {@code name}: The name of the related object (must not be blank).
 * - {@code relatedId}: The ID of the related object (must not be null).
 * - {@code role}: The role of the related object, which can be null.
 * - {@code referringType}: The type of the object that referred to this related object, which can be null.
 * - {@code status}: The status of the related object, which can be null.
 * - {@code enabled}: A boolean indicating whether the related object is enabled, which can be null.
 * - {@code validFor}: A {@link TimePeriod} indicating the time period for which the related object is valid.
 * - {@code auditInfo}: An {@link AuditInfo} object containing audit-related information for the related object.
 *
 * <p>Methods:</p>
 * - {@code compareTo(RelatedObjectAbstract<ID> other)}: Compares this related object with another for order
 * based on relatedId, name, role, and referringType.
 * - {@code equals(Object obj)}: Indicates whether some other object is "equal to" this one based on
 * relatedId, name, role, and referringType.
 * - {@code hashCode()}: Returns a hash code value for the object based on its fields.
 *
 * <p>Enum:</p>
 * - {@link FN}: Represents the field names of the {@code RelatedObjectAbstract} class, including name,
 * relatedId, role, referringType, status, enabled, validFor, and auditInfo.
 *
 * @param <ID> the type of the related object ID, which must be comparable and serializable
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedObjectAbstract<ID extends Comparable<ID> & Serializable, RID extends Comparable<RID> & Serializable> extends BaseIdModelAbstract<ID> implements Comparable<RelatedObjectAbstract<ID, RID>> {

    /**
     * The name of the related object.
     */
    @NotBlank
    private String name;

    /**
     * The ID of the related object.
     */
    @NotBlank
    private RID relatedId;

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
     * The time period for which the related object is valid.
     */
    private TimePeriod validFor;

    /**
     * Represents the audit information associated with the related object.
     */
    private AuditInfo auditInfo;

    /**
     * Compares this related object with the specified related object for order.
     *
     * @param other the related object to be compared
     * @return a negative integer, zero, or a positive integer as this related object is less than, equal to, or greater than the specified related object
     */
    @Override
    public int compareTo(RelatedObjectAbstract<ID, RID> other) {
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

        RelatedObjectAbstract<?, ?> that = (RelatedObjectAbstract<?, ?>) obj;
        return Objects.equals(relatedId, that.relatedId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(role, that.role) &&
                Objects.equals(referringType, that.referringType);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, relatedId, role, referringType, status, enabled, validFor, auditInfo);
    }

    /**
     * Enum representing the field names of the RelatedObjectAbstract class.
     */
    public enum FN {
        name, relatedId, role, referringType, status, enabled, validFor, auditInfo
    }
}
