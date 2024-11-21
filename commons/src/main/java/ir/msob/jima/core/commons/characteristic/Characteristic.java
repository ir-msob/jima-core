package ir.msob.jima.core.commons.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseIdModelAbstract;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * The 'Characteristic' class represents a characteristic with a key, value, and data type.
 * It extends the 'BaseIdModelAbstract' class and implements the 'Comparable' interface.
 * This class includes the following fields:
 * - {@code key}: A non-blank string representing the key of the characteristic.
 * - {@code value}: A serializable object representing the value of the characteristic.
 * - {@code dataType}: A non-blank string representing the data type of the characteristic.
 *
 * The class provides getter and setter methods for these fields, ensuring encapsulation.
 * It overrides the {@code equals}, {@code hashCode}, and {@code compareTo} methods to provide
 * proper equality checks, hash code generation, and comparison logic based on the key.
 *
 * The 'FN' enum represents the field names of the class.
 *
 * @param <ID> The type of the identifier, which must be comparable and serializable.
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Characteristic<ID extends Comparable<ID> & Serializable> extends BaseIdModelAbstract<ID> implements Comparable<Characteristic<ID>> {

    /**
     * A non-blank string representing the key of the characteristic.
     */
    @NotBlank
    private String key;

    /**
     * A serializable object representing the value of the characteristic.
     */
    @NotNull
    private Serializable value;

    /**
     * A non-blank string representing the data type of the characteristic.
     */
    @NotBlank
    private String dataType;

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

        if (o instanceof Characteristic<?> that)
            return Objects.equals(this.getKey(), that.getKey());

        return false;
    }

    /**
     * Returns the hash code of this characteristic based on its fields.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue(), dataType);
    }

    /**
     * Compares this characteristic with another characteristic based on their keys.
     *
     * @param o The other characteristic to compare with.
     * @return A negative integer, zero, or a positive integer as this characteristic is less than,
     *         equal to, or greater than the specified characteristic.
     */
    @Override
    public int compareTo(Characteristic<ID> o) {
        if (this == o) {
            return 0;
        }

        if (o != null && (this.getKey() != null && o.getKey() != null)) {
            return this.getKey().compareTo(o.getKey());

        }

        return Comparator
                .comparing(System::identityHashCode)
                .compare(this, o);
    }

    /**
     * The 'FN' enum represents the field names of the class.
     */
    public enum FN {
        key, value, dataType
    }
}