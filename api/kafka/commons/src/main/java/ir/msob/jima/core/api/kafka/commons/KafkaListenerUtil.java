package ir.msob.jima.core.api.kafka.commons;

import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.function.Consumer;

/**
 * Utility class for creating and managing Kafka listeners in a reusable and standardized way.
 * <p>
 * Provides methods to create container properties, attach message listeners, and start Kafka containers.
 * All Kafka listeners in domain services should use this utility to keep business logic clean.
 */
public final class KafkaListenerUtil {

    private static final Logger log = LoggerFactory.getLogger(KafkaListenerUtil.class);

    private KafkaListenerUtil() {
        // Prevent instantiation
    }

    /**
     * Creates ContainerProperties for a Kafka listener.
     *
     * @param channel The Kafka topic or channel to listen to.
     * @param groupId The consumer group ID for this listener.
     * @return Configured ContainerProperties.
     */
    private static ContainerProperties createContainerProperties(String channel, String groupId) {
        log.debug("Creating ContainerProperties for channel '{}' with groupId '{}'", channel, groupId);
        ContainerProperties containerProperties = new ContainerProperties(channel);
        containerProperties.setGroupId(groupId);
        return containerProperties;
    }

    /**
     * Starts a Kafka listener container using the given container properties and consumer factory.
     *
     * @param kafkaConsumerFactory The Kafka consumer factory.
     * @param containerProperties  The container properties to configure the listener.
     * @param channel              The Kafka channel to listen to.
     */
    private static void startContainer(ConsumerFactory<String, String> kafkaConsumerFactory,
                                       ContainerProperties containerProperties,
                                       String channel) {
        log.info("Starting Kafka listener container for channel '{}'", channel);
        KafkaMessageListenerContainer<String, String> container =
                new KafkaMessageListenerContainer<>(kafkaConsumerFactory, containerProperties);
        container.setBeanName(channel);
        container.start();
        log.info("Kafka listener container for channel '{}' started successfully", channel);
    }

    /**
     * Starts a Kafka listener with a message handler.
     *
     * @param kafkaConsumerFactory The Kafka consumer factory.
     * @param channel              The Kafka topic or channel to listen to.
     * @param groupId              The consumer group ID for this listener.
     * @param messageHandler       The callback to handle incoming messages.
     */
    public static void startListener(ConsumerFactory<String, String> kafkaConsumerFactory,
                                     String channel,
                                     String groupId,
                                     Consumer<String> messageHandler) {

        log.info("Initializing Kafka listener for channel '{}', groupId '{}'", channel, groupId);

        ContainerProperties containerProperties = createContainerProperties(channel, groupId);

        // Attach message listener
        containerProperties.setMessageListener((MessageListener<String, String>) record -> {
            log.debug("Received message from channel '{}': {}", channel, record.value());
            messageHandler.accept(record.value());
        });

        startContainer(kafkaConsumerFactory, containerProperties, channel);
    }
}
