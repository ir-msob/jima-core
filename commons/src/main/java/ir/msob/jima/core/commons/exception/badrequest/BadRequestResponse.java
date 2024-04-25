package ir.msob.jima.core.commons.exception.badrequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The 'BadRequestResponse' class extends the 'AbstractExceptionResponse' class and represents a specific type of response that is returned when a bad request is made.
 * It includes additional fields for the name of the field that has an invalid value, the invalid value itself, and a message.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 400.
 *
 *
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BadRequestResponse extends AbstractExceptionResponse {
    /**
     * The name of the field that has an invalid value.
     */
    private final String fieldName;
    /**
     * The invalid value.
     */
    private final Serializable value;
    /**
     * The message associated with the bad request.
     */
    private final String message;

    /**
     * Constructor for the 'BadRequestResponse' class that takes a message, a field name, and a value as parameters.
     *
     * @param message   The response message.
     * @param fieldName The name of the field that has an invalid value.
     * @param value     The invalid value.
     */
    public BadRequestResponse(String message, String fieldName, Serializable value) {
        this.fieldName = fieldName;
        this.value = value;
        this.message = message;
    }

    /**
     * Constructor for the 'BadRequestResponse' class that takes a field name and a value as parameters.
     *
     * @param fieldName The name of the field that has an invalid value.
     * @param value     The invalid value.
     */
    public BadRequestResponse(String fieldName, Serializable value) {
        this(null, fieldName, value);
    }

    /**
     * Default constructor for the 'BadRequestResponse' class.
     */
    public BadRequestResponse() {
        this(null, null, null);
    }

    /**
     * Overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 400.
     *
     * @return The status code for a bad request.
     */
    @Override
    public int getStatus() {
        return 400;
    }
}