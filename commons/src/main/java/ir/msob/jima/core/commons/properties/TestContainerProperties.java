package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents the properties of a test container.
 * It includes an image object which contains the names of various images.
 * It also includes nested static classes for each type of container (Mongo, Minio, Kafka, Oracle, Redis)
 * Each nested class contains the specific properties for its corresponding container.
 * The properties include the Docker image name, and other specific properties like access key, secret key, cluster ID, username, and password.
 * The Lombok annotations are used to automatically generate getters, setters, constructors, and toString methods.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TestContainerProperties {

    private Mongo mongo = new Mongo();
    private Minio minio = new Minio();
    private Kafka kafka = new Kafka();
    private Oracle oracle = new Oracle();
    private Redis redis = new Redis();
    private Keycloak keycloak = new Keycloak();

    /**
     * This nested static class represents the properties of a Kafka test container.
     * It includes the Docker image name and the cluster ID.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Kafka {
        private String image;
        private String clusterId;
        private boolean reuse = false;
    }

    /**
     * This nested static class represents the properties of a Minio test container.
     * It includes the Docker image name, access key, and secret key.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Minio {
        private String image;
        private String accessKey;
        private String secretKey;
        private boolean reuse = false;
    }

    /**
     * This nested static class represents the properties of a Mongo test container.
     * It includes the Docker image name.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Mongo {
        private String image;
        private boolean reuse = false;
    }

    /**
     * This nested static class represents the properties of an Oracle test container.
     * It includes the Docker image name, username, and password.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Oracle {
        private String image;
        private String username;
        private String password;
        private boolean reuse = false;
    }

    /**
     * This nested static class represents the properties of a Redis test container.
     * It includes the Docker image name.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Redis {
        private String image;
        private boolean reuse = false;
    }

    /**
     * This nested static class represents the properties of a Keycloak test container.
     * It includes the Docker image name.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Keycloak {
        private String image;
        private String realm = "master";
        private String realmJsonFile = "keycloak/realm.json";
        private boolean reuse = false;
    }
}