package ir.msob.jima.core.ral.kafka.test;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestApplication.class, KafkaContainerConfiguration.class})
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
