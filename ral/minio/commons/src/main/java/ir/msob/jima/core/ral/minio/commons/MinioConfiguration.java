package ir.msob.jima.core.ral.minio.commons;

import io.minio.MinioClient;
import ir.msob.jima.core.beans.properties.MinioProperties;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;
import java.time.Duration;
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

    @Bean
    public S3AsyncClient s3AsyncClient(MinioProperties minioProperties) {

        SdkAsyncHttpClient nettyClient = NettyNioAsyncHttpClient.builder()
                .connectionTimeout(Duration.ofMillis(minioProperties.getConnectTimeout()))
                .readTimeout(Duration.ofMillis(minioProperties.getReadTimeout()))
                .writeTimeout(Duration.ofMillis(minioProperties.getWriteTimeout()))
                .build();

        var credentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(minioProperties.getAccessKey(), minioProperties.getSecretKey())
        );

        return S3AsyncClient.builder()
                .endpointOverride(URI.create(minioProperties.getUrl()))
                .region(Region.of(minioProperties.getRegion()))
                .credentialsProvider(credentialsProvider)
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .httpClient(nettyClient)
                .build();
    }
}
