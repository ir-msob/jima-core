package ir.msob.jima.core.ral.postgresql.test;

import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.util.Strings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * This class provides the configuration for setting up a PostgresSQL container for testing purposes.
 * It is annotated with @TestConfiguration to indicate that it is a source of bean definitions.
 * The proxyBeanMethods attribute is set to false to optimize runtime bean creation.
 */
@TestConfiguration(proxyBeanMethods = false)
public class PostgreSQLContainerConfiguration {

    public static void registry(DynamicPropertyRegistry registry, PostgreSQLContainer container) {
        registry.add("spring.datasource.driverClassName", container::getDriverClassName);
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);

        String r2dbcUrl = String.format(
                "r2dbc:postgresql://%s:%d/%s",
                container.getHost(),
                container.getFirstMappedPort(),
                container.getDatabaseName()
        );
        registry.add("spring.r2dbc.driverClassName", container::getDriverClassName);
        registry.add("spring.r2dbc.url", () -> r2dbcUrl);
        registry.add("spring.r2dbc.username", container::getUsername);
        registry.add("spring.r2dbc.password", container::getPassword);
    }

    /**
     * Creates a PostgreSQLContainer bean for testing.
     *
     * @param jimaProperties The JimaProperties object containing test container configuration.
     * @return The created PostgreSQLContainer bean.
     */
    @Bean
    public PostgreSQLContainer postgreSQLContainer(JimaProperties jimaProperties) {
        PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse(jimaProperties.getTestContainer().getPostgreSQL().getImage()));
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getPostgreSQL().getUsername()))
            container.withUsername(jimaProperties.getTestContainer().getPostgreSQL().getUsername());
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getPostgreSQL().getPassword()))
            container.withPassword(jimaProperties.getTestContainer().getPostgreSQL().getPassword());

        container.withReuse(jimaProperties.getTestContainer().getPostgreSQL().isReuse());
        return container;
    }
}
