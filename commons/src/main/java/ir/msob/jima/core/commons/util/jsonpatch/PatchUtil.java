package ir.msob.jima.core.commons.util.jsonpatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.*;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A utility class for working with JSON patches.
 */
@Component
@RequiredArgsConstructor
public class PatchUtil {
    public static final String ADD_OPERATION = "add";
    public static final String REPLACE_OPERATION = "replace";
    private final ObjectMapper objectMapper;

    /**
     * Apply a collection of JSON patch operations to a JSON object.
     *
     * @param patchOperations The collection of patch operations to apply.
     * @param o               The JSON object to which the patch operations should be applied.
     * @return The JSON object after applying the patch operations.
     * @throws JsonPatchException      If an error occurs during the JSON patching process.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     * @throws JsonPointerException    If an error occurs due to JSON pointer issues.
     */
    public Object applyJsonPatch(Collection<PatchOperation> patchOperations, Object o) throws JsonPatchException, JsonProcessingException, JsonPointerException {
        List<JsonPatchOperation> operations = new ArrayList<>();
        for (PatchOperation patchOperation : patchOperations) {
            String operationType = patchOperation.getOperation();

            if (Objects.equals(operationType, ADD_OPERATION)) {
                operations.add(new AddOperation(new JsonPointer(patchOperation.getPath()), objectMapper.convertValue(patchOperation.getValue(), JsonNode.class)));
            } else if (Objects.equals(operationType, REPLACE_OPERATION)) {
                operations.add(new ReplaceOperation(new JsonPointer(patchOperation.getPath()), objectMapper.convertValue(patchOperation.getValue(), JsonNode.class)));
            } else {
                throw new CommonRuntimeException("Cannot detect PatchOperation " + operationType);
            }
        }
        return applyJsonPatch(new JsonPatch(operations), o);
    }

    /**
     * Apply a JSON patch to a JSON object.
     *
     * @param jsonPatch The JSON patch to apply.
     * @param o         The JSON object to which the patch should be applied.
     * @return The JSON object after applying the patch.
     * @throws JsonPatchException      If an error occurs during the JSON patching process.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    public Object applyJsonPatch(JsonPatch jsonPatch, Object o) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(o, JsonNode.class));
        return objectMapper.treeToValue(patched, Object.class);
    }

    /**
     * Prepare a JSON patch for adding a new value at the specified path in a JSON object.
     *
     * @param path The path at which the new value should be added.
     * @param data The data to be added at the specified path.
     * @return The JSON patch for adding the new value.
     * @throws JsonPointerException If an error occurs due to JSON pointer issues.
     */
    public JsonPatch prepareAddJsonPatch(String path, Object data) throws JsonPointerException {
        JsonPointer pointerData = new JsonPointer(path);
        AddOperation addOperationData = new AddOperation(pointerData, objectMapper.convertValue(data, JsonNode.class));

        List<JsonPatchOperation> operations = new ArrayList<>();
        operations.add(addOperationData);
        return new JsonPatch(operations);
    }

    /**
     * Prepare a JSON patch for replacing a value at the specified path in a JSON object.
     *
     * @param path The path at which the value should be replaced.
     * @param data The data to replace the existing value at the specified path.
     * @return The JSON patch for replacing the value.
     * @throws JsonPointerException If an error occurs due to JSON pointer issues.
     */
    public JsonPatch prepareReplaceJsonPatch(String path, Object data) throws JsonPointerException {
        JsonPointer pointerData = new JsonPointer(path);
        ReplaceOperation replaceOperationData = new ReplaceOperation(pointerData, objectMapper.convertValue(data, JsonNode.class));

        List<JsonPatchOperation> operations = new ArrayList<>();
        operations.add(replaceOperationData);
        return new JsonPatch(operations);
    }

    /**
     * Set a value at a specified path within a JSON object.
     *
     * @param o     The JSON object in which to set the value.
     * @param path  The path at which to set the value.
     * @param value The value to be set.
     * @return The JSON object with the updated value.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     * @throws JsonPointerException    If an error occurs due to JSON pointer issues.
     * @throws JsonPatchException      If an error occurs during the JSON patching process.
     */
    public Object setValueToPath(Object o, String path, Object value) throws JsonProcessingException, JsonPointerException, JsonPatchException {
        String[] fields = path.replace("$.", "").split("\\.");
        Object sampleValue = objectMapper.readValue("{}", Object.class);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$");
        for (String f : fields) {
            String fullPath = stringBuilder + "." + f;
            try {
                JsonPath.read(o, fullPath);
            } catch (PathNotFoundException e) {
                String patchPath = (stringBuilder + "." + f).replace("$.", "/").replace(".", "/");
                JsonPatch jsonPatch = prepareAddJsonPatch(patchPath, sampleValue);
                o = applyJsonPatch(jsonPatch, o);
            }
            stringBuilder.append(".").append(f);
        }

        String patchPath = stringBuilder.toString().replace("$.", "/").replace(".", "/");
        JsonPatch jsonPatch = prepareReplaceJsonPatch(patchPath, value);
        return applyJsonPatch(jsonPatch, o);
    }
}
