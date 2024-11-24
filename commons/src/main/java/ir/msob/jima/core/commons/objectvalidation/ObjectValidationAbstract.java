package ir.msob.jima.core.commons.objectvalidation;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseIdModelAbstract;
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
 * The {@code ObjectValidation} class represents an object validation with a key, value, and data type.
 * It extends {@link BaseIdModelAbstract} and implements {@link Comparable} for sorting based on the name.
 * <p>
 * Fields:
 * - {@code name}: The name of the object validation (must not be blank).
 * - {@code status}: The status of the object validation (must not be blank).
 * - {@code enabled}: A boolean indicating whether the object validation is enabled.
 * - {@code validFor}: A {@link TimePeriod} indicating the validity duration of the object validation.
 * <p>
 * Methods:
 * - {@code equals(Object o)}: Checks whether this object validation is equal to another based on their names.
 * - {@code hashCode()}: Returns the hash code of this object validation based on its fields.
 * - {@code compareTo(ObjectValidation<ID> o)}: Compares this object validation with another based on their names.
 * <p>
 * Enum:
 * - {@link FN}: Represents the field names of the class, including name, status, enabled, and validFor.
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ObjectValidationAbstract<ID extends Comparable<ID> & Serializable> extends BaseIdModelAbstract<ID> implements Comparable<ObjectValidationAbstract<ID>> {

    @NotBlank
    private String name;
    @NotBlank
    private String status;
    private Boolean enabled;
    private TimePeriod validFor;


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

        if (o instanceof ObjectValidationAbstract<?> that)
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
        return Objects.hash(getId(), getName(), getStatus(), getEnabled(), getValidFor());
    }

    @Override
    public int compareTo(ObjectValidationAbstract<ID> o) {
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
        name, status, enabled, validFor
    }
}