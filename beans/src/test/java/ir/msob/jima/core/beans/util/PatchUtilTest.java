package ir.msob.jima.core.beans.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.AddOperation;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import ir.msob.jima.core.beans.util.jsonpatch.PatchOperation;
import ir.msob.jima.core.beans.util.jsonpatch.PatchUtil;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PatchUtilTest {

    static final String PATH = "/path";
    static final String VALUE = "value";
    static final String PATH_FIELD = "path";
    private static PatchUtil patchUtil;
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        patchUtil = new PatchUtil(objectMapper);
    }

    @Test
    void testApplyJsonPatch() throws JsonPatchException, JsonProcessingException, JsonPointerException {
        // Create a JSON patch and an initial JSON object
        List<JsonPatchOperation> operations = new ArrayList<>();
        operations.add(new AddOperation(new JsonPointer(PATH), objectMapper.convertValue(VALUE, JsonNode.class)));
        JsonPatch jsonPatch = new JsonPatch(operations);
        Map<String, String> initialObject = new HashMap<>();

        // Apply the JSON patch
        Map<String, String> result = (Map<String, String>) patchUtil.applyJsonPatch(jsonPatch, initialObject);

        // Assert the result
        Assertions.assertEquals(VALUE, result.get(PATH_FIELD));
    }

    @Test
    void testPrepareAddJsonPatch() throws JsonPointerException {
        Map<String, String> data = new HashMap<>();

        JsonPatch jsonPatch = patchUtil.prepareAddJsonPatch(PATH, data);

        // Assert that the JSON patch is correctly prepared
        Assertions.assertNotNull(jsonPatch);
    }

    @Test
    void testPrepareReplaceJsonPatch() throws JsonPointerException {
        Map<String, String> data = new HashMap<>();

        JsonPatch jsonPatch = patchUtil.prepareReplaceJsonPatch(PATH, data);

        // Assert that the JSON patch is correctly prepared
        Assertions.assertNotNull(jsonPatch);
    }

    @Test
    void testSetValueToPath() throws JsonProcessingException, JsonPointerException, JsonPatchException {
        Map<String, Map<String, String>> initialObject = new HashMap<>();
        String path = "$.some.path";

        Map<String, Map<String, String>> result = (Map<String, Map<String, String>>) patchUtil.setValueToPath(initialObject, path, VALUE);

        // Assert the result
        Assertions.assertEquals(VALUE, result.get("some").get(PATH_FIELD));
    }

    @Test
    void testApplyJsonPatchWithUnknownOperationType() throws JsonPatchException, JsonProcessingException, JsonPointerException {
        // Create a JSON patch with an unknown operation type
        List<PatchOperation> patchOperations = new ArrayList<>();
        patchOperations.add(new PatchOperation("unknown", PATH, VALUE));

        Object object = new Object();

        // Try applying the JSON patch with an unknown operation type
        Assertions.assertThrows(CommonRuntimeException.class, () -> patchUtil.applyJsonPatch(patchOperations, object));
    }

}
