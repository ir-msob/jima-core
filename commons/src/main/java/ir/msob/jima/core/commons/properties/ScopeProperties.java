package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ScopeProperties {
    private String channel = "scope";
    private boolean enabled = false;
}
