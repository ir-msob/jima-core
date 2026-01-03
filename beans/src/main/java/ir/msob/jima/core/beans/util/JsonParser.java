package ir.msob.jima.core.beans.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import ir.msob.jima.core.commons.Constants;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.*;

/**
 * Utility class for comparing and extracting differences between two JSON objects.
 */
@Component
@RequiredArgsConstructor
public class JsonParser {
    private final ObjectMapper objectMapper;

    /**
     * Compare two JSON objects and return a map of differences.
     *
     * @param object1 The first JSON object for comparison.
     * @param object2 The second JSON object for comparison.
     * @return A map containing the differences between the two JSON objects.
     * @throws JsonProcessingException if there is an issue with JSON processing.
     */
    public Map<String, Tuple3<@NonNull Operation, @NonNull Optional<Object>, @NonNull Optional<Object>>> diff(Object object1, Object object2) throws JsonProcessingException {
        Map<String, Object> data1 = getJsonPaths(object1);
        Map<String, Object> data2 = getJsonPaths(object2);
        return getDiff(data1, data2);
    }

    /**
     * Compares two JSON objects and returns a map of differences between them.
     *
     * @param data1 The first JSON object's data for comparison.
     * @param data2 The second JSON object's data for comparison.
     * @return A map containing the differences between the two JSON objects.
     * @throws JsonProcessingException if there is an issue with JSON processing.
     */
    private Map<String, Tuple3<@NonNull Operation, @NonNull Optional<Object>, @NonNull Optional<Object>>> getDiff(Map<String, Object> data1, Map<String, Object> data2) throws JsonProcessingException {
        Map<String, Tuple3<@NonNull Operation, @NonNull Optional<Object>, @NonNull Optional<Object>>> result = new TreeMap<>();
        for (Map.Entry<String, Object> entry1 : data1.entrySet()) {
            Object value1 = entry1.getValue();
            Object value2 = data2.get(entry1.getKey());
            data2.remove(entry1.getKey());
            Tuple3<@NonNull Operation, @NonNull Optional<Object>, @NonNull Optional<Object>> tuple3 = Tuples.of(compare(value1, value2), Optional.ofNullable(value1), Optional.ofNullable(value2));
            result.put(entry1.getKey(), tuple3);
        }

        for (Map.Entry<String, Object> entry2 : data2.entrySet()) {
            Tuple3<@NonNull Operation, @NonNull Optional<Object>, @NonNull Optional<Object>> tuple3 = Tuples.of(Operation.ADDED, Optional.empty(), Optional.ofNullable(entry2.getValue()));
            result.put(entry2.getKey(), tuple3);
        }
        return result;
    }

    /**
     * Compare two JSON values and determine the operation type (e.g., added, updated, removed, same).
     *
     * @param value1 The first JSON value.
     * @param value2 The second JSON value.
     * @return The operation type.
     * @throws JsonProcessingException if there is an issue with JSON processing.
     */
    public Operation compare(Object value1, Object value2) throws JsonProcessingException {
        if (value1 == null && value2 == null) {
            return Operation.SAME;
        } else if (value1 != null && value2 == null) {
            return Operation.REMOVED;
        } else if (value1 == null) {
            return Operation.ADDED;
        } else {
            if (compareObject(value1, value2)) {
                return Operation.SAME;
            } else {
                return Operation.UPDATED;
            }
        }
    }

    /**
     * Compares two JSON objects to determine if they are the same.
     *
     * @param value1 The first JSON object for comparison.
     * @param value2 The second JSON object for comparison.
     * @return `true` if the objects are the same; otherwise, `false`.
     * @throws JsonProcessingException if there is an issue with JSON processing.
     */
    private boolean compareObject(Object value1, Object value2) throws JsonProcessingException {
        String v1 = objectMapper.writeValueAsString(value1);
        String v2 = objectMapper.writeValueAsString(value2);
        return v1.equals(v2);
    }

    /**
     * Extract JSON paths and their corresponding values from a JSON object.
     *
     * @param object The JSON object for path extraction.
     * @return A map of JSON paths and their values.
     * @throws JsonProcessingException if there is an issue with JSON processing.
     */
    public Map<String, Object> getJsonPaths(Object object) throws JsonProcessingException {
        if (object == null) return new HashMap<>();

        String json = objectMapper.writeValueAsString(object);
        try {
            json = JsonPath.parse(json).delete("$." + Constants.TYPE_PROPERTY_NAME).jsonString(); // FIXME : Change all JsonTypeInfo.Id.NAME to JsonTypeInfo.Id.NONE (But some codes will fail)
        } catch (Exception ignored) {
        }

        Map<String, Object> result = new HashMap<>();
        JSONObject jsonObject = new JSONObject(json);
        String jsonPath = "$";
        if (json != JSONObject.NULL) {
            readObject(jsonObject, jsonPath, result);
        }
        return result;
    }

    /**
     * Recursively extract JSON paths and their corresponding values from a JSON object.
     *
     * @param object   The JSON object for path extraction.
     * @param jsonPath The JSON path of the current object.
     * @param data     The map to store the extracted paths and values.
     */
    private void readObject(JSONObject object, String jsonPath, Map<String, Object> data) {
        Iterator<String> keysItr = object.keys();
        String parentPath = jsonPath;
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            jsonPath = parentPath + "." + key;

            switch (value) {
                case JSONArray v -> readArray(v, jsonPath, data);
                case JSONObject v -> readObject(v, jsonPath, data);
                case null, default ->  // is a value
                        data.put(jsonPath, value);
            }
        }
    }

    /**
     * Recursively extract JSON paths and their corresponding values from a JSON array.
     *
     * @param array    The JSON array for path extraction.
     * @param jsonPath The JSON path of the current array element.
     * @param data     The map to store the extracted paths and values.
     */
    private void readArray(JSONArray array, String jsonPath, Map<String, Object> data) {
        String parentPath = jsonPath;
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            jsonPath = parentPath + "[" + i + "]";

            switch (value) {
                case JSONArray v -> readArray(v, jsonPath, data);
                case JSONObject v -> readObject(v, jsonPath, data);
                case null, default ->  // is a value
                        data.put(jsonPath, value);
            }
        }
    }

    /**
     * Enumeration of possible JSON object comparison operations.
     */
    public enum Operation {
        ADDED, UPDATED, REMOVED, SAME
    }
}
