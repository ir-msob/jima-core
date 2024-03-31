package ir.msob.jima.core.beans.properties;

import ir.msob.jima.core.commons.properties.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This class represents the properties for the Jima application.
 * It is annotated with @Configuration to indicate that it is a source of bean definitions.
 * The @ConfigurationProperties annotation is used to specify the prefix used in the properties file.
 * It uses Lombok annotations for automatic generation of getters, setters, and a no-argument constructor.
 */
@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "jima")
public class JimaProperties {

    /**
     * An instance of MethodStatsProperties class.
     * It holds properties related to method statistics logging.
     */
    private MethodStatsProperties methodStats = new MethodStatsProperties();

    /**
     * An instance of SecurityProperties class.
     * It holds security-related properties.
     */
    private SecurityProperties security = new SecurityProperties();

    /**
     * An instance of ClientProperties class.
     * It holds client-related properties.
     */
    private ClientProperties client = new ClientProperties();

    /**
     * An instance of TestContainerProperties class.
     * It holds test container-related properties.
     */
    private TestContainerProperties testContainer = new TestContainerProperties();

    /**
     * An instance of LockProperties class.
     * It holds properties related to locking.
     */
    private LockProperties lock = new LockProperties();


    private SearchProperties search = new SearchProperties();

    private HrefProperties href = new HrefProperties();
}