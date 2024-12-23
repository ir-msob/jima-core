package ir.msob.jima.core.commons.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.channel.message.*;
import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;
import lombok.SneakyThrows;

import java.io.Serializable;

/**
 * The 'BaseChannelTypeReference' interface defines the type references for various types of channel messages.
 * It is parameterized with four types 'ID' that extends 'Comparable' and 'Serializable', 'USER' that extends 'BaseUser', 'DTO' that extends 'BaseDto', and 'C' that extends 'BaseCriteria'.
 * The interface includes methods to get the type references for 'ModelType', 'CriteriaMessage', 'PageableMessage', 'JsonPatchMessage', 'DtoMessage', and 'DtosMessage'.
 *
 * @param <ID>   The type of ID.
 * @param <USER> The type of user.
 * @param <DTO>  The type of DTO.
 * @param <C>    The type of criteria.
 */
public interface BaseChannelTypeReference<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> {
    /**
     * Get the object mapper for JSON serialization and deserialization.
     *
     * @return The ObjectMapper instance.
     */
    ObjectMapper getObjectMapper();

    /**
     * Gets the type reference for 'ModelType'.
     *
     * @return The type reference for 'ModelType'.
     */
    TypeReference<BaseChannelMessage<USER, ModelType>> getModelTypeReferenceType();

    /**
     * Gets the type reference for 'CriteriaMessage'.
     *
     * @return The type reference for 'CriteriaMessage'.
     */
    TypeReference<BaseChannelMessage<USER, CriteriaMessage<ID, C>>> getCriteriaReferenceType();

    /**
     * Gets the type reference for 'PageableMessage'.
     *
     * @return The type reference for 'PageableMessage'.
     */
    TypeReference<BaseChannelMessage<USER, PageableMessage<ID, C>>> getCriteriaPageReferenceType();

    /**
     * Gets the type reference for 'PageMessage'.
     *
     * @return The type reference for 'PageMessage'.
     */
    TypeReference<BaseChannelMessage<USER, PageMessage<ID, DTO>>> getPageReferenceType();

    /**
     * Gets the type reference for 'JsonPatchMessage'.
     *
     * @return The type reference for 'JsonPatchMessage'.
     */
    TypeReference<BaseChannelMessage<USER, JsonPatchMessage<ID, C>>> getEditReferenceType();

    /**
     * Gets the type reference for 'DtoMessage'.
     *
     * @return The type reference for 'DtoMessage'.
     */
    TypeReference<BaseChannelMessage<USER, DtoMessage<ID, DTO>>> getDtoReferenceType();

    /**
     * Gets the type reference for 'DtosMessage'.
     *
     * @return The type reference for 'DtosMessage'.
     */
    TypeReference<BaseChannelMessage<USER, DtosMessage<ID, DTO>>> getDtosReferenceType();

    /**
     * Gets the type reference for 'IdMessage'.
     *
     * @return The type reference for 'IdMessage'.
     */
    TypeReference<BaseChannelMessage<USER, IdMessage<ID>>> getIdReferenceType();

    /**
     * Gets the type reference for 'IdsMessage'.
     *
     * @return The type reference for 'IdsMessage'.
     */
    TypeReference<BaseChannelMessage<USER, IdsMessage<ID>>> getIdsReferenceType();

    /**
     * Gets the type reference for 'IdJsonPatchMessage'.
     *
     * @return The type reference for 'IdJsonPatchMessage'.
     */
    TypeReference<BaseChannelMessage<USER, IdJsonPatchMessage<ID>>> getIdJsonPatchReferenceType();

    /**
     * Gets the type reference for 'LongMessage'.
     *
     * @return The type reference for 'LongMessage'.
     */
    TypeReference<BaseChannelMessage<USER, LongMessage>> getLongReferenceType();


    @SneakyThrows
    default <T> T cast(String s, TypeReference<T> typeReference) {
        return getObjectMapper().readValue(s, typeReference);
    }
}