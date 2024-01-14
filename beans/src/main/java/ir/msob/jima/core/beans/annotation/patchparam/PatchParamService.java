package ir.msob.jima.core.beans.annotation.patchparam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import ir.msob.jima.core.beans.spel.SpelRepository;
import ir.msob.jima.core.beans.spel.StandardEvaluationContextUtil;
import ir.msob.jima.core.commons.annotation.patchparam.PatchParam;
import ir.msob.jima.core.commons.util.jsonpatch.PatchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This service class provides the functionality to apply JSON patches to objects using SpEL expressions.
 * It is marked as a Spring service to be detected during classpath scanning.
 */
@Service
@RequiredArgsConstructor
public class PatchParamService {
    // Dependencies for this service
    private final SpelRepository spelRepository;
    private final BeanFactory beanFactory;
    private final ObjectMapper objectMapper;
    private final PatchUtil patchUtil;

    /**
     * Apply a single JSON patch to the provided object using SpEL expressions.
     *
     * @param patchParam      The PatchParam annotation specifying the patching logic.
     * @param paramName       The name of the parameter used in SpEL expressions.
     * @param paramValue      The original object to be patched.
     * @param paramValueClass The class of the original object.
     * @return The patched object.
     * @throws JsonPointerException    If there is an issue with the JSON pointer in the patch.
     * @throws JsonPatchException      If there is an issue with applying the JSON patch.
     * @throws JsonProcessingException If there is an issue with JSON processing.
     */
    public Object patch(PatchParam patchParam, String paramName, Object paramValue, Class<?> paramValueClass)
            throws JsonPointerException, JsonPatchException, JsonProcessingException {
        // Prepare the context for SpEL evaluation
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariable(paramName, paramValue);

        // Prepare the map for SpEL evaluation
        Map<String, Object> map = new HashMap<>();
        map.put(paramName, paramValue);

        // Evaluate the SpEL expression and get the result
        Object result = spelRepository.execute(patchParam.value(), context, map);

        // Prepare the JSON patch
        JsonPatch jsonPatch = patchUtil.prepareAddJsonPatch(patchParam.path(), result);

        // Apply the JSON patch and return the result
        return applyJsonPatch(jsonPatch, paramValue, paramValueClass);
    }

    /**
     * Apply a collection of JSON patches to the provided object using SpEL expressions.
     *
     * @param patchParams     The collection of PatchParam annotations specifying the patching logic.
     * @param paramName       The name of the parameter used in SpEL expressions.
     * @param paramValue      The original object to be patched.
     * @param paramValueClass The class of the original object.
     * @return The patched object.
     * @throws JsonPatchException      If there is an issue with applying the JSON patches.
     * @throws JsonPointerException    If there is an issue with the JSON pointer in the patches.
     * @throws JsonProcessingException If there is an issue with JSON processing.
     */
    public Object patch(Collection<PatchParam> patchParams, String paramName, Object paramValue, Class<?> paramValueClass)
            throws JsonPatchException, JsonPointerException, JsonProcessingException {
        // Apply each patch in the collection
        for (PatchParam patchParam : patchParams) {
            paramValue = patch(patchParam, paramName, paramValue, paramValueClass);
        }
        return paramValue;
    }

    /**
     * Apply a collection of JSON patches to a collection of objects using SpEL expressions.
     *
     * @param patchParams     The collection of PatchParam annotations specifying the patching logic.
     * @param paramName       The name of the parameter used in SpEL expressions.
     * @param paramValues     The original collection of objects to be patched.
     * @param paramValueClass The class of the original objects.
     * @return A collection of patched objects.
     * @throws JsonPatchException      If there is an issue with applying the JSON patches.
     * @throws JsonPointerException    If there is an issue with the JSON pointer in the patches.
     * @throws JsonProcessingException If there is an issue with JSON processing.
     */
    public Object patchMany(Collection<PatchParam> patchParams, String paramName, Collection<Object> paramValues, Class<?> paramValueClass)
            throws JsonPatchException, JsonPointerException, JsonProcessingException {
        // Prepare the result collection
        List<Object> result = new ArrayList<>();
        // Apply the patches to each object in the collection
        for (Object paramValue : paramValues) {
            result.add(patch(patchParams, paramName, paramValue, paramValueClass));
        }
        return result;
    }

    /**
     * Apply a JSON patch to an object and return the result.
     *
     * @param jsonPatch The JSON patch to apply.
     * @param o         The original object to be patched.
     * @param clazz     The class of the original object.
     * @return The patched object.
     * @throws JsonPatchException      If there is an issue with applying the JSON patch.
     * @throws JsonProcessingException If there is an issue with JSON processing.
     */
    private Object applyJsonPatch(JsonPatch jsonPatch, Object o, Class<?> clazz)
            throws JsonPatchException, JsonProcessingException {
        // Apply the JSON patch
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(o, JsonNode.class));
        // Convert the patched JSON node back to the original object's class and return the result
        return objectMapper.treeToValue(patched, clazz);
    }
}