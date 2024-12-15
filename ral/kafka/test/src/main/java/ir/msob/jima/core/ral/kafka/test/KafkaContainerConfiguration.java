package ir.msob.jima.core.ral.kafka.test;

import ir.msob.jima.core.beans.properties.JimaProperties;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * Configuration for setting up a Kafka container for testing purposes.
 */
@TestConfiguration(proxyBeanMethods = false)
public class KafkaContainerConfiguration {

    public static void registry(DynamicPropertyRegistry registry, KafkaContainer container) {
        registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", container::getBootstrapServers);
        registry.add("spring.kafka.producer.bootstrap-servers", container::getBootstrapServers);
    }

    /**
     * Creates a KafkaContainer bean for testing purposes.
     * The @ServiceConnection annotation is used to indicate that this bean is used for establishing a connection to a service.
     *
     * @param jimaProperties The JimaProperties object used to get the Docker image name and cluster ID for the Kafka container.
     * @return The created KafkaContainer bean.
     */
    @Bean
    @ServiceConnection
    public KafkaContainer kafkaContainer(JimaProperties jimaProperties) {
        KafkaContainer container = new KafkaContainer(DockerImageName.parse(jimaProperties.getTestContainer().getKafka().getImage()));
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getKafka().getClusterId())) {
            container.withClusterId(jimaProperties.getTestContainer().getKafka().getClusterId());
        }
        container.withReuse(jimaProperties.getTestContainer().getKafka().isReuse());
        return container;
    }

}
