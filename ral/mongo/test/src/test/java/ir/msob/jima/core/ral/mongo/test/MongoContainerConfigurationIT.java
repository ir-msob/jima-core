package ir.msob.jima.core.ral.mongo.test;

import ir.msob.jima.core.ral.mongo.test.configuration.MongoContainerConfiguration;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestMicroserviceApplication.class, MongoContainerConfiguration.class})
@ContextConfiguration
@Testcontainers
@CommonsLog
class MongoContainerConfigurationIT {

    @Autowired
    MongoDBContainer container;
    @Value("${spring.data.mongodb.uri}")
    private String configUrl;

    @Test
    @DisplayName("Container is running after initialization")
    void containerIsRunningAfterInitialization() {
        assertTrue(container.isRunning(), "Container should be running after initialization");
    }


    @Test
    @DisplayName("Properties are set correctly")
    void testContainerProperties() {
        String containerUrl = container.getReplicaSetUrl();
        assertEquals(containerUrl, configUrl);
    }

}
