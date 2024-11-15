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
 * This class provides the configuration for setting up a Kafka container for testing purposes.
 * It is annotated with @TestConfiguration to indicate that it is a source of bean definitions.
 * The proxyBeanMethods attribute is set to false to optimize runtime bean creation.
 */
@TestConfiguration(proxyBeanMethods = false)
public class KafkaContainerConfiguration {

    /**
     * This method creates a KafkaContainer bean for testing purposes.
     * It uses the DynamicPropertyRegistry to dynamically register properties for the Kafka container.
     * The properties include the bootstrap servers for Kafka consumer and producer.
     * The JimaProperties object is used to get the Docker image name and cluster ID for the Kafka container.
     * If the cluster ID is not blank, it is set to the Kafka container.
     * The @ServiceConnection annotation is used to indicate that this bean is used for establishing a connection to a service.
     *
     * @param registry       The DynamicPropertyRegistry used to dynamically register properties for the Kafka container.
     * @param jimaProperties The JimaProperties object used to get the Docker image name and cluster ID for the Kafka container.
     * @return The created KafkaContainer bean.
     */
    @Bean
    @ServiceConnection
    public KafkaContainer kafkaContainer(DynamicPropertyRegistry registry, JimaProperties jimaProperties) {
        KafkaContainer container = new KafkaContainer(DockerImageName.parse(jimaProperties.getTestContainer().getKafka().getImage()));
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getKafka().getClusterId())) {
            container.withClusterId(jimaProperties.getTestContainer().getKafka().getClusterId());
        }
        container.withReuse(jimaProperties.getTestContainer().getKafka().isReuse());
        registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", container::getBootstrapServers);
        registry.add("spring.kafka.producer.bootstrap-servers", container::getBootstrapServers);
        return container;
    }
}