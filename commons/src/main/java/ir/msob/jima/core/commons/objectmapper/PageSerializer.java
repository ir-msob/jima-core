package ir.msob.jima.core.commons.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.IOException;

/**
 * Custom JSON serializer for serializing a Spring Data {@link Page} object.
 * This class is annotated with {@link JsonComponent} to be automatically discovered by Spring Boot.
 */
@JsonComponent
public class PageSerializer extends JsonSerializer<Page<?>> {

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
        writeSortArrayField(jsonGenerator, page.getSort());

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

    /**
     * Write the "sort" array field into JSON.
     *
     * @param jsonGenerator The JSON generator.
     * @param sort          The {@link Sort} object to be serialized.
     * @throws IOException If an error occurs during JSON generation.
     */
    private void writeSortArrayField(JsonGenerator jsonGenerator, Sort sort) throws IOException {
        // Start writing the "sort" array field
        jsonGenerator.writeArrayFieldStart("sort");

        // Iterate over each Sort.Order and write its properties
        for (Sort.Order order : sort) {
            jsonGenerator.writeStartObject();
            writeFieldIfNotNull(jsonGenerator, "property", order.getProperty());
            writeFieldIfNotNull(jsonGenerator, "direction", order.getDirection().name());
            jsonGenerator.writeBooleanField("ignoreCase", order.isIgnoreCase());
            writeFieldIfNotNull(jsonGenerator, "nullHandling", order.getNullHandling().name());
            jsonGenerator.writeEndObject();
        }

        // End writing the "sort" array field
        jsonGenerator.writeEndArray();
    }

    /**
     * Write a field into JSON if its value is not null.
     *
     * @param jsonGenerator The JSON generator.
     * @param fieldName     The name of the field.
     * @param value         The value of the field.
     * @throws IOException If an error occurs during JSON generation.
     */
    private void writeFieldIfNotNull(JsonGenerator jsonGenerator, String fieldName, String value) throws IOException {
        if (value != null) {
            jsonGenerator.writeStringField(fieldName, value);
        }
    }
}
