package ir.msob.jima.core.ral.postgresql.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class TestBeanConfiguration {
    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(PostgreSQLContainer<?> container) {
        return registry -> {
            PostgreSQLContainerConfiguration.registry(registry, container);
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
