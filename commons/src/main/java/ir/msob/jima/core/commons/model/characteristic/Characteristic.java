package ir.msob.jima.core.commons.model.characteristic;

import ir.msob.jima.core.commons.model.DataType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Characteristic implements Comparable<Characteristic>, Serializable {
    @NotBlank
    private String key;
    private Serializable value;
    @NotNull
    private DataType dataType;
    private boolean array = false;

    @Override
    public int compareTo(Characteristic o) {
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

    @Override
    public int hashCode() {
        return Objects.hash(key, value, dataType, array);
    }

    public enum FN {
        key, value, dataType, array
    }
}
