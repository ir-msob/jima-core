package ir.msob.jima.core.ral.mongo.commons.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.logging.log4j.util.Strings;
import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {
    @Override
    public ObjectId deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        String objectIdAsString = p.readValueAs(String.class);
        if (Strings.isBlank(objectIdAsString))
            return null;
        else
            return new ObjectId(objectIdAsString);
    }

}
