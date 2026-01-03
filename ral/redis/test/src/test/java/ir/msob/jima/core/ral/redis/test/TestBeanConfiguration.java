package ir.msob.jima.core.ral.redis.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;

@TestConfiguration
public class TestBeanConfiguration {
    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(RedisContainer container) {
        return registry -> RedisContainerConfiguration.registry(registry, container);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
