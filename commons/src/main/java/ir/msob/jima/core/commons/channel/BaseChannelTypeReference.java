package ir.msob.jima.core.commons.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.msob.jima.core.commons.channel.message.*;
import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.BaseTypeReference;
import ir.msob.jima.core.commons.shared.ModelType;

import java.io.Serializable;

/**
 * The {@code BaseChannelTypeReference} interface defines type references for various channel message types
 * used in message-based communication.
 * <p>
 * This interface is parameterized with:
 * </p>
 * <ul>
 *   <li>{@code ID} - the type of identifier, must be Comparable and Serializable</li>
 *   <li>{@code USER} - the type of user, must extend BaseUser</li>
 *   <li>{@code DTO} - the type of data transfer object, must extend BaseDto</li>
 *   <li>{@code C} - the type of criteria, must extend BaseCriteria</li>
 * </ul>
 *
 * @param <ID>   the type of identifier
 * @param <USER> the type of user
 * @param <DTO>  the type of data transfer object
 * @param <C>    the type of criteria
 */
public interface BaseChannelTypeReference<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>>
        extends BaseTypeReference {

    /**
     * Gets the TypeReference for ChannelMessage containing ModelType.
     *
     * @return TypeReference for ChannelMessage&lt;USER, ModelType&gt;
     */
    TypeReference<ChannelMessage<USER, ModelType>> getChannelMessageModelTypeReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing CriteriaMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, CriteriaMessage&lt;ID, C&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, CriteriaMessage<ID, C>>> getChannelMessageCriteriaReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing PageableMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, PageableMessage&lt;ID, C&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, PageableMessage<ID, C>>> getChannelMessagePageableReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing PageMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, PageMessage&lt;ID, DTO&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, PageMessage<ID, DTO>>> getChannelMessagePageReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing JsonPatchMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, JsonPatchMessage&lt;ID, C&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, JsonPatchMessage<ID, C>>> getChannelMessageJsonPatchReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing DtoMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, DtoMessage&lt;ID, DTO&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, DtoMessage<ID, DTO>>> getChannelMessageDtoReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing DtosMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, DtosMessage&lt;ID, DTO&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, DtosMessage<ID, DTO>>> getChannelMessageDtosReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing IdMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, IdMessage&lt;ID&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, IdMessage<ID>>> getChannelMessageIdReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing IdsMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, IdsMessage&lt;ID&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, IdsMessage<ID>>> getChannelMessageIdsReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing IdJsonPatchMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, IdJsonPatchMessage&lt;ID&gt;&gt;
     */
    TypeReference<ChannelMessage<USER, IdJsonPatchMessage<ID>>> getChannelMessageIdJsonPatchReferenceType();

    /**
     * Gets the TypeReference for ChannelMessage containing LongMessage.
     *
     * @return TypeReference for ChannelMessage&lt;USER, LongMessage&gt;
     */
    TypeReference<ChannelMessage<USER, LongMessage>> getChannelMessageLongReferenceType();
}