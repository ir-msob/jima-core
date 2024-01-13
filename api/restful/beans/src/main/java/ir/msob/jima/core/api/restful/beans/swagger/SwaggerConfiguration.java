package ir.msob.jima.core.api.restful.beans.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The `SwaggerConfiguration` class configures Swagger documentation for a Spring WebFlux application.
 * It implements the WebFluxConfigurer interface, allowing customization of the WebFlux configuration.
 * <p>
 * This configuration defines properties such as the application name, description, version, license, and license URL
 * for the Swagger API documentation. Additionally, it creates a Docket bean for Swagger to define the API information and endpoints to document.
 * <p>
 * Author: Yaqub Abdi
 */
@Profile("swagger")
@Configuration("SwaggerConfiguration")
@EnableSwagger2  // FIXME: conflict with GraphQL
@RequiredArgsConstructor
public class SwaggerConfiguration implements WebFluxConfigurer {

    private final SwaggerProperties swaggerProperties;
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * Creates a Docket bean for Swagger API documentation configuration, specifying metadata and endpoints to document.
     *
     * @return A Docket bean for Swagger API documentation.
     */
    @Bean("docket")
    @Qualifier("docket")
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    /**
     * Builds API metadata using configured properties like application name, description, version, license, and license URL.
     *
     * @return An ApiInfo instance containing API metadata.
     */
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(swaggerProperties.getApplication().getDescription())
                .version(swaggerProperties.getApplication().getVersion())
                .license(swaggerProperties.getApplication().getLicense())
                .licenseUrl(swaggerProperties.getApplication().getLicenseUrl())
                .build();
    }
}
