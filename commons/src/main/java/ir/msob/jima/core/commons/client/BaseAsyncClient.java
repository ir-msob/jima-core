package ir.msob.jima.core.commons.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;

import java.util.Map;

/**
 * The 'BaseAsyncClient' interface provides a set of basic methods for sending messages to channels.
 * It is designed to be implemented by classes that need to send asynchronous messages.
 * The interface provides two methods for sending messages: one for sending generic channel messages and another for sending channel messages of type Map.
 */
public interface BaseAsyncClient {

    /**
     * This method sends a generic channel message.
     * It is annotated with '@MethodStats', which means that statistics will be collected for this method.
     *
     * @param <USER>         The user type
     * @param <DATA>         The data type
     * @param channel        The channel name
     * @param channelMessage The channel message
     * @param user           Optional: the destination user
     */
    @MethodStats
    <USER extends BaseUser, DATA extends ModelType> void send(String channel, ChannelMessage<USER, DATA> channelMessage, USER user);

    /**
     * This method sends a generic channel message.
     * It is annotated with '@MethodStats', which means that statistics will be collected for this method.
     *
     * @param <USER>         The user type
     * @param <DATA>         The data type
     * @param channel        The channel name
     * @param channelMessage The channel message
     */
    @MethodStats
    <USER extends BaseUser, DATA extends ModelType> void send(String channel, ChannelMessage<USER, DATA> channelMessage) throws JsonProcessingException;

    /**
     * This method sends a channel message of type Map.
     * It is annotated with '@MethodStats', which means that statistics will be collected for this method.
     *
     * @param <USER>         The user type
     * @param channel        The channel name
     * @param channelMessage The channel message as a Map
     * @param user           Optional: the destination user
     */
    @MethodStats
    <USER extends BaseUser> void send(String channel, Map<String, Object> channelMessage, USER user);

}