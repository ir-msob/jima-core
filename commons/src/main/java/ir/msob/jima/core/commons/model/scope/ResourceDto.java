package ir.msob.jima.core.commons.model.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.BaseType;
import lombok.*;

import java.util.Comparator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceDto implements BaseType, Comparable<ResourceDto> {
    private String value;
    private String type;

    public static ResourceDto clone(Resource resource) {
        return ResourceDto.builder()
                .value(resource.value())
                .type(resource.type())
                .build();
    }

    @Override
    public int compareTo(ResourceDto o) {
        if (this == o) {
            return 0;
        }

        Comparator<String> nullSafeComparator = Comparator.nullsFirst(String::compareTo);
        int valueComparison = nullSafeComparator.compare(this.value, o.value);
        if (valueComparison != 0) {
            return valueComparison;
        }

        return nullSafeComparator.compare(this.type, o.type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ResourceDto other)) {
            return false;
        }

        return this.compareTo(other) == 0;
    }
}
