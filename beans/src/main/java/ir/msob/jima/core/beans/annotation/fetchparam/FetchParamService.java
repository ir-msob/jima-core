package ir.msob.jima.core.beans.annotation.fetchparam;

import ir.msob.jima.core.beans.spel.SpelRepository;
import ir.msob.jima.core.beans.spel.StandardEvaluationContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The 'FetchParamService' class is a service for fetching values using SpEL (Spring Expression Language) expressions and parameters.
 * It is annotated with '@Service', indicating that it is a Spring-managed service bean.
 * It uses the '@RequiredArgsConstructor' annotation from Lombok to automatically generate a constructor with required arguments.
 * The class provides methods to fetch values using SpEL expressions and parameters, and return the results.
 */
@Service
@RequiredArgsConstructor
public class FetchParamService {

    // The SpelRepository used for executing SpEL expressions
    private final SpelRepository spelRepository;
    // The BeanFactory used for preparing the evaluation context
    private final BeanFactory beanFactory;

    /**
     * Fetch a value using a SpEL expression and a parameter name and value.
     *
     * @param expression The SpEL expression to execute.
     * @param paramName  The name of the parameter.
     * @param paramValue The value of the parameter.
     * @return The fetched value as an Object.
     */
    public Object fetch(String expression, String paramName, Object paramValue) {
        return fetch(expression, paramName, paramValue, Object.class);
    }

    /**
     * Fetch a value using a SpEL expression, a parameter name, and value, and specify the result type.
     *
     * @param expression The SpEL expression to execute.
     * @param paramName  The name of the parameter.
     * @param paramValue The value of the parameter.
     * @param resultType The desired result type.
     * @param <T>        The generic result type.
     * @return The fetched value with the specified result type.
     */
    public <T> T fetch(String expression, String paramName, Object paramValue, Class<T> resultType) {
        Map<String, Object> map = new HashMap<>();
        map.put(paramName, paramValue);

        return fetch(expression, map, resultType);
    }

    /**
     * Fetch a value using a SpEL expression and a map of parameters.
     *
     * @param expression The SpEL expression to execute.
     * @param map        A map of parameters.
     * @return The fetched value as an Object.
     */
    public Object fetch(String expression, Map<String, Object> map) {
        return fetch(expression, map, Object.class);
    }

    /**
     * Fetch a value using a SpEL expression, a map of parameters, and specify the result type.
     *
     * @param expression The SpEL expression to execute.
     * @param map        A map of parameters.
     * @param resultType The desired result type.
     * @param <T>        The generic result type.
     * @return The fetched value with the specified result type.
     */
    public <T> T fetch(String expression, Map<String, Object> map, Class<T> resultType) {
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariables(map);

        return spelRepository.execute(expression, context, map, resultType);
    }

}