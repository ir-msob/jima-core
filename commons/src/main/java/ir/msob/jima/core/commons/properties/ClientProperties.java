package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The `ClientProperties` class is a simple POJO (Plain Old Java Object) that holds client-related properties.
 * It uses Lombok annotations for automatic generation of getters, setters, a no-argument constructor, and a toString method.
 * The `retry` field is an instance of the nested `Retry` class, which holds retry-related properties.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class ClientProperties {
    /**
     * The `retry` field is an instance of the `RetryProperties` class.
     * This field holds retry-related properties for the client.
     */
    RetryProperties retryRequest = new RetryProperties();

    /**
     * The `retryConnection` field is an instance of the `RetryProperties` class.
     * This field holds retry-related properties for the client's connections.
     */
    RetryProperties retryConnection = new RetryProperties();
}