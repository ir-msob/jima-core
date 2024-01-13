package ir.msob.jima.core.ral.minio.test;

import ir.msob.jima.core.test.testcontainer.BaseContainerConfiguration;
import ir.msob.jima.core.test.testcontainer.ContainerImageConstant;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * Test configuration class for setting up a MinIOContainer for integration tests.
 */
@TestConfiguration
public class MinIOContainerConfiguration extends BaseContainerConfiguration {

    // Create a MinIOContainer with the specified Docker image and credentials
    @Container
    public static final MinIOContainer minIOContainer = new MinIOContainer(DockerImageName.parse(ContainerImageConstant.MIN_IO_IMAGE))
            .withUserName("username")
            .withPassword("password");

    /**
     * Configure dynamic properties based on the MinIOContainer for use in tests.
     *
     * @param registry The DynamicPropertyRegistry for registering dynamic properties.
     */
    @DynamicPropertySource
    public static void registry(DynamicPropertyRegistry registry) {
        registry.add("spring.minio.url", minIOContainer::getS3URL);
        registry.add("spring.minio.access-key", minIOContainer::getUserName);
        registry.add("spring.minio.secret-key", minIOContainer::getPassword);
    }

    /**
     * Get the configured MinIOContainer instance.
     *
     * @return The MinIOContainer instance configured for tests.
     */
    @Override
    protected GenericContainer<?> getContainer() {
        return minIOContainer;
    }
}
