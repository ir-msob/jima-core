package ir.msob.jima.core.commons.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;
import org.springframework.boot.jackson.JsonComponent;
import reactor.core.publisher.Mono;

/**
 * The 'MonoSerializer' class is a custom JSON serializer designed to handle the serialization of Mono objects, which are part of the Project Reactor framework.
 * <p>
 * It is annotated with '@JsonComponent', indicating that it should be automatically registered as a Jackson serializer component when the Spring application starts.
 * <p>
 * This serializer converts Mono objects to their resolved values and writes them as JSON using Jackson's JsonGenerator.
 */
@JsonComponent
public class MonoSerializer extends JsonSerializer<Mono<?>> {

    /**
     * Serialize a Mono object to JSON.
     *
     * @param value       The Mono object to be serialized.
     * @param gen         The Jackson JsonGenerator used for generating JSON.
     * @param serializers The Jackson SerializerProvider.
     */
    @SneakyThrows
    @Override
    public void serialize(Mono value, JsonGenerator gen, SerializerProvider serializers) {
        if (value == null) {
            gen.writeNull();
        } else {
            // Resolve the Mono and serialize its value to JSON
            gen.writeObject(value.toFuture().get());
        }
    }
}
