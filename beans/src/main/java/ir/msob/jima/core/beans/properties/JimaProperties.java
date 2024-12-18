package ir.msob.jima.core.beans.properties;

import ir.msob.jima.core.commons.properties.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The {@code JimaProperties} class serves as a configuration holder for the Jima application.
 * <p>
 * This class is annotated with {@link Configuration} to indicate that it provides
 * bean definitions and is annotated with {@link ConfigurationProperties} to specify
 * the prefix used for properties in the application configuration files. It utilizes
 * Lombok annotations for automatic generation of getters, setters, and a no-argument
 * constructor, streamlining the management of application properties.
 * </p>
 * <p>
 * The properties defined in this class include various configurations childdomain to
 * method statistics, security, client settings, test containers, locking mechanisms,
 * search functionalities, href management, signature handling, messaging, scope,
 * and CRUD operations.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "jima")
public class JimaProperties {

    /**
     * Configuration properties childdomain to method statistics logging.
     */
    private MethodStatsProperties methodStats = new MethodStatsProperties();

    /**
     * Configuration properties childdomain to application security settings.
     */
    private SecurityProperties security = new SecurityProperties();

    /**
     * Configuration properties childdomain to client-specific settings.
     */
    private ClientProperties client = new ClientProperties();

    /**
     * Configuration properties for test container management.
     */
    private TestContainerProperties testContainer = new TestContainerProperties();

    /**
     * Configuration properties childdomain to locking mechanisms.
     */
    private LockProperties lock = new LockProperties();

    /**
     * Configuration properties childdomain to search functionalities.
     */
    private SearchProperties search = new SearchProperties();

    /**
     * Configuration properties for managing href settings.
     */
    private HrefProperties href = new HrefProperties();

    /**
     * Configuration properties childdomain to signature handling.
     */
    private SignatureProperties signature = new SignatureProperties();

    /**
     * Configuration properties for message handling within the application.
     */
    private MessageProperties message = new MessageProperties();

    /**
     * Configuration properties childdomain to scope management.
     */
    private ScopeProperties scope = new ScopeProperties();

    /**
     * Configuration properties childdomain to CRUD operations.
     */
    private CrudProperties crud = new CrudProperties();
}