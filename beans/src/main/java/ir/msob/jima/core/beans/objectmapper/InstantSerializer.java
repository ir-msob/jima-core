package ir.msob.jima.core.beans.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

/**
 * A custom serializer for the {@link Instant} class.
 * This serializer converts an {@link Instant} object to its string representation
 * when serializing to JSON using Jackson.
 */
@JsonComponent
public class InstantSerializer extends JsonSerializer<Instant> {

    /**
     * Serializes an {@link Instant} object to its string representation.
     * If the {@link Instant} is null, it writes a null value.
     *
     * @param value       the {@link Instant} object to serialize
     * @param gen         the {@link JsonGenerator} used to write JSON content
     * @param serializers the {@link SerializerProvider} that can be used to get serializers for
     *                    serializing Objects value contains, if any.
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null)
            gen.writeNull();
        else
            gen.writeString(value.toString());
    }
}
