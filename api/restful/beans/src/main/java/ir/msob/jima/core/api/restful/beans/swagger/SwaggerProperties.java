package ir.msob.jima.core.api.restful.beans.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for Swagger.
 * This class binds the properties defined in the application's configuration file
 * with the prefix "swagger" to the fields of this class.
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "swagger")
@Configuration
public class SwaggerProperties {

    /**
     * The application-specific properties for Swagger.
     */
    private SwaggerApplicationProperty application;
}
