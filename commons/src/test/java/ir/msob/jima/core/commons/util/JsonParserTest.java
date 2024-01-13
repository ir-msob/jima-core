package ir.msob.jima.core.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.util.JsonParser.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.util.function.Tuple3;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    // ObjectMapper for JSON serialization/deserialization
    private ObjectMapper objectMapper;

    // The JsonParser instance being tested
    private JsonParser jsonParser;

    @BeforeEach
    public void setup() {
        // Initialize ObjectMapper and JsonParser
        objectMapper = new ObjectMapper();
        jsonParser = new JsonParser(objectMapper);
    }

    @Test
    void testDiffWhenBothObjectsAreNull() throws JsonProcessingException {
        // Test the diff method when both input objects are null
        Map<String, Tuple3<Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(null, null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testDiffWhenObject2IsNull() throws JsonProcessingException {
        // Test the diff method when the second object is null
        Map<String, Object> object1 = new HashMap<>();
        object1.put("key1", "value1");

        Map<String, Tuple3<Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, null);

        assertEquals(1, result.size());
        assertTrue(result.containsKey("$.key1"));

        Tuple3<Operation, Optional<Object>, Optional<Object>> diff = result.get("$.key1");
        assertEquals(Operation.REMOVED, diff.getT1());
        assertEquals("value1", diff.getT2().get());
        assertFalse(diff.getT3().isPresent());
    }

    @Test
    void testDiffWhenObject1IsNull() throws JsonProcessingException {
        Map<String, Object> object2 = new HashMap<>();
        object2.put("key1", "value1");

        Map<String, Tuple3<Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(null, object2);

        assertEquals(1, result.size());
        assertTrue(result.containsKey("$.key1"));

        Tuple3<Operation, Optional<Object>, Optional<Object>> diff = result.get("$.key1");
        assertEquals(Operation.ADDED, diff.getT1());
        assertFalse(diff.getT2().isPresent());
        assertEquals("value1", diff.getT3().get());
    }

    @Test
    void testDiffWhenBothObjectsAreEmptyJSONObjects() throws JsonProcessingException {
        Map<String, Object> object1 = new HashMap<>();
        Map<String, Object> object2 = new HashMap<>();

        Map<String, Tuple3<Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, object2);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDiffWhenBothObjectsAreIdentical() throws JsonProcessingException {
        Map<String, Object> object1 = new HashMap<>();
        object1.put("key1", "value1");
        object1.put("key2", "value2");

        Map<String, Object> object2 = new HashMap<>();
        object2.put("key1", "value1");
        object2.put("key2", "value2");

        Map<String, Tuple3<Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, object2);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("$.key1"));
        assertTrue(result.containsKey("$.key2"));

        Tuple3<Operation, Optional<Object>, Optional<Object>> diff1 = result.get("$.key1");
        assertEquals(Operation.SAME, diff1.getT1());
        assertEquals("value1", diff1.getT2().get());
        assertEquals("value1", diff1.getT3().get());

        Tuple3<Operation, Optional<Object>, Optional<Object>> diff2 = result.get("$.key2");
        assertEquals(Operation.SAME, diff2.getT1());
        assertEquals("value2", diff2.getT2().get());
        assertEquals("value2", diff2.getT3().get());
    }

    @Test
    void testDiffWhenBothObjectsAreDifferent() throws JsonProcessingException {
        Map<String, Object> object1 = new HashMap<>();
        object1.put("key1", "value1");
        object1.put("key2", "value2");

        Map<String, Object> object2 = new HashMap<>();
        object2.put("key1", "value1_updated");
        object2.put("key3", "value3");

        Map<String, Tuple3<Operation, Optional<Object>, Optional<Object>>> result = jsonParser.diff(object1, object2);

        assertEquals(3, result.size());
        assertTrue(result.containsKey("$.key1"));
        assertTrue(result.containsKey("$.key2"));
        assertTrue(result.containsKey("$.key3"));

        Tuple3<Operation, Optional<Object>, Optional<Object>> diff1 = result.get("$.key1");
        assertEquals(Operation.UPDATED, diff1.getT1());
        assertEquals("value1", diff1.getT2().get());
        assertEquals("value1_updated", diff1.getT3().get());

        Tuple3<Operation, Optional<Object>, Optional<Object>> diff2 = result.get("$.key2");
        assertEquals(Operation.REMOVED, diff2.getT1());
        assertEquals("value2", diff2.getT2().get());
        assertFalse(diff2.getT3().isPresent());

        Tuple3<Operation, Optional<Object>, Optional<Object>> diff3 = result.get("$.key3");
        assertEquals(Operation.ADDED, diff3.getT1());
        assertFalse(diff3.getT2().isPresent());
        assertEquals("value3", diff3.getT3().get());
    }

    @Test
    void testCompareWhenBothValuesAreNull() throws JsonProcessingException {
        Operation operation = jsonParser.compare(null, null);
        assertEquals(Operation.SAME, operation);
    }

    @Test
    void testCompareWhenValue2IsNull() throws JsonProcessingException {
        Operation operation = jsonParser.compare("value1", null);
        assertEquals(Operation.REMOVED, operation);
    }

    @Test
    void testCompareWhenValue1IsNull() throws JsonProcessingException {
        Operation operation = jsonParser.compare(null, "value2");
        assertEquals(Operation.ADDED, operation);
    }

    @Test
    void testCompareWhenBothValuesAreEqual() throws JsonProcessingException {
        Operation operation = jsonParser.compare("value", "value");
        assertEquals(Operation.SAME, operation);
    }

    @Test
    void testCompareWhenBothValuesAreDifferent() throws JsonProcessingException {
        Operation operation = jsonParser.compare("value1", "value2");
        assertEquals(Operation.UPDATED, operation);
    }

    @Test
    void testGetJsonPathsFromNullObject() throws JsonProcessingException {
        Map<String, Object> result = jsonParser.getJsonPaths(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetJsonPathsFromJSONObject() throws JsonProcessingException {
        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("key1", "value1");
        jsonObject.put("key2", "value2");

        Map<String, Object> result = jsonParser.getJsonPaths(jsonObject);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("$.key1"));
        assertTrue(result.containsKey("$.key2"));
        assertEquals("value1", result.get("$.key1"));
        assertEquals("value2", result.get("$.key2"));
    }

    @Test
    void testGetJsonPathsFromArray() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        List<Object> jsonArray = new ArrayList<>();
        jsonArray.add("value1");
        jsonArray.add("value2");
        map.put("array", jsonArray);

        Map<String, Object> result = jsonParser.getJsonPaths(map);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("$.array[0]"));
        assertTrue(result.containsKey("$.array[1]"));
        assertEquals("value1", result.get("$.array[0]"));
        assertEquals("value2", result.get("$.array[1]"));
    }

    @Test
    void testGetJsonPathsFromNestedObject() throws JsonProcessingException {
        Map<String, Object> jsonObject = new HashMap<>();
        Map<String, Object> nestedObject = new HashMap<>();
        nestedObject.put("key1", "value1");
        nestedObject.put("key2", "value2");
        jsonObject.put("nested", nestedObject);

        Map<String, Object> result = jsonParser.getJsonPaths(jsonObject);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("$.nested.key1"));
        assertTrue(result.containsKey("$.nested.key2"));
        assertEquals("value1", result.get("$.nested.key1"));
        assertEquals("value2", result.get("$.nested.key2"));
    }
}
