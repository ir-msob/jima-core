package ir.msob.jima.core.commons.characteristic;

import ir.msob.jima.core.commons.shared.DataType;
import ir.msob.jima.core.commons.shared.keyvalue.KeyValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * The 'Characteristic' class represents a characteristic with a key, value, and data type.
 * It implements the 'Comparable' and 'Serializable' interfaces.
 * The class includes fields for key, value, and dataType, and getter and setter methods for these fields.
 * The 'key' field is annotated with '@NotBlank' to ensure that it is not blank.
 * The 'dataType' field is annotated with '@NotNull' to ensure that it is not null.
 * The 'compareTo' method compares this characteristic with another characteristic based on their keys.
 * If the keys are equal, it returns 0. If the keys are not equal, it compares their identity hash codes.
 * The 'equals' method checks whether this characteristic is equal to another object based on their keys.
 * The 'hashCode' method returns the hash code of this characteristic based on its fields.
 * The 'FN' enum represents the field names of the class.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Characteristic extends KeyValue<String, Serializable> {

    /**
     * The data type of the characteristic.
     */
    @NotNull
    private DataType dataType;

    @Override
    public void setKey(@NotBlank String key) {
        super.setKey(key);
    }

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

        if (o instanceof Characteristic that)
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
     * The 'FN' enum represents the field names of the class.
     */
    public enum FN {
        key, value, dataType
    }
}