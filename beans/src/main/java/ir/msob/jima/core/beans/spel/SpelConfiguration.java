package ir.msob.jima.core.beans.spel;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Configuration for creating a standard SpEL evaluation context.
 * <p>
 * This configuration class defines a Spring Bean that provides a standard SpEL (Spring Expression Language)
 * evaluation context, which is used for evaluating SpEL expressions in the application.
 */
@Configuration
public class SpelConfiguration {

    /**
     * Creates a standard SpEL evaluation context bean.
     *
     * @param beanFactory The Spring BeanFactory used to prepare the evaluation context.
     * @return A StandardEvaluationContext bean for SpEL expressions.
     */
    @Bean
    public StandardEvaluationContext standardEvaluationContext(BeanFactory beanFactory) {
        return StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
    }
}
