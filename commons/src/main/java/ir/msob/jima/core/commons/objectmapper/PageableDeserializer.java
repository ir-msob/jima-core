package ir.msob.jima.core.commons.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ir.msob.jima.core.commons.util.PaginationUtil;
import org.springframework.boot.jackson.JacksonComponent;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

/**
 * Custom JSON deserializer for deserializing a JSON representation of a Spring Data {@link Pageable} object.
 * This class is annotated with {@link JacksonComponent} to be automatically discovered by Spring Boot.
 */
@JacksonComponent
public class PageableDeserializer extends JsonDeserializer<Pageable> {

    /**
     * Deserialize a JSON representation into a {@link Pageable} object.
     *
     * @param jsonParser             The JSON parser.
     * @param deserializationContext The deserialization context.
     * @return A {@link Pageable} object.
     * @throws IOException If an error occurs during JSON parsing.
     */
    @Override
    public Pageable deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        // Get the ObjectCodec from the JsonParser
        ObjectCodec pageableObjectCodec = jsonParser.getCodec();

        // Read the JSON representation into a JsonNode
        JsonNode pageableJsonNode = pageableObjectCodec.readTree(jsonParser);

        // Use the PaginationUtil class to prepare a Pageable object from the JsonNode
        return PaginationUtil.preparePageable(pageableJsonNode);
    }
}
