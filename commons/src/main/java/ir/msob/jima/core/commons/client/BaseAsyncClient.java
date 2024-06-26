package ir.msob.jima.core.commons.client;

import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;

import java.util.Map;
import java.util.Optional;

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
     * @param channelMessage The channel message
     * @param channel        The channel name
     * @param user           Optional: the destination user
     */
    @MethodStats
    <USER extends BaseUser, DATA extends ModelType> void send(ChannelMessage<USER, DATA> channelMessage, String channel, Optional<USER> user);

    /**
     * This method sends a channel message of type Map.
     * It is annotated with '@MethodStats', which means that statistics will be collected for this method.
     *
     * @param <USER>         The user type
     * @param channelMessage The channel message as a Map
     * @param channel        The channel name
     * @param user           Optional: the destination user
     */
    @MethodStats
    <USER extends BaseUser> void send(Map<String, Object> channelMessage, String channel, Optional<USER> user);

}