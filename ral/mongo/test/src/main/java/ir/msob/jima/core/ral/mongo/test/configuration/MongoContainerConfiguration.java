package ir.msob.jima.core.ral.mongo.test.configuration;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import ir.msob.jima.core.beans.properties.JimaProperties;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Objects;

/**
 * This class provides the configuration for setting up a MongoDB container for testing purposes.
 * It is annotated with @TestConfiguration to indicate that it is a source of bean definitions.
 * The proxyBeanMethods attribute is set to false to optimize runtime bean creation.
 */
@TestConfiguration(proxyBeanMethods = false)
public class MongoContainerConfiguration {

    public static void registry(DynamicPropertyRegistry registry, MongoDBContainer container) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

    /**
     * This method creates a MongoDBContainer bean for testing purposes.
     * It uses the DynamicPropertyRegistry to dynamically register properties for the MongoDB container.
     * The property includes the URI for the MongoDB container.
     * The JimaProperties object is used to get the Docker image name for the MongoDB container.
     *
     * @param jimaProperties The JimaProperties object used to get the Docker image name for the MongoDB container.
     * @return The created MongoDBContainer bean.
     */
    @Bean
    public MongoDBContainer mongoDBContainer(JimaProperties jimaProperties) {
        MongoDBContainer container = new MongoDBContainer(DockerImageName.parse(jimaProperties.getTestContainer().getMongo().getImage()));
        container.withReuse(jimaProperties.getTestContainer().getMongo().isReuse());

        if (Strings.isNotBlank(jimaProperties.getTestContainer().getMongo().getContainer())
                && jimaProperties.getTestContainer().getMongo().getHostPort() != null
                && jimaProperties.getTestContainer().getMongo().getContainerPort() != null) {

            container.withCreateContainerCmdModifier(cmd -> {
                cmd.withName(jimaProperties.getTestContainer().getMongo().getContainer());
                Objects.requireNonNull(cmd.getHostConfig()).withPortBindings(
                        new PortBinding(
                                Ports.Binding.bindPort(jimaProperties.getTestContainer().getMongo().getHostPort()),
                                new ExposedPort(jimaProperties.getTestContainer().getMongo().getContainerPort())
                        )
                );
            });
        }

        return container;
    }
}