package ir.msob.jima.core.beans.spel;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * This utility class is used for creating and configuring a StandardEvaluationContext for SpEL (Spring Expression Language) evaluations.
 * It provides a static method to prepare a StandardEvaluationContext with a BeanFactoryResolver.
 * The BeanFactoryResolver is used for resolving beans in SpEL expressions.
 */
public class StandardEvaluationContextUtil {
    // Private constructor to prevent instantiation of utility class
    private StandardEvaluationContextUtil() {
    }

    /**
     * This static method creates and configures a StandardEvaluationContext with a BeanFactoryResolver.
     * The BeanFactoryResolver is used for resolving beans in SpEL expressions.
     *
     * @param beanFactory The BeanFactory to be used for resolving beans in SpEL expressions.
     * @return A configured StandardEvaluationContext.
     */
    public static StandardEvaluationContext prepareStandardEvaluationContext(BeanFactory beanFactory) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        return standardEvaluationContext;
    }
}