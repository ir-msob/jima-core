package ir.msob.jima.core.api.restful.beans.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "swagger")
@Configuration
public class SwaggerProperties {
    private SwaggerApplicationProperty application;
}
