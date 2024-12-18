package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Configuration properties for scope settings.
 * This class holds configuration for scope-childdomain settings, including the channel name and enablement status.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ScopeProperties {

    /**
     * The name of the channel used for scope operations.
     * Default value is "scope".
     */
    private String channel = "scope";

    /**
     * Indicates whether the scope feature is enabled.
     * Default value is false.
     */
    private boolean enabled = false;
}
