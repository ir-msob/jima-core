package ir.msob.jima.core.beans.spel;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Utility class for creating and configuring a StandardEvaluationContext for SpEL (Spring Expression Language) evaluations.
 */
public class StandardEvaluationContextUtil {
    private StandardEvaluationContextUtil() {
    }

    /**
     * Creates and configures a StandardEvaluationContext with a BeanFactoryResolver.
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
