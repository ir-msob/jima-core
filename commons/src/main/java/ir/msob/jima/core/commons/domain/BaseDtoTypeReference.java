package ir.msob.jima.core.commons.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.shared.PageResponse;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.Collection;

/**
 * The 'BaseChannelTypeReference' interface defines the type references for various types of channel messages.
 * It is parameterized with four types 'ID' that extends 'Comparable' and 'Serializable', 'USER' that extends 'BaseUser', 'DTO' that extends 'BaseDto', and 'C' that extends 'BaseCriteria'.
 * The interface includes methods to get the type references for 'ModelType', 'CriteriaMessage', 'PageableMessage', 'JsonPatchMessage', 'DtoMessage', and 'DtosMessage'.
 *
 * @param <ID>  The type of ID.
 * @param <DTO> The type of DTO.
 * @param <C>   The type of criteria.
 */
public interface BaseDtoTypeReference<
        ID extends Comparable<ID> & Serializable,
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
    ParameterizedTypeReference<Collection<DTO>> getDtosTypeReferenceType();

    /**
     * Gets the type reference for 'CriteriaMessage'.
     *
     * @return The type reference for 'CriteriaMessage'.
     */
    ParameterizedTypeReference<DTO> getDtoReferenceType();

    /**
     * Gets the type reference for 'PageableMessage'.
     *
     * @return The type reference for 'PageableMessage'.
     */
    ParameterizedTypeReference<C> getCriteriaReferenceType();

    /**
     * Gets the type reference for 'PageMessage'.
     *
     * @return The type reference for 'PageMessage'.
     */
    ParameterizedTypeReference<PageResponse<DTO>> getPageReferenceType();


    @SneakyThrows
    default <T> T cast(String json, TypeReference<T> typeReference) {
        return getObjectMapper().readValue(json, typeReference);
    }
}