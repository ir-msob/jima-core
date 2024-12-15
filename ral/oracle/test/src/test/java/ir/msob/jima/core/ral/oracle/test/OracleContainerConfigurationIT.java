package ir.msob.jima.core.ral.oracle.test;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestMicroserviceApplication.class, OracleContainerConfiguration.class})
@ContextConfiguration
@Testcontainers
@CommonsLog
//@Disabled // TODO
class OracleContainerConfigurationIT {

    @Autowired
    OracleContainer container;

    @Value("${spring.datasource.driverClassName}")
    private String configDriverClassName;
    @Value("${spring.datasource.url}")
    private String configUrl;
    @Value("${spring.datasource.username}")
    private String configUsername;
    @Value("${spring.datasource.password}")
    private String configPassword;

    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(OracleContainer container) {
        return registry -> {
            OracleContainerConfiguration.registry(registry, container);
        };
    }

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
