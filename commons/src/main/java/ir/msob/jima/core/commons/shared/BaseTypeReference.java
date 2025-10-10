package ir.msob.jima.core.commons.shared;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Type;

/**
 * The {@code BaseTypeReference} interface provides utility methods for type reference operations
 * and JSON serialization/deserialization using Jackson ObjectMapper.
 * <p>
 * Implementing classes should provide an instance of {@link ObjectMapper} for JSON operations.
 * </p>
 */
public interface BaseTypeReference {

    /**
     * Gets the ObjectMapper instance for JSON serialization and deserialization.
     *
     * @return the ObjectMapper instance
     */
    ObjectMapper getObjectMapper();

    /**
     * Converts a Jackson {@link TypeReference} to a Spring {@link ParameterizedTypeReference}.
     *
     * @param <T>     the type of the object to be referenced
     * @param typeRef the Jackson TypeReference to convert
     * @return a Spring ParameterizedTypeReference with the same type information
     */
    default <T> ParameterizedTypeReference<T> cast(TypeReference<T> typeRef) {
        return new ParameterizedTypeReference<>() {
            @Override
            public Type getType() {
                return typeRef.getType();
            }
        };
    }

    /**
     * Deserializes JSON string into an object of the specified type using the configured ObjectMapper.
     *
     * @param <T>           the type of the object to deserialize
     * @param json          the JSON string to deserialize
     * @param typeReference the TypeReference describing the target type
     * @return the deserialized object
     * @throws RuntimeException if deserialization fails
     */
    @SneakyThrows
    default <T> T cast(String json, TypeReference<T> typeReference) {
        return getObjectMapper().readValue(json, typeReference);
    }
}