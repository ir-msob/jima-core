package ir.msob.jima.core.beans.scope;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the scope-related services.
 * This configuration is activated when the property 'jima.scope.enabled' is set to true.
 * It initializes the ScopeSenderService upon application startup.
 */
@Configuration
@ConditionalOnProperty(name = "jima.scope.enabled", havingValue = "true")
public class ScopeAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLog(ScopeAutoConfiguration.class);

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * Initializes the ScopeSenderService with the provided application context,
     * asynchronous client, and Jima properties. This method is called after the
     * bean's properties have been set.
     *
     * @param applicationContext the application context
     * @param asyncClient        the asynchronous client
     * @param jimaProperties     the Jima properties
     */
    @PostConstruct
    public void startup(ApplicationContext applicationContext
            , BaseAsyncClient asyncClient
            , JimaProperties jimaProperties) throws JsonProcessingException {
        logger.info("Initializing ScopeSenderService with application name: {}", applicationName);
        ScopeScannerService scopeScannerService = new ScopeScannerService(applicationContext);
        ScopeSenderService scopeSenderService = new ScopeSenderService(scopeScannerService, asyncClient, jimaProperties, applicationName);
        scopeSenderService.send();
    }
}
