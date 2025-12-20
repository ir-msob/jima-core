package ir.msob.jima.core.commons.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JacksonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * Custom JSON serializer for serializing a Spring Data {@link Page} object.
 * This class is annotated with {@link JacksonComponent} to be automatically discovered by Spring Boot.
 */
@JacksonComponent
public class PageSerializer extends BasePageableSerializer<Page<?>> {

    /**
     * Serialize a {@link Page} object into JSON.
     *
     * @param page               The {@link Page} object to be serialized.
     * @param jsonGenerator      The JSON generator.
     * @param serializerProvider The serializer provider.
     * @throws IOException If an error occurs during JSON generation.
     */
    @Override
    public void serialize(Page<?> page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Start writing the JSON object
        jsonGenerator.writeStartObject();

        // Write the "content" field
        writeContentField(jsonGenerator, page.getContent());

        // Write boolean fields
        jsonGenerator.writeBooleanField("first", page.isFirst());
        jsonGenerator.writeBooleanField("last", page.isLast());

        // Write number fields
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeNumberField("numberOfElements", page.getNumberOfElements());
        jsonGenerator.writeNumberField("size", page.getSize());
        jsonGenerator.writeNumberField("number", page.getNumber());

        // Write the "sort" array field
        writeSortField(jsonGenerator, page.getSort());

        // End writing the JSON object
        jsonGenerator.writeEndObject();
    }

    /**
     * Write the "content" field into JSON.
     *
     * @param jsonGenerator The JSON generator.
     * @param content       The content to be serialized.
     * @throws IOException If an error occurs during JSON generation.
     */
    private void writeContentField(JsonGenerator jsonGenerator, Object content) throws IOException {
        jsonGenerator.writeObjectField("content", content);
    }
}
