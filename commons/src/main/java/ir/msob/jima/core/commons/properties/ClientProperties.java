package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ClientProperties configuration for HTTP client and RSocket connections.
 * Provides separate retry configurations for requests and connections.
 */
@Setter
@Getter
@NoArgsConstructor
public class ClientProperties {

    /**
     * Retry configuration for HTTP requests or RSocket requests.
     * Typically used for transient failures in individual requests.
     */
    private RetryProperties retryRequest = new RetryProperties();

    /**
     * Retry configuration for connection establishment.
     * Typically used for connection failures, network issues, etc.
     */
    private RetryProperties retryConnection = new RetryProperties();

    /**
     * Connection timeout in milliseconds.
     * Default value is 30000 ms (30 seconds).
     */
    private long connectionTimeout = 30000;

    /**
     * Response timeout in milliseconds.
     * Default value is 60000 ms (60 seconds).
     */
    private long responseTimeout = 60000;

    /**
     * Maximum number of connections in pool.
     * Default value is 100.
     */
    private int maxConnections = 100;

    /**
     * Whether to enable keep-alive for connections.
     * Default value is true.
     */
    private boolean keepAlive = true;

    /**
     * Keep-alive duration in milliseconds.
     * Default value is 300000 ms (5 minutes).
     */
    private long keepAliveDuration = 300000;
}