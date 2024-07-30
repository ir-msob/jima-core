package ir.msob.jima.core.commons.model.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.ResourceType;
import ir.msob.jima.core.commons.model.dto.BaseType;
import lombok.*;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceDto implements BaseType, Comparable<ResourceDto> {
    private String value;
    private ResourceType type;
    private SortedSet<ScopeDto> scopes = new TreeSet<>();

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

        Comparator<ResourceType> nullSafeComparatorResourceType = Comparator.nullsFirst(ResourceType::compareTo);
        return nullSafeComparatorResourceType.compare(this.type, o.type);
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
