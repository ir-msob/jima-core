package ir.msob.jima.core.commons.exception.conflict;

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
public class ConflictResponse extends AbstractExceptionResponse {

    private final String message;

    public ConflictResponse(String message) {
        super();
        this.message = message;
    }

    public ConflictResponse() {
        super();
        this.message = null;
    }

    @Override
    public Integer getStatus() {
        return 409;
    }
}
