package ir.msob.jima.core.ral.minio.test;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestMicroserviceApplication.class, MinIOContainerConfiguration.class})
@ContextConfiguration
@Testcontainers
@CommonsLog
class MinioContainerConfigurationIT {

    @Autowired
    MinIOContainer container;
    @Value("${spring.minio.url}")
    private String configUrl;
    @Value("${spring.minio.access-key}")
    private String configAccessKey;
    @Value("${spring.minio.secret-key}")
    private String configSecretKey;


    @Test
    @DisplayName("Container is running after initialization")
    void containerIsRunningAfterInitialization() {
        assertTrue(container.isRunning(), "Container should be running after initialization");
    }

    @Test
    @DisplayName("Properties are set correctly")
    void testContainerProperties() {
        String containerUrl = container.getS3URL();
        String accessKey = container.getUserName();
        String secretKey = container.getPassword();
        assertEquals(containerUrl, configUrl);
        assertEquals(accessKey, configAccessKey);
        assertEquals(secretKey, configSecretKey);

    }

}
