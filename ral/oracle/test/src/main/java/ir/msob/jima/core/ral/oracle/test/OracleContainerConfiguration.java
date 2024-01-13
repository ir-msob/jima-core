package ir.msob.jima.core.ral.oracle.test;

import ir.msob.jima.core.test.testcontainer.BaseContainerConfiguration;
import ir.msob.jima.core.test.testcontainer.ContainerImageConstant;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * Configuration class for setting up a OracleDB container for integration testing.
 */
@TestConfiguration
public class OracleContainerConfiguration extends BaseContainerConfiguration {

    @Container
    public static final OracleContainer oracleContainer = new OracleContainer(DockerImageName.parse(ContainerImageConstant.ORACLE_DB_IMAGE));

    /**
     * Register dynamic properties for Spring Data OracleDB configuration.
     *
     * @param registry The DynamicPropertyRegistry for registering dynamic properties.
     */
    @DynamicPropertySource
    public static void registry(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.driverClassName", oracleContainer::getDriverClassName);
        registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
        registry.add("spring.datasource.username", oracleContainer::getUsername);
        registry.add("spring.datasource.password", oracleContainer::getPassword);
    }

    @Override
    protected GenericContainer<?> getContainer() {
        return oracleContainer;
    }
}
