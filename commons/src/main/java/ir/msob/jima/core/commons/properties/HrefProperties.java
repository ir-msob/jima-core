package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Configuration properties for Href settings.
 * This class holds the base URL configuration used in the application.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class HrefProperties {

    /**
     * The base URL used in the application.
     * Default value is "<a href="http://localhost:8080">...</a>".
     */
    private String baseUrl = "http://localhost:8080";
}
