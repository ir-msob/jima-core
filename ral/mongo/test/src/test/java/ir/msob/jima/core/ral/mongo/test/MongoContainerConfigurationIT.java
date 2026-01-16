package ir.msob.jima.core.ral.mongo.test;

import ir.msob.jima.core.ral.mongo.test.configuration.MongoContainerConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestApplication.class, MongoContainerConfiguration.class})
@ContextConfiguration
@Testcontainers
class MongoContainerConfigurationIT {

    @Autowired
    MongoDBContainer container;
    @Value("${spring.mongodb.uri}")
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
