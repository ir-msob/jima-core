package ir.msob.jima.core.api.kafka.test;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.test.Assertable;
import ir.msob.jima.core.test.BaseCoreResourceTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.io.Serializable;
import java.time.Duration;

/**
 * Base interface for Kafka listener tests.
 *
 * @param <ID>   the type of the identifier
 * @param <USER> the type of the user
 * @param <D>    the type of the domain
 * @param <DTO>  the type of the data transfer object
 * @param <C>    the type of the criteria
 */
public interface BaseCoreKafkaListenerTest<ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>>
        extends
        BaseCoreResourceTest<ID, USER, D, DTO, C> {

    /**
     * Gets the Kafka template for sending messages.
     *
     * @return the Kafka template
     */
    KafkaTemplate<String, String> getKafkaTemplate();

    /**
     * Gets the group ID for the Kafka consumer.
     *
     * @return the group ID
     */
    String getGroupId();

    /**
     * Gets the consumer factory for creating Kafka consumers.
     *
     * @return the consumer factory
     */
    ConsumerFactory<String, String> getConsumerFactory();

    /**
     * Starts a Kafka message listener on the specified channel.
     *
     * @param channel    the channel to listen to
     * @param assertable the assertable to validate received messages
     */
    default void startListener(String channel, Assertable<String> assertable) {
        ContainerProperties containerProperties = new ContainerProperties(channel);
        containerProperties.setGroupId(getGroupId());

        containerProperties.setMessageListener((MessageListener<String, String>) dto -> assertable.assertThan(dto.value()));

        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(getConsumerFactory(), containerProperties);
        container.setBeanName(channel);
        container.start();
        try {
            Thread.sleep(getJimaProperties().getTest().getKafka().getMessageWaitDuration());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            container.stop();
        }
    }
}

