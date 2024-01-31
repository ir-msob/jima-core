package ir.msob.jima.core.beans.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

@JsonComponent
public class InstantDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        String instantAsString = p.readValueAs(String.class);
        if (Strings.isBlank(instantAsString))
            return null;
        else
            return Instant.parse(instantAsString);
    }

}
