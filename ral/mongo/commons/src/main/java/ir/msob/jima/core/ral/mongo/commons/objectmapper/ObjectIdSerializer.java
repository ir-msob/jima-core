package ir.msob.jima.core.ral.mongo.commons.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * A custom serializer for converting BSON ObjectId instances into JSON strings.
 * This serializer is used to handle the conversion of ObjectId to JSON data
 * when working with MongoDB in a Spring Boot application.
 */
@JsonComponent
public class ObjectIdSerializer extends JsonSerializer<ObjectId> {

    /**
     * Serializes an ObjectId into a JSON string.
     *
     * @param value       the ObjectId to serialize.
     * @param gen         the JsonGenerator used to write JSON content.
     * @param serializers the SerializerProvider that can be used to get serializers for
     *                    serializing Objects value contains, if any.
     * @throws IOException if an error occurs during writing JSON content.
     */
    @Override
    public void serialize(ObjectId value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null)
            gen.writeNull();
        else
            gen.writeString(value.toHexString());
    }
}
