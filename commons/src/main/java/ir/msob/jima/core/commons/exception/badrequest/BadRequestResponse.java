package ir.msob.jima.core.commons.exception.badrequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BadRequestResponse extends AbstractExceptionResponse {
    /*
     * Name of field has invalid value
     */
    private final String fieldName;
    /*
     * Invalid value
     */
    private final Serializable value;

    private final String message;

    public BadRequestResponse(String message, String fieldName, Serializable value) {
        super();
        this.fieldName = fieldName;
        this.value = value;
        this.message = message;
    }

    public BadRequestResponse(String fieldName, Serializable value) {
        super();
        this.fieldName = fieldName;
        this.value = value;
        this.message = null;
    }

    public BadRequestResponse() {
        super();
        this.fieldName = null;
        this.value = null;
        this.message = null;
    }

    @Override
    public Integer getStatus() {
        return 400;
    }
}
