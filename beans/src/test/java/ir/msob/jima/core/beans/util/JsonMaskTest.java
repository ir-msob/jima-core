package ir.msob.jima.core.beans.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatchException;
import ir.msob.jima.core.beans.util.jsonpatch.PatchUtil;
import ir.msob.jima.core.beans.util.mask.JsonMask;
import ir.msob.jima.core.beans.util.mask.Mask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

public class JsonMaskTest {

    private static JsonMask jsonMask;
    private static ObjectMapper objectMapper;

    // Initialize the necessary objects before running tests.
    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        PatchUtil patchUtil = new PatchUtil(objectMapper);
        jsonMask = new JsonMask(objectMapper, patchUtil);
    }

    // Test masking a JSON object.
    @Test
    void testMask() throws JsonProcessingException, JsonPatchException, JsonPointerException {
        // Define a mask to transform JSON paths.
        Collection<Mask> masks = Collections.singletonList(new Mask("$.from.path", "$.to.path"));

        // Create the input JSON object to be masked.
        Map<String, Map<String, String>> inputObject = new HashMap<>();
        inputObject.put("from", new HashMap<>());
        inputObject.get("from").put("path", "value");

        // Apply the mask and convert the result to a map.
        Object maskedObject = jsonMask.mask(masks, inputObject);
        @SuppressWarnings("unchecked")
        Map<String, Map<String, String>> maskedMap = objectMapper.convertValue(maskedObject, Map.class);

        // Verify that the maskedMap contains the expected value.
        Assertions.assertEquals("value", maskedMap.get("to").get("path"));
    }

    // Test moving data from a non-array to a non-array JSON structure.
    @Test
    void testMoveDataForNonArrayToNonArray() throws JsonProcessingException, JsonPatchException, JsonPointerException {
        // Define a mask to transform JSON paths.
        Mask mask = new Mask("$.from.path", "$.to.path");

        // Create the input JSON object with source data.
        Map<String, Map<String, String>> inputObject = new HashMap<>();
        inputObject.put("from", new HashMap<>());
        inputObject.get("from").put("path", "value");

        // Apply the mask to move the data and convert the result to a map.
        Object maskedObject = jsonMask.moveData(mask, inputObject, new HashMap<>());
        @SuppressWarnings("unchecked")
        Map<String, Map<String, String>> maskedMap = objectMapper.convertValue(maskedObject, Map.class);

        // Verify that the maskedMap contains the expected value.
        Assertions.assertEquals("value", maskedMap.get("to").get("path"));
    }

    // Test moving data from an array to an array JSON structure.
    @Test
    void testMoveDataForArrayToArray() throws JsonProcessingException, JsonPatchException, JsonPointerException {
        // Define a mask to transform JSON paths.
        Mask mask = new Mask("$.from.path[*].subpath", "$.to.path[*].subpath");

        // Create the input JSON object with source data in an array.
        Map<String, Map<String, List<Map<String, String>>>> inputObject = new HashMap<>();
        inputObject.put("from", new HashMap<>());
        inputObject.get("from").put("path", new ArrayList<>());
        inputObject.get("from").get("path").add(new HashMap<>());
        inputObject.get("from").get("path").getFirst().put("subpath", "value");

        // Apply the mask to move the data and convert the result to a map.
        Object maskedObject = jsonMask.moveData(mask, inputObject, new HashMap<>());
        @SuppressWarnings("unchecked")
        Map<String, Map<String, List<Map<String, String>>>> maskedMap = objectMapper.convertValue(maskedObject, Map.class);

        // Verify that the maskedMap contains the expected value.
        Assertions.assertEquals("value", maskedMap.get("to").get("path").getFirst().get("subpath"));
    }
}
