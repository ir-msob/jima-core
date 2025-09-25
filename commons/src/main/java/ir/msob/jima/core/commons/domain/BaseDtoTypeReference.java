package ir.msob.jima.core.commons.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.shared.PageResponse;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * The {@code BaseDtoTypeReference} interface defines reusable type references
 * and serialization helpers for DTO-related operations. It is parameterized with:
 * <ul>
 *   <li>{@code ID} – The identifier type, must be {@link Comparable} and {@link Serializable}.</li>
 *   <li>{@code DTO} – The Data Transfer Object type, must extend {@link BaseDto}.</li>
 *   <li>{@code C} – The criteria type used for filtering and queries, must extend {@link BaseCriteria}.</li>
 * </ul>
 *
 * <p>This interface provides:</p>
 * <ul>
 *   <li>Access to an {@link ObjectMapper} for JSON serialization and deserialization.</li>
 *   <li>Type-safe {@link ParameterizedTypeReference} instances for DTOs, collections of DTOs,
 *       criteria objects, page responses, and IDs.</li>
 *   <li>A convenience {@link #cast(String, TypeReference)} method to deserialize
 *       a JSON string into a target type.</li>
 * </ul>
 *
 * <p>Implementations of this interface are expected to provide an {@link ObjectMapper}
 * properly configured with the application's serialization settings.</p>
 *
 * @param <ID>  The type of the entity identifier.
 * @param <DTO> The Data Transfer Object type representing the entity.
 * @param <C>   The criteria type used for search and filtering operations.
 */
public interface BaseDtoTypeReference<
        ID extends Comparable<ID> & Serializable,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> {

    /**
     * Provides the {@link ObjectMapper} used for JSON serialization and deserialization.
     * Implementations should supply a properly configured instance that
     * includes any custom serializers or deserializers used in the application.
     *
     * @return the configured {@link ObjectMapper}
     */
    ObjectMapper getObjectMapper();

    /**
     * Returns a type reference for a paginated response of DTOs.
     * Useful when deserializing a JSON object into a {@link PageResponse} containing DTOs.
     *
     * @return the type reference for a paginated response of DTOs
     */
    TypeReference<PageResponse<DTO>> getPageResponseReferenceType();

    /**
     * Returns a type reference for a collection of IDs.
     * Useful when deserializing a JSON array into a {@code Collection<ID>}.
     *
     * @return the type reference for a collection of IDs
     */
    TypeReference<Collection<ID>> getIdsReferenceType();

    /**
     * Utility method to deserialize a JSON string into the specified type
     * using the configured {@link ObjectMapper}.
     *
     * <p>This is a generic helper that can be used to map JSON into any
     * type supported by the application, including DTOs, collections, or paginated responses.</p>
     *
     * @param json          the JSON string to deserialize
     * @param typeReference the type reference describing the target type
     * @param <T>           the target type
     * @return the deserialized object of type {@code T}
     */
    @SneakyThrows
    default <T> T cast(String json, TypeReference<T> typeReference) {
        return getObjectMapper().readValue(json, typeReference);
    }

    default <T> ParameterizedTypeReference<T> toParamTypeRef(TypeReference<T> typeRef) {
        return new ParameterizedTypeReference<>() {
            @Override
            public Type getType() {
                return typeRef.getType();
            }
        };
    }
}
