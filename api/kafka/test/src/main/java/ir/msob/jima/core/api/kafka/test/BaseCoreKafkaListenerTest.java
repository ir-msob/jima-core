package ir.msob.jima.core.api.kafka.test;

import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.core.commons.model.dto.BaseDto;
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

public interface BaseCoreKafkaListenerTest<ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>>
        extends
        BaseCoreResourceTest<ID, USER, D, DTO, C> {

    KafkaTemplate<String, String> getKafkaTemplate();

    String getGroupId();

    Duration getSleepDuration();

    ConsumerFactory<String, String> getConsumerFactory();

    default void startListener(String channel, Assertable<String> assertable) {
        ContainerProperties containerProperties = new ContainerProperties(channel);
        containerProperties.setGroupId(getGroupId());

        containerProperties.setMessageListener((MessageListener<String, String>) dto -> assertable.assertThan(dto.value()));

        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(getConsumerFactory(), containerProperties);
        container.setBeanName(channel);
        container.start();
        try {
            Thread.sleep(getSleepDuration());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            container.stop();
        }
    }
}

