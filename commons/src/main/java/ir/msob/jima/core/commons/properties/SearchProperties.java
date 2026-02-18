package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Configuration properties for search settings.
 * This class holds configuration for search-embeddeddomain settings, including the channel name.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SearchProperties {

    /**
     * The name of the channel used for search operations.
     * Default value is "search".
     */
    private String channel = "search";
}
