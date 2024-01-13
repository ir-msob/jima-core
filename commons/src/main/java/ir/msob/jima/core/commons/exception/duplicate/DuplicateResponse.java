package ir.msob.jima.core.commons.exception.duplicate;

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
public class DuplicateResponse extends AbstractExceptionResponse {
    /*
     * Name of field has invalid value
     */
    private final String key;
    /*
     * Invalid value
     */
    private final Serializable value;

    private final String message;

    public DuplicateResponse(String key, Serializable value) {
        super();
        this.key = key;
        this.value = value;
        this.message = null;
    }

    public DuplicateResponse() {
        super();
        this.key = null;
        this.value = null;
        this.message = null;
    }

    public DuplicateResponse(String key, Serializable value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    @Override
    public Integer getStatus() {
        return 409;
    }
}
