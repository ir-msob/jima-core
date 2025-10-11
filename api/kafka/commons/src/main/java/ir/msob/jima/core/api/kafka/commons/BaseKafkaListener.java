package ir.msob.jima.core.api.kafka.commons;

import ir.msob.jima.core.commons.resource.listener.BaseListener;
import ir.msob.jima.core.commons.security.BaseUser;
import org.springframework.kafka.core.ConsumerFactory;

import java.io.Serializable;

/**
 * This interface defines methods for creating and managing Kafka message listener containers.
 * Implementing classes should provide concrete implementations for these methods.
 *
 * @param <ID>   The type of ID.
 * @param <USER> The type of BaseUser.
 */
public interface BaseKafkaListener<ID extends Comparable<ID> & Serializable, USER extends BaseUser>
        extends BaseListener<ID, USER> {

    /**
     * Gets the Kafka consumer group ID for this listener.
     *
     * @return The Kafka consumer group ID.
     */
    String getGroupId();


    /**
     * Gets the Kafka ConsumerFactory to be used for creating Kafka message listener containers.
     *
     * @return The Kafka ConsumerFactory.
     */
    ConsumerFactory<String, String> getKafkaConsumerFactory();

}
