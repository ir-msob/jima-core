package ir.msob.jima.core.ral.oracle.test;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import ir.msob.jima.core.beans.properties.JimaProperties;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Objects;

/**
 * This class provides the configuration for setting up an OracleDB container for testing purposes.
 * It is annotated with @TestConfiguration to indicate that it is a source of bean definitions.
 * The proxyBeanMethods attribute is set to false to optimize runtime bean creation.
 */
@TestConfiguration(proxyBeanMethods = false)
public class OracleContainerConfiguration {

    public static void registry(DynamicPropertyRegistry registry, OracleContainer container) {
        registry.add("spring.datasource.driverClassName", container::getDriverClassName);
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    /**
     * This method creates an OracleContainer bean for testing purposes.
     * It uses the DynamicPropertyRegistry to dynamically register properties for the Oracle container.
     * The properties include the driver class name, JDBC URL, username, and password for the Oracle container.
     * The JimaProperties object is used to get the Docker image name, username, and password for the Oracle container.
     * If the username and password are not blank, they are set to the Oracle container.
     * The @ServiceConnection annotation is used to indicate that this bean is used for establishing a connection to a service.
     *
     * @param jimaProperties The JimaProperties object used to get the Docker image name, username, and password for the Oracle container.
     * @return The created OracleContainer bean.
     */
    @Bean
    public OracleContainer kafkaContainer(JimaProperties jimaProperties) {
        OracleContainer container = new OracleContainer(DockerImageName.parse(jimaProperties.getTestContainer().getOracle().getImage()));
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getOracle().getUsername()))
            container.withUsername(jimaProperties.getTestContainer().getOracle().getUsername());
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getOracle().getPassword()))
            container.withPassword(jimaProperties.getTestContainer().getOracle().getPassword());

        container.withReuse(jimaProperties.getTestContainer().getOracle().isReuse());

        if (Strings.isNotBlank(jimaProperties.getTestContainer().getOracle().getContainer())
                && jimaProperties.getTestContainer().getOracle().getHostPort() != null
                && jimaProperties.getTestContainer().getOracle().getContainerPort() != null) {

            container.withCreateContainerCmdModifier(cmd -> {
                cmd.withName(jimaProperties.getTestContainer().getOracle().getContainer());
                Objects.requireNonNull(cmd.getHostConfig()).withPortBindings(
                        new PortBinding(
                                Ports.Binding.bindPort(jimaProperties.getTestContainer().getOracle().getHostPort()),
                                new ExposedPort(jimaProperties.getTestContainer().getOracle().getContainerPort())
                        )
                );
            });
        }

        return container;
    }
}