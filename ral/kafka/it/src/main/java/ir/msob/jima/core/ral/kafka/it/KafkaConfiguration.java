package ir.msob.jima.core.ral.kafka.it;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.util.backoff.ExponentialBackOff;

import java.util.Map;
import java.util.UUID;

/**
 * Configuration class for setting up Kafka consumers and producers.
 */
@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class KafkaConfiguration {
    private final KafkaProperties kafkaProperties;

    @Value("${spring.kafka.error-handler.initial-interval:30000}")
    private long initialInterval;
    @Value("${spring.kafka.error-handler.multiplier:2}")
    private double multiplier;
    @Value("${spring.kafka.error-handler.max-interval:30000}")
    private long maxInterval;
    @Value("${spring.kafka.error-handler.max-elapsed-time:100000}")
    private long maxElapsedTime;
    @Value("${spring.kafka.error-handler.dlt-suffix:.DLT}")
    private String dltSuffix;

    /**
     * Configures Kafka consumer properties.
     *
     * @return Map of Kafka consumer properties.
     */
    public Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();

        // Set additional Kafka consumer properties
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return properties;
    }

    /**
     * Configures Kafka producer properties.
     *
     * @return Map of Kafka producer properties.
     */
    public Map<String, Object> kafkaProducerProperties() {
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();

        // Set additional Kafka producer properties
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, UUID.randomUUID() + "-");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    /**
     * Creates a Kafka consumer factory.
     *
     * @return Kafka consumer factory.
     */
    @Bean
    public ConsumerFactory<String, String> kafkaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaConsumerProperties(), new StringDeserializer(), new StringDeserializer());
    }

    /**
     * Creates a Kafka producer factory.
     *
     * @return Kafka producer factory.
     */
    @Bean
    public ProducerFactory<String, String> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProducerProperties());
    }

    /**
     * Creates a Kafka listener container factory.
     *
     * @param consumerFactory         The Kafka consumer factory.
     * @param kafkaTemplate           The Kafka template.
     * @param kafkaTransactionManager The Kafka transaction manager.
     * @return Kafka listener container factory.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory,
            KafkaTemplate<String, String> kafkaTemplate,
            KafkaTransactionManager<String, String> kafkaTransactionManager) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setReplyTemplate(kafkaTemplate);
        factory.getContainerProperties().setTransactionManager(kafkaTransactionManager);

        // Error handler
        DeadLetterPublishingRecoverer deadLetterPublishingRecoverer = new DeadLetterPublishingRecoverer(kafkaTemplate, (r, e) -> new TopicPartition(r.topic() + dltSuffix, r.partition()));
        ExponentialBackOff exponentialBackOff = new ExponentialBackOff(initialInterval, multiplier);
        exponentialBackOff.setMaxInterval(maxInterval);
        exponentialBackOff.setMaxElapsedTime(maxElapsedTime);
        factory.setCommonErrorHandler(new DefaultErrorHandler(deadLetterPublishingRecoverer, exponentialBackOff));

        return factory;
    }

    /**
     * Creates a Kafka transaction manager.
     *
     * @param producerFactory The Kafka producer factory.
     * @return Kafka transaction manager.
     */
    @Bean
    public KafkaTransactionManager<String, String> kafkaTransactionManager(ProducerFactory<String, String> producerFactory) {
        KafkaTransactionManager<String, String> kafkaTransactionManager = new KafkaTransactionManager<>(producerFactory);
        kafkaTransactionManager.setTransactionSynchronization(AbstractPlatformTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
        return kafkaTransactionManager;
    }

    /**
     * Creates a Kafka template.
     *
     * @param kafkaProducerFactory The Kafka producer factory.
     * @return Kafka template.
     */
    @Bean
    @Primary
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> kafkaProducerFactory) {
        return new KafkaTemplate<>(kafkaProducerFactory);
    }
}
