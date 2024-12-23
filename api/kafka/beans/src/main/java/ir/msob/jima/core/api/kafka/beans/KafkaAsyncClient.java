package ir.msob.jima.core.api.kafka.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.channel.BaseChannelMessage;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This service class, KafkaAsyncClient, implements the BaseAsyncClient interface and is responsible for sending messages asynchronously to Kafka.
 */
@Service
@RequiredArgsConstructor
public class KafkaAsyncClient implements BaseAsyncClient {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Send a BaseChannelMessage to a Kafka channel.
     *
     * @param channelMessage The BaseChannelMessage to be sent.
     * @param channel        The Kafka channel to which the message should be sent.
     * @param user           An optional user associated with the message.
     */
    @MethodStats
    @SneakyThrows
    @Override
    public <USER extends BaseUser, DATA extends ModelType> void send(BaseChannelMessage<USER, DATA> channelMessage, String channel, USER user) {
        // Set the user information in the BaseChannelMessage.
        if (channelMessage.getUser() == null)
            channelMessage.setUser(user);

        sendMessage(channelMessage, channel);
    }

    @Override
    public <USER extends BaseUser, DATA extends ModelType> void send(BaseChannelMessage<USER, DATA> channelMessage, String channel) throws JsonProcessingException {
        sendMessage(channelMessage, channel);
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
    public <USER extends BaseUser> void send(Map<String, Object> channelMessage, String channel, USER user) {
        // Set the user information in the BaseChannelMessage.
        channelMessage.putIfAbsent(BaseChannelMessage.FN.user.name(), user);
        sendMessage(channelMessage, channel);
    }

    private void sendMessage(Object message, String channel) throws JsonProcessingException {
        // Serialize the Map to JSON and send it to the Kafka channel.
        String msg = objectMapper.writeValueAsString(message);
        kafkaTemplate.executeInTransaction(ops -> ops.send(channel, msg));
    }
}
