package ir.msob.jima.core.commons.shared.timeperiod;

import lombok.*;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * This class represents a time period with a start and end instant.
 * It implements Serializable interface for the object to be converted into a byte stream.
 * It provides methods to get and set the start and end instants.
 * It also overrides equals and hashCode methods from the Object class.
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimePeriod implements Serializable {
    /**
     * The start instant of the time period.
     */
    private Instant startDate;

    /**
     * The end instant of the time period.
     */
    private Instant endDate;

    /**
     * Checks if the TimePeriod is valid based on the validity period.
     *
     * @return true if the TimePeriod is valid, false if it is not valid,
     * and null if the validity period is not set.
     */
    public @Nullable Boolean isValid() {
        if (startDate == null && endDate == null) return null;
        else if (startDate == null) return Instant.now().isBefore(endDate);
        else if (endDate == null) return Instant.now().isAfter(startDate);
        else return Instant.now().isAfter(startDate) && Instant.now().isBefore(endDate);
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

        if (o instanceof TimePeriod that) {
            return Objects.equals(this.getStartDate(), that.getStartDate())
                    && Objects.equals(this.getEndDate(), that.getEndDate());
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
        return Objects.hash(startDate, endDate);
    }

    /**
     * Enum representing the field names of the TimePeriod class.
     */
    public enum FN {
        startDate, endDate
    }
}