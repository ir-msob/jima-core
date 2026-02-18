package ir.msob.jima.core.ral.redisson.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration class for Redis properties embeddeddomain to locking operations.
 * <p>
 * This class is used to configure the Redis server host, port, and URL for
 * locking operations, such as distributed locks using Redisson.
 */
@Component("lockRedisDatasourceProperties")
@ConfigurationProperties(prefix = "spring.data.redis")
@Setter
@Getter
public class LockRedisDatasourceProperties {

    /**
     * Get the host of the Redis server.
     *
     */
    private String host;

    /**
     * Get the port of the Redis server.
     *
     */
    private int port;

    /**
     * Get the URL of the Redis server.
     *
     */
    private String url;
}

