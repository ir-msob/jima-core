package ir.msob.jima.core.commons.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.data.domain.Sort;

import java.io.IOException;

/**
 * Base class for custom JSON serializers for Spring Data objects.
 */
public abstract class BasePageableSerializer<T> extends JsonSerializer<T> {

    /**
     * Write the "sort" field into JSON.
     *
     * @param jsonGenerator The JSON generator.
     * @param sort          The {@link Sort} object to be serialized.
     * @throws IOException If an error occurs during JSON generation.
     */
    protected void writeSortField(JsonGenerator jsonGenerator, Sort sort) throws IOException {
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
    protected void writeFieldIfNotNull(JsonGenerator jsonGenerator, String fieldName, String value) throws IOException {
        if (value != null) {
            jsonGenerator.writeStringField(fieldName, value);
        }
    }
}
