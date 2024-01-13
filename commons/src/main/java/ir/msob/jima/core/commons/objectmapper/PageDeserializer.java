package ir.msob.jima.core.commons.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ir.msob.jima.core.commons.util.PaginationUtil;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * Custom JSON deserializer for deserializing a JSON representation of a Spring Data {@link Page} object.
 * This class is annotated with {@link JsonComponent} to be automatically discovered by Spring Boot.
 */
@JsonComponent
public class PageDeserializer extends JsonDeserializer<Page<?>> {

    /**
     * Deserialize a JSON representation into a {@link Page} object.
     *
     * @param jsonParser             The JSON parser.
     * @param deserializationContext The deserialization context.
     * @return A {@link Page} object.
     * @throws IOException If an error occurs during JSON parsing.
     */
    @Override
    public Page<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        // Get the ObjectCodec from the JsonParser
        ObjectCodec pageableObjectCodec = jsonParser.getCodec();

        // Read the JSON representation into a JsonNode
        JsonNode pageableJsonNode = pageableObjectCodec.readTree(jsonParser);

        // Use the PaginationUtil class to prepare a Page object from the JsonNode and JsonParser
        return PaginationUtil.preparePage(pageableJsonNode, jsonParser);
    }
}
