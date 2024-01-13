package ir.msob.jima.core.beans.annotation.fetchparam;

import ir.msob.jima.core.beans.spel.SpelRepository;
import ir.msob.jima.core.beans.spel.StandardEvaluationContextUtil;
import ir.msob.jima.core.commons.annotation.fetchparam.FetchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A service for fetching values using SpEL expressions and parameters.
 */
@Service
@RequiredArgsConstructor
public class FetchParamService {

    private final SpelRepository spelRepository;
    private final BeanFactory beanFactory;

    /**
     * Fetch a value using the specified FetchParam and parameter name and value.
     *
     * @param fetchParam The FetchParam annotation
     * @param paramName  The name of the parameter
     * @param paramValue The value of the parameter
     * @return The fetched value as an Object
     */
    public Object fetch(FetchParam fetchParam, String paramName, Object paramValue) {
        return fetch(fetchParam, paramName, paramValue, Object.class);
    }

    /**
     * Fetch a value using the specified FetchParam, parameter name, and value, and specify the result type.
     *
     * @param fetchParam The FetchParam annotation
     * @param paramName  The name of the parameter
     * @param paramValue The value of the parameter
     * @param resultType The desired result type
     * @param <T>        The generic result type
     * @return The fetched value with the specified result type
     */
    public <T> T fetch(FetchParam fetchParam, String paramName, Object paramValue, Class<T> resultType) {
        Map<String, Object> map = new HashMap<>();
        map.put(paramName, paramValue);

        return fetch(fetchParam, map, resultType);
    }

    /**
     * Fetch a value using the specified FetchParam and a map of parameters.
     *
     * @param fetchParam The FetchParam annotation
     * @param map        A map of parameters
     * @return The fetched value as an Object
     */
    public Object fetch(FetchParam fetchParam, Map<String, Object> map) {
        return fetch(fetchParam, map, Object.class);
    }

    /**
     * Fetch a value using the specified FetchParam, a map of parameters, and specify the result type.
     *
     * @param fetchParam The FetchParam annotation
     * @param map        A map of parameters
     * @param resultType The desired result type
     * @param <T>        The generic result type
     * @return The fetched value with the specified result type
     */
    public <T> T fetch(FetchParam fetchParam, Map<String, Object> map, Class<T> resultType) {
        return fetch(fetchParam.value(), map, resultType);
    }

    /**
     * Fetch a value using a SpEL expression, a map of parameters, and specify the result type.
     *
     * @param expression The SpEL expression
     * @param map        A map of parameters
     * @param resultType The desired result type
     * @param <T>        The generic result type
     * @return The fetched value with the specified result type
     */
    public <T> T fetch(String expression, Map<String, Object> map, Class<T> resultType) {
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariables(map);

        return spelRepository.execute(expression, context, map, resultType);
    }

    /**
     * Fetch multiple values using a collection of FetchParam annotations and a map of parameters.
     *
     * @param fetchParams A collection of FetchParam annotations
     * @param map         A map of parameters
     * @return A map of fetched values with names as keys
     */
    public Map<String, Object> fetch(Collection<FetchParam> fetchParams, Map<String, Object> map) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (FetchParam fetchParam : fetchParams) {
            result.put(fetchParam.name(), fetch(fetchParam, map));
        }
        return result;
    }
}
