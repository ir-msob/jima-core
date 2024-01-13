package ir.msob.jima.core.api.kafka.commons;

import ir.msob.jima.core.commons.listener.BaseListener;
import ir.msob.jima.core.commons.security.BaseUser;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.io.Serializable;

/**
 * This interface defines methods for creating and managing Kafka message listener containers.
 * Implementing classes should provide concrete implementations for these methods.
 *
 * @param <ID>   The type of ID.
 * @param <USER> The type of BaseUser.
 */
public interface BaseKafkaListener<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>>
        extends BaseListener<ID, USER> {

    /**
     * Gets the Kafka consumer group ID for this listener.
     *
     * @return The Kafka consumer group ID.
     */
    String getGroupId();

    /**
     * Creates a ContainerProperties object for the specified Kafka channel.
     *
     * @param channel The Kafka channel to configure ContainerProperties for.
     * @return A ContainerProperties object with the specified settings.
     */
    default ContainerProperties createKafkaContainerProperties(String channel) {
        ContainerProperties containerProperties = new ContainerProperties(channel);
        containerProperties.setGroupId(getGroupId());
        return containerProperties;
    }

    /**
     * Starts a Kafka message listener container with the specified ContainerProperties for a given channel.
     *
     * @param containerProperties The ContainerProperties to configure the listener container.
     * @param channel             The Kafka channel to listen to.
     */
    default void startKafkaContainer(ContainerProperties containerProperties, String channel) {
        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(getKafkaConsumerFactory(), containerProperties);
        container.setBeanName(channel);
        container.start();
    }

    /**
     * Gets the Kafka ConsumerFactory to be used for creating Kafka message listener containers.
     *
     * @return The Kafka ConsumerFactory.
     */
    ConsumerFactory<String, String> getKafkaConsumerFactory();

}
