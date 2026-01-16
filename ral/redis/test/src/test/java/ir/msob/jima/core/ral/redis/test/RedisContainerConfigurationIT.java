package ir.msob.jima.core.ral.redis.test;

import com.redis.testcontainers.RedisContainer;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestApplication.class, RedisContainerConfiguration.class})
@ContextConfiguration
@Testcontainers
@CommonsLog
class RedisContainerConfigurationIT {

    @Autowired
    RedisContainer container;

    @Value("${spring.data.redis.url}")
    private String configUrl;
    @Value("${spring.data.redis.host}")
    private String configHost;
    @Value("${spring.data.redis.port}")
    private Integer configPort;

    @Test
    @DisplayName("Container is running after initialization")
    void containerIsRunningAfterInitialization() {
        Assertions.assertTrue(container.isRunning(), "Container should be running after initialization");
    }


    @Test
    @DisplayName("Properties are set correctly")
    void testContainerProperties() {
        String url = container.getRedisURI();
        String host = container.getHost();
        Integer port = container.getExposedPorts().stream().findFirst().orElse(null);
        assertEquals(url, configUrl);
        assertEquals(host, configHost);
        assertEquals(port, configPort);
    }

}
