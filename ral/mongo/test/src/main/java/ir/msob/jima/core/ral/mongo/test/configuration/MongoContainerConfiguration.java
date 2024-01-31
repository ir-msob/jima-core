package ir.msob.jima.core.ral.mongo.test.configuration;

import ir.msob.jima.core.test.testcontainer.BaseContainerConfiguration;
import ir.msob.jima.core.test.testcontainer.ContainerImageConstant;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * Configuration class for setting up a MongoDB container for integration testing.
 */
@TestConfiguration
public class MongoContainerConfiguration extends BaseContainerConfiguration {

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse(ContainerImageConstant.MONGO_DB_IMAGE));

    /**
     * Register dynamic properties for Spring Data MongoDB configuration.
     *
     * @param registry The DynamicPropertyRegistry for registering dynamic properties.
     */
    @DynamicPropertySource
    public static void registry(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Override
    protected GenericContainer<?> getContainer() {
        return mongoDBContainer;
    }
}
