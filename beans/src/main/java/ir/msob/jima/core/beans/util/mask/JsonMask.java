package ir.msob.jima.core.beans.util.mask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import ir.msob.jima.core.beans.util.jsonpatch.PatchUtil;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class provides utility methods for masking JSON data based on specified masks.
 * It provides methods to get field data from a JSON object, mask a JSON object based on a collection of masks,
 * move data from one part of a JSON object to another, process non-array to non-array data movement,
 * and process array to array data movement.
 * It uses the ObjectMapper class for converting values to JSON nodes and the PatchUtil class for applying patches to JSON objects.
 */
@Component
@RequiredArgsConstructor
public class JsonMask {
    private static final Logger log = LoggerFactory.getLog(JsonMask.class);
    private final ObjectMapper objectMapper;
    private final PatchUtil patchUtil;

    /**
     * This method gets the data from a specified field in a JSON object.
     * It uses the JsonPath class to read the data from the field.
     * If the field is not found, it logs an error and returns null.
     *
     * @param o         The JSON object from which to get the field data.
     * @param fieldPath The path to the field in the JSON object.
     * @return The data from the specified field, or null if the field is not found.
     */
    private static Object getFieldData(Object o, String fieldPath) {
        try {
            return JsonPath.read(o, fieldPath);
        } catch (PathNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * This method masks a JSON object based on a collection of masks.
     * It creates a new JSON object and then moves data from the original JSON object to the new one based on the masks.
     * It uses the ObjectMapper class to convert the new JSON object to a string and then back to an Object.
     *
     * @param mask The collection of masks to apply to the JSON object.
     * @param o    The JSON object to be masked.
     * @return The masked JSON object.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     * @throws JsonPatchException      If an error occurs while applying JSON patches.
     * @throws JsonPointerException    If an error occurs due to JSON pointer issues.
     */
    public Object mask(Collection<Mask> mask, Object o) throws JsonProcessingException, JsonPatchException, JsonPointerException {
        Object result = objectMapper.readValue("{}", Object.class);
        for (Mask entry : mask) {
            result = moveData(entry, o, result);
        }
        return result;
    }

    /**
     * This method moves data from one part of a JSON object to another based on a mask.
     * It checks if the from and to paths in the mask contain arrays, and then calls the appropriate method to process the data movement.
     * If both paths contain arrays, it calls the processArrayToArrays method.
     * If neither path contains an array, it calls the processNonArrayToNonArray method.
     * If the from path contains an array and the to path does not, or vice versa, it throws a CommonRuntimeException.
     *
     * @param entry  The mask specifying the from and to paths for the data movement.
     * @param o      The original JSON object.
     * @param result The result JSON object.
     * @return The result JSON object after the data movement.
     * @throws JsonPatchException      If an error occurs while applying JSON patches.
     * @throws JsonPointerException    If an error occurs due to JSON pointer issues.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    public Object moveData(Mask entry, Object o, Object result) throws JsonPatchException, JsonPointerException, JsonProcessingException {
        if (entry.getFrom().contains(Constants.ARRAY_IN_JSON) && entry.getTo().contains(Constants.ARRAY_IN_JSON)) {
            return processArrayToArrays(entry, o, result);
        } else if (!entry.getFrom().contains(Constants.ARRAY_IN_JSON) && !entry.getTo().contains(Constants.ARRAY_IN_JSON)) {
            return processNonArrayToNonArray(entry, o, result);
        }
        throw new CommonRuntimeException("Mask is invalid, key: {}, value: {}", entry.getFrom(), entry.getTo());
    }

    /**
     * This method processes the movement of non-array data to a non-array location in a JSON object.
     * It gets the data from the from path in the original JSON object and sets it at the to path in the result JSON object.
     *
     * @param entry  The mask specifying the from and to paths for the data movement.
     * @param o      The original JSON object.
     * @param result The result JSON object.
     * @return The result JSON object after the data movement.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     * @throws JsonPointerException    If an error occurs due to JSON pointer issues.
     * @throws JsonPatchException      If an error occurs while applying JSON patches.
     */
    private Object processNonArrayToNonArray(Mask entry, Object o, Object result) throws JsonProcessingException, JsonPointerException, JsonPatchException {
        Object filedData = getFieldData(o, entry.getFrom());
        return patchUtil.setValueToPath(result, entry.getTo(), filedData);
    }

    /**
     * This method processes the movement of array data to an array location in a JSON object.
     * It gets the data from the from path in the original JSON object and sets it at the to path in the result JSON object.
     * If the to path does not exist in the result JSON object, it adds a placeholder value at the path.
     *
     * @param entry  The mask specifying the from and to paths for the data movement.
     * @param o      The original JSON object.
     * @param result The result JSON object.
     * @return The result JSON object after the data movement.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     * @throws JsonPointerException    If an error occurs due to JSON pointer issues.
     * @throws JsonPatchException      If an error occurs while applying JSON patches.
     */
    private Object processArrayToArrays(Mask entry, Object o, Object result) throws JsonProcessingException, JsonPointerException, JsonPatchException {
        String[] keys = entry.getFrom().split(Constants.ARRAY_IN_JSON_REGX);
        String[] values = entry.getTo().split(Constants.ARRAY_IN_JSON_REGX);

        List<Object> sourceObjects = JsonPath.read(o, keys[0]);

        List<Object> targetObjects;
        try {
            targetObjects = JsonPath.read(result, values[0]);
        } catch (PathNotFoundException e) {
            Object sampleObject = objectMapper.readValue("{}", Object.class);
            targetObjects = new ArrayList<>();
            for (int i = 0; i < sourceObjects.size(); i++) {
                targetObjects.add(sampleObject);
            }
            result = patchUtil.setValueToPath(result, values[0], new ArrayList<>());
        }

        List<Object> resultArray = new ArrayList<>();
        for (int i = 0; i < sourceObjects.size(); i++) {
            Object sourceObjectItem = sourceObjects.get(i);
            Object targetObjectItem = targetObjects.get(i);

            Object filedData = getFieldData(sourceObjectItem, "$" + keys[1]);

            try {
                JsonPath.read(targetObjectItem, "$" + values[1]);
            } catch (PathNotFoundException e) {
                targetObjectItem = patchUtil.setValueToPath(targetObjectItem, "$" + values[1], objectMapper.readValue("{}", Object.class));
            }
            targetObjectItem = patchUtil.setValueToPath(targetObjectItem, "$" + values[1], filedData);
            resultArray.add(targetObjectItem);
        }

        return patchUtil.setValueToPath(result, values[0], resultArray);
    }
    private Object processArrayToArrays2(Mask entry, Object o, Object result) throws JsonProcessingException, JsonPointerException, JsonPatchException {
        String[] keys = entry.getFrom().split(Constants.ARRAY_IN_JSON_REGX);
        String[] values = entry.getTo().split(Constants.ARRAY_IN_JSON_REGX);

        List<Object> sourceObjects = JsonPath.read(o, keys[0]);

        List<Object> targetObjects;
        try {
            targetObjects = JsonPath.read(result, values[0]);
        } catch (PathNotFoundException e) {
            Object sampleObject = objectMapper.readValue("{}", Object.class);
            targetObjects = new ArrayList<>();
            for (int i = 0; i < sourceObjects.size(); i++) {
                targetObjects.add(sampleObject);
            }
            result = patchUtil.setValueToPath(result, values[0], new ArrayList<>());
        }

        List<Object> resultArray = new ArrayList<>();
        for (int i = 0; i < sourceObjects.size(); i++) {
            Object sourceObjectItem = sourceObjects.get(i);
            Object targetObjectItem = targetObjects.get(i);

            Object filedData = getFieldData(sourceObjectItem, "$" + keys[1]);

            try {
                JsonPath.read(targetObjectItem, "$" + values[1]);
            } catch (PathNotFoundException e) {
                targetObjectItem = patchUtil.setValueToPath(targetObjectItem, "$" + values[1], objectMapper.readValue("{}", Object.class));
            }
            targetObjectItem = patchUtil.setValueToPath(targetObjectItem, "$" + values[1], filedData);
            resultArray.add(targetObjectItem);
        }

        return patchUtil.setValueToPath(result, values[0], resultArray);
    }
}