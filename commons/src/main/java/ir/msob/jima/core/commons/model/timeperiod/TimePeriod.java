package ir.msob.jima.core.commons.model.timeperiod;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimePeriod implements Serializable {
    private Instant start;
    private Instant end;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof TimePeriod that) {
            return Objects.equals(this.getStart(), that.getStart())
                    && Objects.equals(this.getEnd(), that.getEnd());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    public enum FN {
        start, end
    }
}
