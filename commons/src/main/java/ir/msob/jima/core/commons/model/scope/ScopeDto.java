package ir.msob.jima.core.commons.model.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.BaseType;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScopeDto implements BaseType, Comparable<ScopeDto> {
    private String scope;

    public static ScopeDto clone(Scope scope) {
        return ScopeDto.builder()
                .scope(scope.value())
                .build();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScopeDto scopeDto = (ScopeDto) o;
        return Objects.equals(scope, scopeDto.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scope);
    }
}