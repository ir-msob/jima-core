package ir.msob.jima.core.beans.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This class holds the configuration properties for the Jima Core module.
 * It is marked with the `@Configuration` annotation to indicate that it is a source of bean definitions.
 * The `@ConfigurationProperties` annotation is used to specify the prefix used in the properties file.
 */
@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "jima.core")
public class CoreConfigProperties {
    /**
     * The properties related to method statistics logging.
     */
    private MethodStatsProperties methodStats = new MethodStatsProperties();
}