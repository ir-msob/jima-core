package ir.msob.jima.core.api.restful.beans.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwaggerApplicationProperty {
    private String description;
    private String version;
    private String license;
    private String licenseUrl;
}
