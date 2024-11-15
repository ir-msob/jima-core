package ir.msob.jima.core.ral.oracle.test;

import ir.msob.jima.core.beans.properties.JimaProperties;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * This class provides the configuration for setting up an OracleDB container for testing purposes.
 * It is annotated with @TestConfiguration to indicate that it is a source of bean definitions.
 * The proxyBeanMethods attribute is set to false to optimize runtime bean creation.
 */
@TestConfiguration(proxyBeanMethods = false)
public class OracleContainerConfiguration {

    /**
     * This method creates an OracleContainer bean for testing purposes.
     * It uses the DynamicPropertyRegistry to dynamically register properties for the Oracle container.
     * The properties include the driver class name, JDBC URL, username, and password for the Oracle container.
     * The JimaProperties object is used to get the Docker image name, username, and password for the Oracle container.
     * If the username and password are not blank, they are set to the Oracle container.
     * The @ServiceConnection annotation is used to indicate that this bean is used for establishing a connection to a service.
     *
     * @param registry       The DynamicPropertyRegistry used to dynamically register properties for the Oracle container.
     * @param jimaProperties The JimaProperties object used to get the Docker image name, username, and password for the Oracle container.
     * @return The created OracleContainer bean.
     */
    @Bean
    @ServiceConnection
    public OracleContainer kafkaContainer(DynamicPropertyRegistry registry, JimaProperties jimaProperties) {
        OracleContainer container = new OracleContainer(DockerImageName.parse(jimaProperties.getTestContainer().getOracle().getImage()));
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getOracle().getUsername()))
            container.withUsername(jimaProperties.getTestContainer().getOracle().getUsername());
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getOracle().getPassword()))
            container.withPassword(jimaProperties.getTestContainer().getOracle().getPassword());

        container.withReuse(jimaProperties.getTestContainer().getOracle().isReuse());

        registry.add("spring.datasource.driverClassName", container::getDriverClassName);
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        return container;
    }
}