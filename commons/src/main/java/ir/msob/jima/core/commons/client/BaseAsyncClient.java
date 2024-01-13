package ir.msob.jima.core.commons.client;

import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * This interface provides a set of basic methods for sending messages to channels.
 */
public interface BaseAsyncClient {

    /**
     * This method sends a generic channel message.
     *
     * @param <ID>           The ID type
     * @param <USER>         The user type
     * @param <DATA>         The data type
     * @param channelMessage The channel message
     * @param channel        The channel name
     * @param user           Optional: the destination user
     */
    @MethodStats
    <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>, DATA extends ModelType> void send(ChannelMessage<ID, USER, DATA> channelMessage, String channel, Optional<USER> user);

    /**
     * This method sends a channel message of type Map.
     *
     * @param <ID>           The ID type
     * @param <USER>         The user type
     * @param channelMessage The channel message as a Map
     * @param channel        The channel name
     * @param user           Optional: the destination user
     */
    @MethodStats
    <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> void send(Map<String, Object> channelMessage, String channel, Optional<USER> user);

}
