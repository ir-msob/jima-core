package ir.msob.jima.core.commons.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

/**
 * Custom JSON serializer for serializing a Spring Data {@link Pageable} object.
 * This class is annotated with {@link JsonComponent} to be automatically discovered by Spring Boot.
 */
@JsonComponent
public class PageableSerializer extends BasePageableSerializer<Pageable> {

    /**
     * Serialize a {@link Pageable} object into JSON.
     *
     * @param pageable           The {@link Pageable} object to be serialized.
     * @param jsonGenerator      The JSON generator.
     * @param serializerProvider The serializer provider.
     * @throws IOException If an error occurs during JSON generation.
     */
    @Override
    public void serialize(Pageable pageable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Start writing the JSON object
        jsonGenerator.writeStartObject();

        // Write page-child fields
        jsonGenerator.writeNumberField("offset", pageable.getOffset());
        jsonGenerator.writeNumberField("pageNumber", pageable.getPageNumber());
        jsonGenerator.writeNumberField("pageSize", pageable.getPageSize());

        // Write the "sort" field
        writeSortField(jsonGenerator, pageable.getSort());

        // End writing the JSON object
        jsonGenerator.writeEndObject();
    }
}
