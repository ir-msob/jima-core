package ir.msob.jima.core.commons.resource.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.channel.message.*;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;
import ir.msob.jima.core.commons.shared.PageDto;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.Collection;

/**
 * The 'BaseListener' interface defines methods for creating and managing Kafka message listener containers.
 * It includes methods for getting the class type for the identifier and user, getting the ObjectMapper, UserService, and AsyncClient instances, retrieving a user based on the provided authentication token, and preparing a ChannelMessage by copying and modifying properties of another ChannelMessage.
 * Classes that implement this interface should provide concrete implementations for these methods.
 *
 * @param <ID>   The type of ID.
 * @param <USER> The type of BaseUser.
 */
public interface BaseListener<ID extends Comparable<ID> & Serializable, USER extends BaseUser, DTO extends BaseDto<ID>, C extends BaseCriteria<ID>>
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
        return ChannelMessage
                .<USER, DATA>builder()
                .data(data)
                .channel(null)
                .metadata(channelMessageReq.getMetadata())
                .status(status)
                .build();
    }

    /**
     * Sends a callback with a collection of DTOs.
     *
     * @param message The original message.
     * @param dtos    The DTOs to send in the callback.
     * @param status  The status of the operation.
     * @param user    The user who initiated the operation.
     */
    @SneakyThrows
    default <DATA extends ModelType> void sendCallbackDtos(ChannelMessage<USER, DATA> message, Collection<DTO> dtos, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            DtosMessage<ID, DTO> data = new DtosMessage<>();
            data.setDtos(dtos);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }

    /**
     * Sends a callback with a single DTO.
     *
     * @param message The original message.
     * @param dto     The DTO to send in the callback.
     * @param status  The status of the operation.
     * @param user    The user who initiated the operation.
     */
    @SneakyThrows
    default <DATA extends ModelType> void sendCallbackDto(ChannelMessage<USER, DATA> message, DTO dto, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            DtoMessage<ID, DTO> data = new DtoMessage<>();
            data.setDto(dto);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }

    /**
     * Sends a callback with a collection of IDs.
     *
     * @param message The original message.
     * @param ids     The IDs to send in the callback.
     * @param status  The status of the operation.
     * @param user    The user who initiated the operation.
     */
    @SneakyThrows
    default <DATA extends ModelType> void sendCallbackIds(ChannelMessage<USER, DATA> message, Collection<ID> ids, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            IdsMessage<ID> data = new IdsMessage<>();
            data.setIds(ids);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }

    /**
     * Sends a callback with a single ID.
     *
     * @param message The original message.
     * @param id      The ID to send in the callback.
     * @param status  The status of the operation.
     * @param user    The user who initiated the operation.
     */
    @SneakyThrows
    default <DATA extends ModelType> void sendCallbackId(ChannelMessage<USER, DATA> message, ID id, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            IdMessage<ID> data = new IdMessage<>();
            data.setId(id);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }

    /**
     * Sends a callback with the count of all entities.
     *
     * @param message The original message.
     * @param count   The count of all entities.
     * @param status  The status of the operation.
     * @param user    The user who initiated the operation.
     */
    @SneakyThrows
    default void sendCallbackCountAll(ChannelMessage<USER, ModelType> message, Long count, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            LongMessage data = new LongMessage();
            data.setResult(count);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }

    /**
     * Sends a callback with the count of entities that match the criteria.
     *
     * @param message The original message.
     * @param count   The count of entities that match the criteria.
     * @param status  The status of the operation.
     * @param user    The user who initiated the operation.
     */
    @SneakyThrows
    default void sendCallbackCount(ChannelMessage<USER, CriteriaMessage<ID, C>> message, Long count, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            LongMessage data = new LongMessage();
            data.setResult(count);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }

    @SneakyThrows
    default void sendCallbackBoolean(ChannelMessage<USER, CriteriaMessage<ID, C>> message, Boolean result, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            BooleanMessage data = new BooleanMessage();
            data.setResult(result);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }

    /**
     * Sends a callback with a page of DTOs.
     *
     * @param message The original message.
     * @param page    The page of DTOs to send in the callback.
     * @param status  The status of the operation.
     * @param user    The user who initiated the operation.
     */
    @SneakyThrows
    default <DATA extends ModelType> void sendCallbackPage(ChannelMessage<USER, DATA> message, PageDto<DTO> page, Integer status, USER user) {
        if (!message.getCallbacks().isEmpty()) {
            PageMessage<ID, DTO> data = new PageMessage<>();
            data.setPage(page);
            for (ChannelMessage<USER, ? extends ModelType> callback : message.getCallbacks()) {
                getAsyncClient().send(callback.getChannel(), prepareChannelMessage(callback, data, status, user), user).subscribe();
            }
        }
    }
}