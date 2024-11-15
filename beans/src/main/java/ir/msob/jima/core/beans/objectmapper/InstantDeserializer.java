package ir.msob.jima.core.beans.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

/**
 * A custom deserializer for converting JSON string representations of
 * {@link java.time.Instant} into {@link Instant} objects.
 *
 * <p>This deserializer is used to handle JSON data that represents
 * an instant in time, converting it into a Java {@link Instant} object.
 * It is annotated with {@link JsonComponent} to be automatically
 * registered with Spring Boot's Jackson configuration.</p>
 *
 * <p>If the JSON string is blank, this deserializer will return
 * {@code null}. Otherwise, it will parse the string into an
 * {@link Instant} using {@link Instant#parse(CharSequence)}.</p>
 */
@JsonComponent
public class InstantDeserializer extends JsonDeserializer<Instant> {

    /**
     * Deserializes a JSON string into an {@link Instant} object.
     *
     * @param p                      the {@link JsonParser} used to read the JSON content
     * @param deserializationContext the context for deserialization
     * @return the deserialized {@link Instant} object, or {@code null} if the input is blank
     * @throws IOException if an I/O error occurs during deserialization
     */
    @Override
    public Instant deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        String instantAsString = p.readValueAs(String.class);
        if (Strings.isBlank(instantAsString))
            return null;
        else
            return Instant.parse(instantAsString);
    }
}
