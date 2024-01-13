package ir.msob.jima.core.ral.redis.test;

import com.redis.testcontainers.RedisContainer;
import ir.msob.jima.core.test.testcontainer.BaseContainerConfiguration;
import ir.msob.jima.core.test.testcontainer.ContainerImageConstant;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * This class provides the configuration for setting up a Redis container for testing purposes.
 */
@TestConfiguration
public class RedisContainerConfiguration extends BaseContainerConfiguration {

    /**
     * The RedisContainer instance used to run the Redis container.
     */
    @Container
    public static final RedisContainer redisContainer = new RedisContainer(DockerImageName.parse(ContainerImageConstant.REDIS_IMAGE));

    /**
     * Configures dynamic properties for Redis host, port, and URL using Testcontainers.
     *
     * @param registry The DynamicPropertyRegistry used for registering dynamic properties.
     */
    @DynamicPropertySource
    public static void registry(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", redisContainer.getExposedPorts().stream().findFirst()::get);
        registry.add("spring.data.redis.url", redisContainer::getRedisURI);
    }

    /**
     * Gets the RedisContainer instance for this configuration.
     *
     * @return The RedisContainer instance.
     */
    @Override
    protected GenericContainer<?> getContainer() {
        return redisContainer;
    }
}
