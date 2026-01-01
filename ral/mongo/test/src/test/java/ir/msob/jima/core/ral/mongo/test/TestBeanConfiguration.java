package ir.msob.jima.core.ral.mongo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.ral.mongo.test.configuration.MongoContainerConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.mongodb.MongoDBContainer;

@TestConfiguration
public class TestBeanConfiguration {
    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(MongoDBContainer container) {
        return registry -> {
            MongoContainerConfiguration.registry(registry, container);
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
