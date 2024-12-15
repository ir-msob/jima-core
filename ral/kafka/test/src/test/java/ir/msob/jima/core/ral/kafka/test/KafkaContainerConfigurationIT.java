package ir.msob.jima.core.ral.kafka.test;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestMicroserviceApplication.class, KafkaContainerConfiguration.class})
@ContextConfiguration
@Testcontainers
@CommonsLog
class KafkaContainerConfigurationIT {

    @Autowired
    KafkaContainer container;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String consumerServers;
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String producerServers;

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

    @Test
    @DisplayName("Container is running after initialization")
    void containerIsRunningAfterInitialization() {
        assertTrue(container.isRunning(), "Container should be running after initialization");
    }


    @Test
    @DisplayName("Properties are set correctly")
    void testContainerProperties() {
        String containerBootstrapServers = container.getBootstrapServers();
        assertEquals(containerBootstrapServers, bootstrapServers);
        assertEquals(containerBootstrapServers, consumerServers);
        assertEquals(containerBootstrapServers, producerServers);
    }

}
