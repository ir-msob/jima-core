package ir.msob.jima.core.ral.kafka.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.kafka.KafkaContainer;

@TestConfiguration
public class TestBeanConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * Registers dynamic properties for the Kafka container.
     *
     * @param kafkaContainer The Kafka container instance.
     * @return The DynamicPropertyRegistrar bean.
     */
    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(KafkaContainer kafkaContainer) {
        return registry -> {
            KafkaContainerConfiguration.registry(registry, kafkaContainer);
        };
    }
}
