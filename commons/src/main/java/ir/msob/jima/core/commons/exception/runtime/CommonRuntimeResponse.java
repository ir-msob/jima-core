package ir.msob.jima.core.commons.exception.runtime;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRuntimeResponse extends AbstractExceptionResponse {
    private final String message;

    public CommonRuntimeResponse() {
        super();
        this.message = null;
    }

    public CommonRuntimeResponse(String message) {
        this.message = message;
    }

    @Override
    public Integer getStatus() {
        return 500;
    }
}
