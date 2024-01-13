package ir.msob.jima.core.test.testcontainer;

/**
 * This class, ContainerImageConstant, serves as a central location to define constants for container images used in testing scenarios.
 * It provides easy access to image names and versions to ensure consistency across test cases.
 */
public class ContainerImageConstant {
    public static final String REDIS_IMAGE = "redis:7.2";
    public static final String KAFKA_IMAGE = "confluentinc/cp-kafka:7.5.1";
    public static final String MONGO_DB_IMAGE = "mongo:7-jammy";
    public static final String ORACLE_DB_IMAGE = "gvenzl/oracle-xe:21-slim";
    public static final String MIN_IO_IMAGE = "minio/minio:RELEASE.2023-10-07T15-07-38Z.fips";

    // Private constructor to prevent instantiation of this class.
    private ContainerImageConstant() {
    }
}
