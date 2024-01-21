package ir.msob.jima.core.commons.model.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.BaseType;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScopeDto implements BaseType {
    private String scope;

    public static ScopeDto clone(Scope scope) {
        return ScopeDto.builder()
                .scope(scope.value())
                .build();
    }
}
