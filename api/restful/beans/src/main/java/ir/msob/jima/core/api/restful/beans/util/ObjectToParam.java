package ir.msob.jima.core.api.restful.beans.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.msob.jima.core.commons.util.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * This service class, ObjectToParam, is responsible for converting objects into a MultiValueMap of key-value pairs, where keys represent object properties and values represent their corresponding values.
 */
@Component
@RequiredArgsConstructor
public class ObjectToParam {
    private final JsonParser jsonParser;

    /**
     * Add a key-value pair to the MultiValueMap.
     *
     * @param key    The key (property name).
     * @param value  The value (property value).
     * @param result The MultiValueMap to which the key-value pair should be added.
     */
    private static void addResult(String key, String value, MultiValueMap<String, String> result) {
        if (key.endsWith("]")) {
            key = key.substring(0, key.length() - 3);
        }
        result.computeIfAbsent(key, k -> new ArrayList<>())
                .add(value);
    }

    /**
     * Convert one or more objects into a MultiValueMap of key-value pairs.
     *
     * @param objects The objects to convert into key-value pairs.
     * @return A MultiValueMap containing the properties and values extracted from the objects.
     * @throws JsonProcessingException If there is an issue with processing JSON data from the objects.
     */
    public MultiValueMap<String, String> convert(Object... objects) throws JsonProcessingException {
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        for (Object o : objects) {
            Map<String, Object> propertyMap = jsonParser.getJsonPaths(o);
            propertyMap.entrySet()
                    .stream()
                    .filter(entry -> shouldIncludeProperty(entry.getValue()))
                    .forEach(entry -> {
                        String key = entry.getKey().replace("$.", "");
                        String value = String.valueOf(entry.getValue());
                        addResult(key, value, result);
                    });
        }
        return result;
    }

    /**
     * Determine whether a property should be included in the result based on its value.
     *
     * @param value The value of the property.
     * @return true if the property should be included, false otherwise.
     */
    public boolean shouldIncludeProperty(Object value) {
        return value != null && !Objects.equals(value, "null") && !Objects.equals(value, "");
    }
}
