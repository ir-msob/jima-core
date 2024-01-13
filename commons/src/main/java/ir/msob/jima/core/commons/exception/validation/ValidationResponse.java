package ir.msob.jima.core.commons.exception.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationResponse extends AbstractExceptionResponse {
    private final Collection<InvalidData> invalidData;
    private final String message;

    public ValidationResponse(Collection<InvalidData> invalidData) {
        this.invalidData = invalidData;
        this.message = null;
    }

    public ValidationResponse(String message, Collection<InvalidData> invalidData) {
        this.message = message;
        this.invalidData = invalidData;
    }

    public ValidationResponse() {
        this.invalidData = null;
        this.message = null;
    }

    @Override
    public Integer getStatus() {
        return 400;
    }
}
