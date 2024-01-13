package ir.msob.jima.core.commons.exception.resourcenotfound;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceNotFoundResponse extends AbstractExceptionResponse {
    private final String message;
    private final String resource;

    public ResourceNotFoundResponse() {
        this.message = null;
        this.resource = null;
    }

    public ResourceNotFoundResponse(String message, String resource) {
        this.message = message;
        this.resource = resource;
    }

    @Override
    public Integer getStatus() {
        return 404;
    }
}
