package ir.msob.jima.core.ral.postgresql.test;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestApplication.class, PostgreSQLContainerConfiguration.class})
@ContextConfiguration
@Testcontainers
@CommonsLog
class PostgreSQLContainerConfigurationIT {

    @Autowired
    PostgreSQLContainer<?> container;

    @Value("${spring.datasource.driverClassName}")
    private String configDriverClassName;
    @Value("${spring.datasource.url}")
    private String configUrl;
    @Value("${spring.datasource.username}")
    private String configUsername;
    @Value("${spring.datasource.password}")
    private String configPassword;

    @Test
    @DisplayName("Container is running after initialization")
    void containerIsRunningAfterInitialization() {
        assertTrue(container.isRunning(), "Container should be running after initialization");
    }


    @Test
    @DisplayName("Properties are set correctly")
    void testContainerProperties() {
        String driverClassName = container.getDriverClassName();
        String url = container.getJdbcUrl();
        String username = container.getUsername();
        String password = container.getPassword();
        assertEquals(driverClassName, configDriverClassName);
        assertEquals(url, configUrl);
        assertEquals(username, configUsername);
        assertEquals(password, configPassword);
    }

}
