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
 * Repository for working with Spring Expression Language (SpEL) expressions.
 */
@Component
@RequiredArgsConstructor
public class SpelRepository {
    private final BeanFactory beanFactory;
    private final StandardEvaluationContext standardEvaluationContext;
    private final ExpressionParser parser = new SpelExpressionParser();

    /**
     * Execute a SpEL expression and return the result of the specified type.
     */
    public <T> T execute(String expression, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext, desiredResultType);
    }

    /**
     * Execute a SpEL expression and return the result as an Object.
     */
    public Object execute(String expression) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext);
    }

    /**
     * Execute a SpEL expression with a root object and return the result of the specified type.
     */
    public <T> T execute(String expression, Object rootObject, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext, rootObject, desiredResultType);
    }

    /**
     * Execute a SpEL expression with a root object and return the result as an Object.
     */
    public Object execute(String expression, Object rootObject) {
        return parser.parseExpression(expression).getValue(standardEvaluationContext, rootObject);
    }

    /**
     * Execute a SpEL expression with provided variables and return the result of the specified type.
     */
    public <T> T execute(String expression, Map<String, Object> variables, Class<T> desiredResultType) {
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariables(variables);
        return parser.parseExpression(expression).getValue(context, desiredResultType);
    }

    /**
     * Execute a SpEL expression with provided variables and return the result as an Object.
     */
    public Object execute(String expression, Map<String, Object> variables) {
        StandardEvaluationContext context = StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
        context.setVariables(variables);
        return parser.parseExpression(expression).getValue(context);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and return the result of the specified type.
     */
    public <T> T execute(String expression, EvaluationContext context, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(context, desiredResultType);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and a root object.
     */
    public <T> T execute(String expression, EvaluationContext context, Object rootObject, Class<T> desiredResultType) {
        return parser.parseExpression(expression).getValue(context, rootObject, desiredResultType);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and return the result as an Object.
     */
    public Object execute(String expression, EvaluationContext context, Object rootObject) {
        return parser.parseExpression(expression).getValue(context, rootObject);
    }

    /**
     * Execute a SpEL expression with a custom evaluation context and return the result as an Object.
     */
    public Object execute(String expression, EvaluationContext context) {
        return parser.parseExpression(expression).getValue(context);
    }
}
