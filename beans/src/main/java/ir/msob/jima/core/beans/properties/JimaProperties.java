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

    /**
     * An instance of SearchProperties class.
     * It holds properties related to search.
     */
    private SearchProperties search = new SearchProperties();

    /**
     * An instance of HrefProperties class.
     * It holds properties related to href.
     */
    private HrefProperties href = new HrefProperties();

    /**
     * An instance of SignatureProperties class.
     * It holds properties related to signature.
     */
    private SignatureProperties signature = new SignatureProperties();

    /**
     * An instance of MessageProperties class.
     * It holds properties related to messages.
     */
    private MessageProperties message = new MessageProperties();

    /**
     * An instance of ScopeProperties class.
     * It holds properties related to scope.
     */
    private ScopeProperties scope = new ScopeProperties();
}