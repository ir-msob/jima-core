package ir.msob.jima.core.commons.model.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.ResourceType;
import ir.msob.jima.core.commons.model.dto.BaseType;
import lombok.*;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Data Transfer Object (DTO) for resources.
 * Represents a resource with a value, type, and associated scopes.
 */
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
    @Builder.Default
    private SortedSet<ScopeDto> scopes = new TreeSet<>();

    /**
     * Creates a clone of the given {@link Resource} as a {@link ResourceDto}.
     *
     * @param resource the resource to clone
     * @return a new instance of ResourceDto with the same value and type as the given resource
     */
    public static ResourceDto clone(Resource resource) {
        return ResourceDto.builder()
                .value(resource.value())
                .type(resource.type())
                .build();
    }

    /**
     * Compares this ResourceDto with another for order.
     * Comparison is based on the value and type fields, with nulls considered less than non-nulls.
     *
     * @param o the ResourceDto to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
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

    /**
     * Indicates whether some other object is "equal to" this one.
     * Equality is based on the compareTo method.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
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
