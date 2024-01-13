package ir.msob.jima.core.commons.util.mask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import ir.msob.jima.core.commons.util.jsonpatch.PatchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A utility class for masking JSON data based on specified masks.
 */
@Component
@RequiredArgsConstructor
public class JsonMask {
    private static final Logger log = LoggerFactory.getLog(JsonMask.class);
    private final ObjectMapper objectMapper;
    private final PatchUtil patchUtil;

    private static Object getFieldData(Object o, String fieldPath) {
        try {
            return JsonPath.read(o, fieldPath);
        } catch (PathNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Masks the given JSON data according to the provided masks.
     *
     * @param mask The collection of masks to apply to the JSON data.
     * @param o    The JSON data to be masked.
     * @return The masked JSON data.
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

    public Object moveData(Mask entry, Object o, Object result) throws JsonPatchException, JsonPointerException, JsonProcessingException {
        if (entry.getFrom().contains(Constants.ARRAY_IN_JSON) && entry.getTo().contains(Constants.ARRAY_IN_JSON)) {
            return processArrayToArrays(entry, o, result);
        } else if (!entry.getFrom().contains(Constants.ARRAY_IN_JSON) && !entry.getTo().contains(Constants.ARRAY_IN_JSON)) {
            return processNonArrayToNonArray(entry, o, result);
        }
        throw new CommonRuntimeException("Mask is invalid, key: {}, value: {}", entry.getFrom(), entry.getTo());
    }

    private Object processNonArrayToNonArray(Mask entry, Object o, Object result) throws JsonProcessingException, JsonPointerException, JsonPatchException {
        Object filedData = getFieldData(o, entry.getFrom());
        return patchUtil.setValueToPath(result, entry.getTo(), filedData);
    }

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
}
