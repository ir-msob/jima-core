package ir.msob.jima.core.commons.util;

import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Utility class for accessing beans in simple classes.
 * <p>
 * This class allows you to access beans from the application context in classes that are not managed by Spring.
 * It implements the ApplicationContextAware interface to set the application context when the Spring container starts.
 * You can use the static `getBean` method to retrieve beans by their class type.
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    /**
     * Get a bean by its class type.
     *
     * @param beanClass The class type of the bean to retrieve.
     * @param <T>       The type of the bean.
     * @return The bean instance of the specified class type.
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    /**
     * Sets the application context.
     *
     * @param applicationContext The Spring application context to set.
     * @throws BeansException If an error occurs while setting the application context.
     */
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        BeanUtil.applicationContext = applicationContext;
    }
}
