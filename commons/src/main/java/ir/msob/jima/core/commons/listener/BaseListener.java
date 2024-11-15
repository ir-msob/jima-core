package ir.msob.jima.core.commons.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;

/**
 * The 'BaseListener' interface defines methods for creating and managing Kafka message listener containers.
 * It includes methods for getting the class type for the identifier and user, getting the ObjectMapper, UserService, and AsyncClient instances, retrieving a user based on the provided authentication token, and preparing a ChannelMessage by copying and modifying properties of another ChannelMessage.
 * Classes that implement this interface should provide concrete implementations for these methods.
 *
 * @param <ID>   The type of ID.
 * @param <USER> The type of BaseUser.
 */
public interface BaseListener<ID extends Comparable<ID> & Serializable, USER extends BaseUser>
        extends BaseResource<ID, USER> {
    /**
     * Gets the ObjectMapper for serializing and deserializing messages.
     *
     * @return The ObjectMapper instance.
     */
    ObjectMapper getObjectMapper();

    /**
     * Gets the AsyncClient for sending asynchronous messages.
     *
     * @return The AsyncClient instance.
     */
    BaseAsyncClient getAsyncClient();

    /**
     * Retrieves a user based on the provided authentication token.
     *
     * @param token The authentication token to identify the user.
     * @return the user
     */
    default USER getUser(String token) {
        return getUserService().getUser(token);
    }

    /**
     * Prepares a ChannelMessage by copying and modifying properties of another ChannelMessage.
     *
     * @param channelMessageReq The source ChannelMessage.
     * @param data              The new data to set in the resulting ChannelMessage.
     * @param status            The new status to set in the resulting ChannelMessage.
     * @param user              An optional user to associate with the resulting ChannelMessage.
     * @param <DATA_REQ>        The type of data in the source ChannelMessage.
     * @param <DATA>            The type of data in the resulting ChannelMessage.
     * @return A new ChannelMessage with modified properties.
     */
    default <DATA_REQ extends ModelType, DATA extends ModelType> ChannelMessage<USER, DATA> prepareChannelMessage(ChannelMessage<USER, DATA_REQ> channelMessageReq, DATA data, Integer status, USER user) {
        ChannelMessage<USER, DATA> channelMessage = new ChannelMessage<>();
        channelMessage.setData(data);
        channelMessage.setCallback(null);
        channelMessage.setMetadata(channelMessageReq.getMetadata());
        channelMessage.setStatus(status);
        return channelMessage;
    }
}