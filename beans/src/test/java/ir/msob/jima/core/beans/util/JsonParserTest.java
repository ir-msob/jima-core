package ir.msob.jima.core.beans.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.util.function.Tuple3;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    // The JsonParser instance being tested
    private JsonParser jsonParser;

    @BeforeEach
    public void setup() {
        // Initialize ObjectMapper and JsonParser
        // ObjectMapper for JSON serialization/deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        jsonParser = new JsonParser(objectMapper);
    }

    @Test
    void testDiffWhenBothObjectsAreNull() throws JsonProcessingException {
        // Test the diff method when both input objects are null
        Map<String, Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(null, null);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testDiffWhenObject2IsNull() throws JsonProcessingException {
        // Test the diff method when the second object is null
        Map<String, Object> object1 = new HashMap<>();
        object1.put("key1", "value1");

        Map<String, Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, null);

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.containsKey("$.key1"));

        Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>> diff = result.get("$.key1");
        assertEquals(JsonParser.Operation.REMOVED, diff.getT1());
        Assertions.assertEquals("value1", diff.getT2().get());
        Assertions.assertFalse(diff.getT3().isPresent());
    }

    @Test
    void testDiffWhenObject1IsNull() throws JsonProcessingException {
        Map<String, Object> object2 = new HashMap<>();
        object2.put("key1", "value1");

        Map<String, Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(null, object2);

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.containsKey("$.key1"));

        Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>> diff = result.get("$.key1");
        assertEquals(JsonParser.Operation.ADDED, diff.getT1());
        Assertions.assertFalse(diff.getT2().isPresent());
        Assertions.assertEquals("value1", diff.getT3().get());
    }

    @Test
    void testDiffWhenBothObjectsAreEmptyJSONObjects() throws JsonProcessingException {
        Map<String, Object> object1 = new HashMap<>();
        Map<String, Object> object2 = new HashMap<>();

        Map<String, Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, object2);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testDiffWhenBothObjectsAreIdentical() throws JsonProcessingException {
        Map<String, Object> object1 = new HashMap<>();
        object1.put("key1", "value1");
        object1.put("key2", "value2");

        Map<String, Object> object2 = new HashMap<>();
        object2.put("key1", "value1");
        object2.put("key2", "value2");

        Map<String, Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, object2);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.containsKey("$.key1"));
        Assertions.assertTrue(result.containsKey("$.key2"));

        Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>> diff1 = result.get("$.key1");
        assertEquals(JsonParser.Operation.SAME, diff1.getT1());
        Assertions.assertEquals("value1", diff1.getT2().get());
        Assertions.assertEquals("value1", diff1.getT3().get());

        Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>> diff2 = result.get("$.key2");
        assertEquals(JsonParser.Operation.SAME, diff2.getT1());
        Assertions.assertEquals("value2", diff2.getT2().get());
        Assertions.assertEquals("value2", diff2.getT3().get());
    }

    @Test
    void testDiffWhenBothObjectsAreDifferent() throws JsonProcessingException {
        Map<String, Object> object1 = new HashMap<>();
        object1.put("key1", "value1");
        object1.put("key2", "value2");

        Map<String, Object> object2 = new HashMap<>();
        object2.put("key1", "value1_updated");
        object2.put("key3", "value3");

        Map<String, Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, object2);

        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.containsKey("$.key1"));
        Assertions.assertTrue(result.containsKey("$.key2"));
        Assertions.assertTrue(result.containsKey("$.key3"));

        Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>> diff1 = result.get("$.key1");
        assertEquals(JsonParser.Operation.UPDATED, diff1.getT1());
        Assertions.assertEquals("value1", diff1.getT2().get());
        Assertions.assertEquals("value1_updated", diff1.getT3().get());

        Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>> diff2 = result.get("$.key2");
        assertEquals(JsonParser.Operation.REMOVED, diff2.getT1());
        Assertions.assertEquals("value2", diff2.getT2().get());
        Assertions.assertFalse(diff2.getT3().isPresent());

        Tuple3<JsonParser.Operation, Optional<Object>, Optional<Object>> diff3 = result.get("$.key3");
        assertEquals(JsonParser.Operation.ADDED, diff3.getT1());
        Assertions.assertFalse(diff3.getT2().isPresent());
        Assertions.assertEquals("value3", diff3.getT3().get());
    }

    @Test
    void testCompareWhenBothValuesAreNull() throws JsonProcessingException {
        JsonParser.Operation operation = jsonParser.compare(null, null);
        assertEquals(JsonParser.Operation.SAME, operation);
    }

    @Test
    void testCompareWhenValue2IsNull() throws JsonProcessingException {
        JsonParser.Operation operation = jsonParser.compare("value1", null);
        assertEquals(JsonParser.Operation.REMOVED, operation);
    }

    @Test
    void testCompareWhenValue1IsNull() throws JsonProcessingException {
        JsonParser.Operation operation = jsonParser.compare(null, "value2");
        assertEquals(JsonParser.Operation.ADDED, operation);
    }

    @Test
    void testCompareWhenBothValuesAreEqual() throws JsonProcessingException {
        JsonParser.Operation operation = jsonParser.compare("value", "value");
        assertEquals(JsonParser.Operation.SAME, operation);
    }

    @Test
    void testCompareWhenBothValuesAreDifferent() throws JsonProcessingException {
        JsonParser.Operation operation = jsonParser.compare("value1", "value2");
        assertEquals(JsonParser.Operation.UPDATED, operation);
    }

    @Test
    void testGetJsonPathsFromNullObject() throws JsonProcessingException {
        Map<String, Object> result = jsonParser.getJsonPaths(null);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testGetJsonPathsFromJSONObject() throws JsonProcessingException {
        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("key1", "value1");
        jsonObject.put("key2", "value2");

        Map<String, Object> result = jsonParser.getJsonPaths(jsonObject);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.containsKey("$.key1"));
        Assertions.assertTrue(result.containsKey("$.key2"));
        Assertions.assertEquals("value1", result.get("$.key1"));
        Assertions.assertEquals("value2", result.get("$.key2"));
    }

    @Test
    void testGetJsonPathsFromArray() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        List<Object> jsonArray = new ArrayList<>();
        jsonArray.add("value1");
        jsonArray.add("value2");
        map.put("array", jsonArray);

        Map<String, Object> result = jsonParser.getJsonPaths(map);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.containsKey("$.array[0]"));
        Assertions.assertTrue(result.containsKey("$.array[1]"));
        Assertions.assertEquals("value1", result.get("$.array[0]"));
        Assertions.assertEquals("value2", result.get("$.array[1]"));
    }

    @Test
    void testGetJsonPathsFromNestedObject() throws JsonProcessingException {
        Map<String, Object> jsonObject = new HashMap<>();
        Map<String, Object> nestedObject = new HashMap<>();
        nestedObject.put("key1", "value1");
        nestedObject.put("key2", "value2");
        jsonObject.put("nested", nestedObject);

        Map<String, Object> result = jsonParser.getJsonPaths(jsonObject);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.containsKey("$.nested.key1"));
        Assertions.assertTrue(result.containsKey("$.nested.key2"));
        Assertions.assertEquals("value1", result.get("$.nested.key1"));
        Assertions.assertEquals("value2", result.get("$.nested.key2"));
    }
}
