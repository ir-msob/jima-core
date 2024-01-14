package ir.msob.jima.core.beans.spel;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * This configuration class is responsible for setting up the Spring Expression Language (SpEL) evaluation context.
 * It is marked with the `@Configuration` annotation to indicate that it is a source of bean definitions.
 * The `@Bean` annotation is used to declare a bean to be managed by the Spring container.
 */
@Configuration
public class SpelConfiguration {

    /**
     * This method creates a StandardEvaluationContext bean for evaluating SpEL expressions.
     * The context is prepared using a provided BeanFactory.
     *
     * @param beanFactory The Spring BeanFactory used to prepare the evaluation context.
     * @return A StandardEvaluationContext bean for SpEL expressions.
     */
    @Bean
    public StandardEvaluationContext standardEvaluationContext(BeanFactory beanFactory) {
        return StandardEvaluationContextUtil.prepareStandardEvaluationContext(beanFactory);
    }
}