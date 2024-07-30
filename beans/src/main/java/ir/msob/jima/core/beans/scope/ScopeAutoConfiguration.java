package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "jima.scope.enabled", havingValue = "true")
public class ScopeAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLog(ScopeAutoConfiguration.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @PostConstruct
    public void startup(ApplicationContext applicationContext
            , BaseAsyncClient asyncClient
            , JimaProperties jimaProperties) {
        logger.info("Initializing ScopeSenderService with application name: {}", applicationName);
        ScopeScannerService scopeScannerService = new ScopeScannerService(applicationContext);
        ScopeSenderService scopeSenderService= new ScopeSenderService(scopeScannerService, asyncClient, jimaProperties, applicationName);
        scopeSenderService.send();
    }
}
