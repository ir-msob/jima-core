package ir.msob.jima.core.api.kafka.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.model.channel.ChannelInfoAbstract;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * This service class, KafkaAsyncClient, implements the BaseAsyncClient interface and is responsible for sending messages asynchronously to Kafka.
 */
@Service
@RequiredArgsConstructor
public class KafkaAsyncClient implements BaseAsyncClient {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Send a ChannelMessage to a Kafka channel.
     *
     * @param channelMessage The ChannelMessage to be sent.
     * @param channel        The Kafka channel to which the message should be sent.
     * @param user           An optional user associated with the message.
     */
    @MethodStats
    @SneakyThrows
    @Override
    public <USER extends BaseUser, DATA extends ModelType> void send(ChannelMessage<USER, DATA> channelMessage, String channel, Optional<USER> user) {
        // Set the user information in the ChannelMessage.
        if (channelMessage.getUser() == null)
            channelMessage.setUser(user.orElse(null));

        // Serialize the ChannelMessage to JSON and send it to the Kafka channel.
        String msg = objectMapper.writeValueAsString(channelMessage);
        kafkaTemplate.executeInTransaction(ops -> ops.send(channel, msg));
    }

    public <USER extends BaseUser, DATA extends ModelType> void send2(ChannelMessage<USER, DATA> channelMessage, String channel, Optional<USER> user) throws JsonProcessingException {
        // Set the user information in the ChannelMessage.
        if (channelMessage.getUser() == null)
            channelMessage.setUser(user.orElse(null));

        // Serialize the ChannelMessage to JSON and send it to the Kafka channel.
        String msg = objectMapper.writeValueAsString(channelMessage);
        kafkaTemplate.executeInTransaction(ops -> ops.send(channel, msg));
    }

    /**
     * Send a Map of channelMessage to a Kafka channel.
     *
     * @param channelMessage The Map representing the message data.
     * @param channel        The Kafka channel to which the message should be sent.
     * @param user           An optional user associated with the message.
     */
    @MethodStats
    @SneakyThrows
    @Override
    public <USER extends BaseUser> void send(Map<String, Object> channelMessage, String channel, Optional<USER> user) {
        // Set the user information in the ChannelMessage.
        if (channelMessage.get(ChannelInfoAbstract.FN.user.name()) == null)
            user.ifPresent(u -> channelMessage.put(ChannelInfoAbstract.FN.user.name(), u));

        // Serialize the Map to JSON and send it to the Kafka channel.
        String msg = objectMapper.writeValueAsString(channelMessage);
        kafkaTemplate.executeInTransaction(ops -> ops.send(channel, msg));
    }
}
