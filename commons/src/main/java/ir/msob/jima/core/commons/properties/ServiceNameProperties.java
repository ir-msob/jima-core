package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds mappings of logical service keys to microservice names.
 * Example usage in properties: jima.services.names.my-key = my-microservice
 */
@Setter
@Getter
@NoArgsConstructor
public class ServiceNameProperties {

    /**
     * Map of service key to microservice name.
     */
    private Map<String, String> names = new HashMap<>();
}




