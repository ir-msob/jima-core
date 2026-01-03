package ir.msob.jima.core.api.kafka.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.jspecify.annotations.NonNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This service class, KafkaAsyncClient, implements the BaseAsyncClient interface and is responsible for sending messages asynchronously to Kafka.
 */
@Service
@RequiredArgsConstructor
public class KafkaAsyncClient implements BaseAsyncClient {
    private final KafkaTemplate<@NonNull String, @NonNull String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Send a ChannelMessage to a Kafka channel.
     *
     * @param channel        The Kafka channel to which the message should be sent.
     * @param channelMessage The ChannelMessage to be sent.
     * @param user           An optional user associated with the message.
     */
    @MethodStats
    @SneakyThrows
    @Override
    public <USER extends BaseUser, DATA extends ModelType> void send(String channel, ChannelMessage<USER, DATA> channelMessage, USER user) {
        // Set the user information in the ChannelMessage.
        if (channelMessage.getUser() == null)
            channelMessage.setUser(user);

        sendMessage(channel, channelMessage.getKey(), channelMessage);
    }

    @Override
    public <USER extends BaseUser, DATA extends ModelType> void send(String channel, ChannelMessage<USER, DATA> channelMessage) throws JsonProcessingException {
        sendMessage(channel, channelMessage.getKey(), channelMessage);
    }

    /**
     * Send a Map of channelMessage to a Kafka channel.
     *
     * @param channel        The Kafka channel to which the message should be sent.
     * @param channelMessage The Map representing the message data.
     * @param user           An optional user associated with the message.
     */
    @MethodStats
    @SneakyThrows
    @Override
    public <USER extends BaseUser> void send(String channel, Map<String, Object> channelMessage, USER user) {
        // Set the user information in the ChannelMessage.
        channelMessage.putIfAbsent(ChannelMessage.FN.user.name(), user);
        String key = channelMessage.get("key") == null ? null : String.valueOf(channelMessage.get("key"));
        sendMessage(channel, key, channelMessage);
    }

    private void sendMessage(String channel, String key, Object message) throws JsonProcessingException {
        // Serialize the Map to JSON and send it to the Kafka channel.
        String msg = objectMapper.writeValueAsString(message);
        if (Strings.isNotBlank(key)) {
            kafkaTemplate.send(channel, key, msg);
        } else {
            kafkaTemplate.send(channel, msg);
        }
    }

}
