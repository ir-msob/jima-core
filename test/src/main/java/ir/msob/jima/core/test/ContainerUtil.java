package ir.msob.jima.core.test;


import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import ir.msob.jima.core.commons.properties.TestContainerProperties;
import org.apache.logging.log4j.util.Strings;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.GenericContainer;

import java.util.List;
import java.util.Objects;

public class ContainerUtil {
    private ContainerUtil() {
    }

    /**
     * Ensure container is running or create a new one.
     * - If container exists but is stopped → start it.
     * - If container does not exist → Testcontainers creates it.
     * - Always applies fixed name and optional port binding.
     *
     * @param container Testcontainers container
     * @param <T>       container type
     * @return prepared container
     */
    @SuppressWarnings("resource") // DockerClient lifecycle managed by Testcontainers
    public static <T extends GenericContainer<T>> T prepareReusableContainer(
            T container, TestContainerProperties.BaseTestContainer testContainer) {

        if (Strings.isNotBlank(testContainer.getContainer())) {
            var dockerClient = DockerClientFactory.instance().client();

            // Look for existing container
            var existing = dockerClient.listContainersCmd()
                    .withShowAll(true) // include stopped containers
                    .exec()
                    .stream()
                    .filter(c -> List.of(c.getNames()).contains("/" + testContainer.getContainer()))
                    .findFirst();

            if (existing.isPresent()) {
                String containerId = existing.get().getId();
                String state = existing.get().getState();

                // If stopped → start it
                if (!"running".equalsIgnoreCase(state)) {
                    dockerClient.startContainerCmd(containerId).exec();
                }

                // IMPORTANT: do NOT set withCreateContainerCmdModifier when using existing container
                // This avoids name/port conflicts and uses the existing container.
                return container;
            } else {
                // No existing container → enforce fixed name & port bindings (original behavior)
                container.withCreateContainerCmdModifier(cmd -> {
                    cmd.withName(testContainer.getContainer());
                    if (testContainer.getHostPort() != null && testContainer.getContainerPort() != null) {
                        Objects.requireNonNull(cmd.getHostConfig()).withPortBindings(
                                new PortBinding(
                                        Ports.Binding.bindPort(testContainer.getHostPort()),
                                        new ExposedPort(testContainer.getContainerPort())
                                )
                        );
                    }
                });
            }
        }

        return container;
    }

}