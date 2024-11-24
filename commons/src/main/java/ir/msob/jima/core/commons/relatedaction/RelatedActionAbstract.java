package ir.msob.jima.core.commons.relatedaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseIdModelAbstract;
import ir.msob.jima.core.commons.shared.audit.auditinfo.AuditInfo;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * The {@code RelatedAction} class represents an action that is related to a specific context
 * within the application. It encapsulates various properties and behaviors associated with
 * the action, including its identification, status, and validity.
 * <p>
 * This class extends {@link BaseIdModelAbstract} and implements {@link Comparable} to allow
 * for sorting based on the action's name.
 *
 * <p>Fields:</p>
 * - {@code name}: The name of the related action (must not be blank).
 * - {@code status}: The current status of the related action (must not be blank).
 * - {@code mandatory}: A boolean indicating whether the related action is mandatory.
 * - {@code validFor}: A {@link TimePeriod} indicating the duration for which the action is valid.
 * - {@code auditInfo}: An {@link AuditInfo} object containing audit-related information for the action.
 *
 * <p>Methods:</p>
 * - {@code equals(Object o)}: Compares this related action with another object for equality based on the name.
 * - {@code hashCode()}: Returns the hash code for this related action based on its fields.
 * - {@code compareTo(RelatedAction<ID> o)}: Compares this related action with another based on their names.
 *
 * <p>Enum:</p>
 * - {@link FN}: Represents the field names of the class, including name, status, mandatory, validFor, and auditInfo.
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedActionAbstract<ID extends Comparable<ID> & Serializable> extends BaseIdModelAbstract<ID> implements Comparable<RelatedActionAbstract<ID>> {

    @NotBlank
    private String name;
    @NotBlank
    private String status;
    private Boolean mandatory;
    private TimePeriod validFor;
    private AuditInfo auditInfo;

    /**
     * Checks whether this characteristic is equal to another object based on their keys.
     *
     * @param o The other object.
     * @return True if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof RelatedActionAbstract<?> that)
            return Objects.equals(this.getName(), that.getName());

        return false;
    }

    /**
     * Returns the hash code of this characteristic based on its fields.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus(), getMandatory(), getValidFor(), getAuditInfo());
    }

    @Override
    public int compareTo(RelatedActionAbstract<ID> o) {
        if (this == o) {
            return 0;
        }

        if (o != null && (this.getName() != null && o.getName() != null)) {
            return this.getName().compareTo(o.getName());

        }

        return Comparator
                .comparing(System::identityHashCode)
                .compare(this, o);
    }

    /**
     * The 'FN' enum represents the field names of the class.
     */
    public enum FN {
        name, status, mandatory, validFor, auditInfo

    }
}