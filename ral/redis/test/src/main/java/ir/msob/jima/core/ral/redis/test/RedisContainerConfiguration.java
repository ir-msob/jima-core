package ir.msob.jima.core.ral.redis.test;

import com.redis.testcontainers.RedisContainer;
import ir.msob.jima.core.beans.properties.JimaProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.utility.DockerImageName;

/**
 * This class provides the configuration for setting up a Redis container for testing purposes.
 * It is annotated with @TestConfiguration to indicate that it is a source of bean definitions.
 * The proxyBeanMethods attribute is set to false to optimize runtime bean creation.
 */
@TestConfiguration(proxyBeanMethods = false)
public class RedisContainerConfiguration {

    /**
     * This method creates a RedisContainer bean for testing purposes.
     * It uses the DynamicPropertyRegistry to dynamically register properties for the Redis container.
     * The properties include the host, port, and URI of the Redis container.
     * The JimaProperties object is used to get the Docker image name for the Redis container.
     * The @Bean annotation is used to indicate that this method produces a bean to be managed by the Spring container.
     *
     * @param registry       The DynamicPropertyRegistry used to dynamically register properties for the Redis container.
     * @param jimaProperties The JimaProperties object used to get the Docker image name for the Redis container.
     * @return The created RedisContainer bean.
     */
    @Bean
    public RedisContainer redisContainer(DynamicPropertyRegistry registry, JimaProperties jimaProperties) {
        RedisContainer container = new RedisContainer(DockerImageName.parse(jimaProperties.getTestContainer().getRedis().getImage()));
        container.withReuse(jimaProperties.getTestContainer().getRedis().isReuse());
        registry.add("spring.data.redis.host", container::getHost);
        registry.add("spring.data.redis.port", container.getExposedPorts().stream().findFirst()::get);
        registry.add("spring.data.redis.url", container::getRedisURI);
        return container;
    }
}