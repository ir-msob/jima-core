package ir.msob.jima.core.ral.kafka.test;

import ir.msob.jima.core.test.testcontainer.BaseContainerConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static ir.msob.jima.core.test.testcontainer.ContainerImageConstant.KAFKA_IMAGE;

/**
 * Test configuration class for setting up a Kafka container for integration tests.
 */
@TestConfiguration
public class KafkaContainerConfiguration extends BaseContainerConfiguration {

    /**
     * The Kafka container instance for use in integration tests.
     */
    @Container
    private static final KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse(KAFKA_IMAGE));

    /**
     * Registers dynamic properties for configuring Kafka properties with the Kafka container's bootstrap servers.
     *
     * @param registry The dynamic property registry for adding properties.
     */
    @DynamicPropertySource
    public static void registry(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.producer.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    /**
     * Gets the Kafka container instance to be used in integration tests.
     *
     * @return The Kafka container instance.
     */
    @Override
    protected GenericContainer<?> getContainer() {
        return kafkaContainer;
    }
}
