package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

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
     * Connection timeout.
     * Default value is 30000 ms (30 seconds).
     */
    private Duration connectionTimeout = Duration.ofSeconds(30);

    /**
     * Request timeout.
     * Default value is 30000 ms (30 seconds).
     */
    private Duration requestTimeout = Duration.ofSeconds(30);

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
     * Keep-alive duration.
     * Default value is 300000 ms (5 minutes).
     */
    private Duration keepAliveDuration = Duration.ofSeconds(30);

}