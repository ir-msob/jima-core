package ir.msob.jima.core.ral.oracle.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.OracleContainer;

@TestConfiguration
public class TestBeanConfiguration {
    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(OracleContainer container) {
        return registry -> {
            OracleContainerConfiguration.registry(registry, container);
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
