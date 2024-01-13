package ir.msob.jima.core.commons.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;
import java.util.Optional;

/**
 * This interface defines methods for creating and managing Kafka message listener containers.
 * Implementing classes should provide concrete implementations for these methods.
 *
 * @param <ID>   The type of ID.
 * @param <USER> The type of BaseUser.
 */
public interface BaseListener<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> {
    /**
     * Get the class type for the identifier (e.g., entity primary key).
     *
     * @return The class type for the identifier.
     */
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(this.getClass(), BaseResource.class, 0);
    }

    /**
     * Get the class type representing a user, typically derived from 'BaseUser'.
     *
     * @return The class type for the user.
     */
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(this.getClass(), BaseResource.class, 1);
    }

    /**
     * Gets the ObjectMapper for serializing and deserializing messages.
     *
     * @return The ObjectMapper instance.
     */
    ObjectMapper getObjectMapper();

    /**
     * Gets the UserService for retrieving user information.
     *
     * @return The UserService instance.
     */
    BaseUserService getUserService();

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
     * @return An optional containing the user if found, or an empty optional if not found.
     */
    default Optional<USER> getUser(String token) {
        return getUserService().getUser(Optional.ofNullable(token));
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
    default <DATA_REQ extends ModelType, DATA extends ModelType> ChannelMessage<ID, USER, DATA> prepareChannelMessage(ChannelMessage<ID, USER, DATA_REQ> channelMessageReq, DATA data, Integer status, Optional<USER> user) {
        ChannelMessage<ID, USER, DATA> channelMessage = new ChannelMessage<>();
        channelMessage.setData(data);
        channelMessage.setCallback(null);
        channelMessage.setMetadata(channelMessageReq.getMetadata());
        channelMessage.setStatus(status);
        return channelMessage;
    }
}
