package ir.msob.jima.core.commons.resource.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.channel.BaseChannelMessage;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;

import java.io.Serializable;

/**
 * The 'BaseListener' interface defines methods for creating and managing Kafka message listener containers.
 * It includes methods for getting the class type for the identifier and user, getting the ObjectMapper, UserService, and AsyncClient instances, retrieving a user based on the provided authentication token, and preparing a BaseChannelMessage by copying and modifying properties of another BaseChannelMessage.
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
     * Prepares a BaseChannelMessage by copying and modifying properties of another BaseChannelMessage.
     *
     * @param channelMessageReq The source BaseChannelMessage.
     * @param data              The new data to set in the resulting BaseChannelMessage.
     * @param status            The new status to set in the resulting BaseChannelMessage.
     * @param user              An optional user to associate with the resulting BaseChannelMessage.
     * @param <DATA_REQ>        The type of data in the source BaseChannelMessage.
     * @param <DATA>            The type of data in the resulting BaseChannelMessage.
     * @return A new BaseChannelMessage with modified properties.
     */
    default <DATA_REQ extends ModelType, DATA extends ModelType> BaseChannelMessage<USER, DATA> prepareChannelMessage(BaseChannelMessage<USER, DATA_REQ> channelMessageReq, DATA data, Integer status, USER user) {
        return BaseChannelMessage
                .<USER, DATA>builder()
                .data(data)
                .channel(null)
                .metadata(channelMessageReq.getMetadata())
                .status(status)
                .build();
    }
}