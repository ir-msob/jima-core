package ir.msob.jima.core.beans.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Minio properties. This class is used to configure Minio
 * properties such as URL, access key, secret key, bucket name, and timeouts.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
@Setter
@Getter
public class MinioProperties {
    /**
     * The URL of the Minio server.
     */
    private String url;

    /**
     * The access key for authentication with the Minio server.
     */
    private String accessKey;

    /**
     * The secret key for authentication with the Minio server.
     */
    private String secretKey;

    /**
     * The name of the default bucket to be used for Minio operations.
     */
    private String bucket;

    /**
     * The connection timeout in milliseconds for Minio client.
     */
    private Long connectTimeout;

    /**
     * The write timeout in milliseconds for Minio client.
     */
    private Long writeTimeout;

    /**
     * The read timeout in milliseconds for Minio client.
     */
    private Long readTimeout;
}
