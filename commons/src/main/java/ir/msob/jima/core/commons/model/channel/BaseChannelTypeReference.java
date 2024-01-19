package ir.msob.jima.core.commons.model.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.msob.jima.core.commons.model.channel.message.*;
import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;

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
        USER extends BaseUser<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> {
    /**
     * Gets the type reference for 'ModelType'.
     *
     * @return The type reference for 'ModelType'.
     */
    TypeReference<ChannelMessage<ID, USER, ModelType>> getModelTypeReferenceType();

    /**
     * Gets the type reference for 'CriteriaMessage'.
     *
     * @return The type reference for 'CriteriaMessage'.
     */
    TypeReference<ChannelMessage<ID, USER, CriteriaMessage<ID, C>>> getCriteriaReferenceType();

    /**
     * Gets the type reference for 'PageableMessage'.
     *
     * @return The type reference for 'PageableMessage'.
     */
    TypeReference<ChannelMessage<ID, USER, PageableMessage<ID, C>>> getCriteriaPageReferenceType();

    /**
     * Gets the type reference for 'JsonPatchMessage'.
     *
     * @return The type reference for 'JsonPatchMessage'.
     */
    TypeReference<ChannelMessage<ID, USER, JsonPatchMessage<ID, C>>> getEditReferenceType();

    /**
     * Gets the type reference for 'DtoMessage'.
     *
     * @return The type reference for 'DtoMessage'.
     */
    TypeReference<ChannelMessage<ID, USER, DtoMessage<ID, DTO>>> getDtoReferenceType();

    /**
     * Gets the type reference for 'DtosMessage'.
     *
     * @return The type reference for 'DtosMessage'.
     */
    TypeReference<ChannelMessage<ID, USER, DtosMessage<ID, DTO>>> getDtosReferenceType();

    /**
     * Gets the type reference for 'IdMessage'.
     *
     * @return The type reference for 'IdMessage'.
     */
    TypeReference<ChannelMessage<ID, USER, IdMessage<ID>>> getIdReferenceType();

    /**
     * Gets the type reference for 'IdJsonPatchMessage'.
     *
     * @return The type reference for 'IdJsonPatchMessage'.
     */
    TypeReference<ChannelMessage<ID, USER, IdJsonPatchMessage<ID>>> getIdJsonPatchReferenceType();
}