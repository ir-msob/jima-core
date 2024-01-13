package ir.msob.jima.core.commons.model.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.msob.jima.core.commons.model.channel.message.*;
import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;

public interface BaseChannelTypeReference<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> {
    TypeReference<ChannelMessage<ID, USER, ModelType>> getModelTypeReferenceType();

    TypeReference<ChannelMessage<ID, USER, CriteriaMessage<ID, C>>> getCriteriaReferenceType();

    TypeReference<ChannelMessage<ID, USER, PageableMessage<ID, C>>> getCriteriaPageReferenceType();

    TypeReference<ChannelMessage<ID, USER, JsonPatchMessage<ID, C>>> getEditReferenceType();

    TypeReference<ChannelMessage<ID, USER, DtoMessage<ID, DTO>>> getDtoReferenceType();

    TypeReference<ChannelMessage<ID, USER, DtosMessage<ID, DTO>>> getDtosReferenceType();
}
