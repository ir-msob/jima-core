package ir.msob.jima.core.ral.minio.test;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import ir.msob.jima.core.beans.properties.JimaProperties;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Objects;

/**
 * This class provides the configuration for setting up a MinIO container for testing purposes.
 * It is annotated with @TestConfiguration to indicate that it is a source of bean definitions.
 * The proxyBeanMethods attribute is set to false to optimize runtime bean creation.
 */
@TestConfiguration(proxyBeanMethods = false)
public class MinIOContainerConfiguration {

    public static void registry(DynamicPropertyRegistry registry, MinIOContainer container) {
        registry.add("spring.minio.url", container::getS3URL);
        registry.add("spring.minio.access-key", container::getUserName);
        registry.add("spring.minio.secret-key", container::getPassword);
    }

    /**
     * This method creates a MinIOContainer bean for testing purposes.
     * It uses the DynamicPropertyRegistry to dynamically register properties for the MinIO container.
     * The properties include the URL, access key, and secret key for the MinIO container.
     * The JimaProperties object is used to get the Docker image name, access key, and secret key for the MinIO container.
     *
     * @param jimaProperties The JimaProperties object used to get the Docker image name, access key, and secret key for the MinIO container.
     * @return The created MinIOContainer bean.
     */
    @Bean
    public MinIOContainer minIOContainer(JimaProperties jimaProperties) {
        MinIOContainer container = new MinIOContainer(DockerImageName.parse(jimaProperties.getTestContainer().getMinio().getImage()));
        if (Strings.isNotBlank(jimaProperties.getTestContainer().getMinio().getAccessKey()))
            container.withUserName(jimaProperties.getTestContainer().getMinio().getAccessKey());

        if (Strings.isNotBlank(jimaProperties.getTestContainer().getMinio().getSecretKey()))
            container.withPassword(jimaProperties.getTestContainer().getMinio().getSecretKey());

        container.withReuse(jimaProperties.getTestContainer().getMinio().isReuse());

        if (Strings.isNotBlank(jimaProperties.getTestContainer().getMinio().getContainer())
                && jimaProperties.getTestContainer().getMinio().getHostPort() != null
                && jimaProperties.getTestContainer().getMinio().getContainerPort() != null) {

            container.withCreateContainerCmdModifier(cmd -> {
                cmd.withName(jimaProperties.getTestContainer().getMinio().getContainer());
                Objects.requireNonNull(cmd.getHostConfig()).withPortBindings(
                        new PortBinding(
                                Ports.Binding.bindPort(jimaProperties.getTestContainer().getMinio().getHostPort()),
                                new ExposedPort(jimaProperties.getTestContainer().getMinio().getContainerPort())
                        )
                );
            });
        }
        return container;
    }
}