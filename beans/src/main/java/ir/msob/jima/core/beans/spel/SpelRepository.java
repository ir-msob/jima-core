package ir.msob.jima.core.beans.spel;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This class is a repository for working with Spring Expression Language (SpEL) expressions.
 * It is marked with the `@Component` annotation to indicate that it is a Spring-managed bean.
 * It uses the `@RequiredArgsConstructor` annotation from Lombok to automatically generate a constructor with required arguments.
 * The class provides methods to execute SpEL expressions and return the results.
 */
@Component
@RequiredArgsConstructor
public class SpelRepository {
    // The Spring BeanFactory used to prepare the evaluation context
    private final BeanFactory beanFactory;
    // The StandardEvaluationContext used for evaluating SpEL expressions
    private final StandardEvaluationContext standardEvaluationContext;
    // The ExpressionParser used to parse SpEL expressions
    private final ExpressionParser parser = new SpelExpressionParser();

    /**
     * Execute a SpEL expression and return the result of the specified type.
     *
     * @param expression The SpEL expression to execute.
     * @param desiredResultType The desired result type.
     * @return The result of the SpEL expression execution.
     * @param <T>   the type of the result.
     */
    public <T> T execute(String expression, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext, desiredResultType);
    }

    /**
     * Execute a SpEL expression and return the result as an Object.
     *
     * @param expression The SpEL expression to execute.
     * @return The result of the SpEL expression execution.
     */
    public Object execute(String expression) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext);
    }

    /**
     * Execute a SpEL expression with a root object and return the result of the specified type.
     *
     * @param expression The SpEL expression to execute.
     * @param rootObject The root object for the SpEL expression.
     * @param desiredResultType The desired result type.
     * @return The result of the SpEL expression execution.
     * @param <T>   the type of the result.
     */
    public <T> T execute(String expression, Object rootObject, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext, rootObject, desiredResultType);
    }

    /**
     * Execute a SpEL expression with a root object and return the result as an Object.
     *
     * @param expression The SpEL expression to execute.
     * @param rootObject The root object for the SpEL expression.
     * @return The result of the SpEL expression execution.
     */
    public Object execute(String expression, Object rootObject) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext, rootObject);
    }

    /**
     * Execute a SpEL expression with provided variables and return the result of the specified type.
     *
     * @param expression The SpEL expression to execute.
     * @param variables The variables to be used in the SpEL expression.
     * @param desiredResultType The desired result type.
     * @return The result of the SpEL expression execution.
     * @param <T>   the type of the result.
     */
    public <T> T execute(String expression, Map<String, Object> variables, Class<T> desiredResultType) {
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariables(variables);
        return parser.parseExpression(expression).getValue(context, desiredResultType);
    }

    /**
     * Execute a SpEL expression with provided variables and return the result as an Object.
     *
     * @param expression The SpEL expression to execute.
     * @param variables The variables to be used in the SpEL expression.
     * @return The result of the SpEL expression execution.
     */
    public Object execute(String expression, Map<String, Object> variables) {
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariables(variables);
        return parser.parseExpression(expression).getValue(context);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and return the result of the specified type.
     *
     * @param expression The SpEL expression to execute.
     * @param context The custom evaluation context for the SpEL expression.
     * @param desiredResultType The desired result type.
     * @return The result of the SpEL expression execution.
     * @param <T>   the type of the result.
     */
    public <T> T execute(String expression, EvaluationContext context, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(context, desiredResultType);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and a root object.
     *
     * @param expression The SpEL expression to execute.
     * @param context The custom evaluation context for the SpEL expression.
     * @param rootObject The root object for the SpEL expression.
     * @param desiredResultType The desired result type.
     * @return The result of the SpEL expression execution.
     * @param <T>   the type of the result.
     */
    public <T> T execute(String expression, EvaluationContext context, Object rootObject, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(context, rootObject, desiredResultType);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and return the result as an Object.
     *
     * @param expression The SpEL expression to execute.
     * @param context The custom evaluation context for the SpEL expression.
     * @param rootObject The root object for the SpEL expression.
     * @return The result of the SpEL expression execution.
     */
    public Object execute(String expression, EvaluationContext context, Object rootObject) {
        return parser.parseExpression(expression).getValue(context, rootObject);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and return the result as an Object.
     *
     * @param expression The SpEL expression to execute.
     * @param context The custom evaluation context for the SpEL expression.
     * @return The result of the SpEL expression execution.
     */
    public Object execute(String expression, EvaluationContext context) {
        return parser.parseExpression(expression).getValue(context);
    }
}