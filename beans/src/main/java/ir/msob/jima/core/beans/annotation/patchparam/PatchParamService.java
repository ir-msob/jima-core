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
 */
@Service
@RequiredArgsConstructor
public class PatchParamService {
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
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariable(paramName, paramValue);

        Map<String, Object> map = new HashMap<>();
        map.put(paramName, paramValue);

        Object result = spelRepository.execute(patchParam.value(), context, map);

        JsonPatch jsonPatch = patchUtil.prepareAddJsonPatch(patchParam.path(), result);

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
        List<Object> result = new ArrayList<>();
        for (Object paramValue : paramValues) {
            result.add(patch(patchParams, paramName, paramValue, paramValueClass));
        }
        return result;
    }

    private Object applyJsonPatch(JsonPatch jsonPatch, Object o, Class<?> clazz)
            throws JsonPatchException, JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(o, JsonNode.class));
        return objectMapper.treeToValue(patched, clazz);
    }
}
