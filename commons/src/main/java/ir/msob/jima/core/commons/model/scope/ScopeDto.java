package ir.msob.jima.core.commons.model.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.BaseType;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for scopes.
 * Represents a scope with a single string value.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScopeDto implements BaseType, Comparable<ScopeDto> {
    private String scope;

    /**
     * Creates a clone of the given {@link Scope} as a {@link ScopeDto}.
     *
     * @param scope the scope to clone
     * @return a new instance of ScopeDto with the same value as the given scope
     */
    public static ScopeDto clone(Scope scope) {
        return ScopeDto.builder()
                .scope(scope.value())
                .build();
    }

    /**
     * Compares this ScopeDto with another for order.
     * Comparison is based on the scope field, with nulls considered less than non-nulls.
     *
     * @param o the ScopeDto to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(ScopeDto o) {
        if (this == o) {
            return 0;
        }

        if (o == null) {
            return 1;
        }

        String thisScope = Objects.requireNonNull(this.scope);
        String otherScope = Objects.requireNonNull(o.scope);

        return Comparator.comparing(String::valueOf, Comparator.nullsFirst(String::compareTo))
                .compare(thisScope, otherScope);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Equality is based on the scope field.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScopeDto scopeDto = (ScopeDto) o;
        return Objects.equals(scope, scopeDto.scope);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(scope);
    }
}