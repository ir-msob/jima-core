package ir.msob.jima.core.beans.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The `JimaConfigProperties` class is a configuration class that holds the properties for the Jima Core module.
 * It is annotated with `@Configuration` to indicate that it is a source of bean definitions.
 * The `@ConfigurationProperties` annotation is used to specify the prefix used in the properties file.
 * It uses Lombok annotations for automatic generation of getters, setters, and a no-argument constructor.
 * The `methodStats` field is an instance of `MethodStatsProperties` class, which holds properties related to method statistics logging.
 * The `security` field is an instance of `SecurityProperties` class, which holds security-related properties.
 * The `client` field is an instance of `ClientProperties` class, which holds client-related properties.
 */
@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "jima")
public class JimaConfigProperties {
    /**
     * The `methodStats` field is an instance of `MethodStatsProperties` class.
     * It holds properties related to method statistics logging.
     */
    private MethodStatsProperties methodStats = new MethodStatsProperties();

    /**
     * The `security` field is an instance of `SecurityProperties` class.
     * It holds security-related properties.
     */
    private SecurityProperties security = new SecurityProperties();

    /**
     * The `client` field is an instance of `ClientProperties` class.
     * It holds client-related properties.
     */
    private ClientProperties client = new ClientProperties();
}