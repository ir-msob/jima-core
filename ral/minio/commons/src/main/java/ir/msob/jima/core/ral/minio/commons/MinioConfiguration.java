package ir.msob.jima.core.ral.minio.commons;

import io.minio.MinioClient;
import ir.msob.jima.core.beans.properties.MinioProperties;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Configuration class for setting up a Minio client bean.
 */
@Configuration
public class MinioConfiguration {

    /**
     * Creates and configures a Minio client bean based on the provided MinioProperties.
     *
     * @param minioProperties The MinioProperties containing configuration values.
     * @return A MinioClient bean.
     */
    @Bean
    public MinioClient generateMinioClient(MinioProperties minioProperties) {
        // Configure an OkHttpClient for HTTP communication with Minio
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(minioProperties.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(minioProperties.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(minioProperties.getReadTimeout(), TimeUnit.MILLISECONDS)
                .build();

        // Build and return a MinioClient using the provided properties
        return MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .httpClient(httpClient)
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
