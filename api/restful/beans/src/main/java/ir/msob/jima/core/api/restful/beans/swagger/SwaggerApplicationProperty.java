package ir.msob.jima.core.api.restful.beans.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the properties of a Swagger application.
 * This class is used to define metadata for the Swagger documentation.
 */
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwaggerApplicationProperty {

    /**
     * The description of the application.
     */
    private String description;

    /**
     * The version of the application.
     */
    private String version;

    /**
     * The license of the application.
     */
    private String license;

    /**
     * The URL of the license.
     */
    private String licenseUrl;
}
