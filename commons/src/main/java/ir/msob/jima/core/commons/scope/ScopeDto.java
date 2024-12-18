package ir.msob.jima.core.commons.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.BaseType;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;

/**
 * The {@code ScopeDto} class is a Data Transfer Object (DTO) that represents a scope
 * with associated string values for element and operation. It is used to transfer
 * data between different layers of the application, particularly in scenarios involving
 * scope management and operations.
 *
 * <p>This class implements the {@link Comparable} interface, allowing instances of
 * {@code ScopeDto} to be compared based on their element and operation values.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *     <li>Provides a builder pattern for easy instantiation.</li>
 *     <li>Includes methods for cloning from a {@link Scope} object.</li>
 *     <li>Overrides {@code equals}, {@code hashCode}, and {@code compareTo} methods
 *     for proper comparison and hashing behavior.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * ScopeDto scopeDto = ScopeDto.builder()
 *     .element("exampleElement")
 *     .operation("exampleOperation")
 *     .build();
 * }
 * </pre>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScopeDto implements BaseType, Comparable<ScopeDto> {
    private String element;
    private String operation;

    /**
     * Creates a clone of the given {@link Scope} as a {@link ScopeDto}.
     *
     * @param scope the scope to clone
     * @return a new instance of {@code ScopeDto} with the same values as the given scope
     */
    public static ScopeDto clone(Scope scope) {
        return ScopeDto.builder()
                .element(scope.element())
                .operation(scope.operation())
                .build();
    }

    /**
     * Compares this {@code ScopeDto} with another for order.
     * Comparison is based on the element field, with nulls considered less than non-nulls.
     *
     * @param o the {@code ScopeDto} to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object
     */
    @Override
    public int compareTo(ScopeDto o) {
        if (this == o) {
            return 0;
        }

        if (o == null) {
            return 1;
        }

        // Compare by element
        int elementComparison = Comparator.nullsFirst(String::compareTo)
                .compare(this.element, o.element);

        // If elements are equal, compare by operation
        return (elementComparison != 0) ? elementComparison :
                Comparator.nullsFirst(String::compareTo).compare(this.operation, o.operation);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Equality is based on the element and operation fields.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScopeDto scopeDto = (ScopeDto) o;
        return Objects.equals(element, scopeDto.element) &&
                Objects.equals(operation, scopeDto.operation);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(element, operation);
    }
}