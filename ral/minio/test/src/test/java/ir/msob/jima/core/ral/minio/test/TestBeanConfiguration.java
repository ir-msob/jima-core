package ir.msob.jima.core.ral.minio.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.MinIOContainer;

@TestConfiguration
public class TestBeanConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(MinIOContainer container) {
        return registry -> {
            MinIOContainerConfiguration.registry(registry, container);
        };
    }

}
