package ir.msob.jima.core.ral.mongo.commons.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ir.msob.jima.core.commons.util.Strings;
import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JacksonComponent;

import java.io.IOException;

/**
 * A custom deserializer for converting JSON strings into BSON ObjectId instances.
 * This deserializer is used to handle the conversion of JSON data to ObjectId
 * when working with MongoDB in a Spring Boot application.
 */
@JacksonComponent
public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

    /**
     * Deserializes a JSON string into an ObjectId.
     *
     * @param p                      the JsonParser used to parse the JSON content.
     * @param deserializationContext the context for deserialization.
     * @return an ObjectId if the input string is not blank; otherwise, returns null.
     * @throws IOException if an error occurs during parsing.
     */
    @Override
    public ObjectId deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        String objectIdAsString = p.readValueAs(String.class);
        if (Strings.isBlank(objectIdAsString))
            return null;
        else
            return new ObjectId(objectIdAsString);
    }
}
