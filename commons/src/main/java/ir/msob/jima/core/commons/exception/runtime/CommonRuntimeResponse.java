package ir.msob.jima.core.commons.exception.runtime;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * The 'CommonRuntimeResponse' class extends the 'AbstractExceptionResponse' class and represents a specific type of response that is returned when a common runtime exception occurs.
 * It includes an additional field for the message associated with the exception.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 500.
 *
 *
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRuntimeResponse extends AbstractExceptionResponse {
    /**
     * The message associated with the exception.
     */
    private final String message;

    /**
     * Default constructor for the 'CommonRuntimeResponse' class.
     */
    public CommonRuntimeResponse() {
        super();
        this.message = null;
    }

    /**
     * Constructor for the 'CommonRuntimeResponse' class that takes a message as a parameter.
     *
     * @param message The message associated with the exception.
     */
    public CommonRuntimeResponse(String message) {
        this.message = message;
    }

    /**
     * Overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 500.
     *
     * @return The status code for a common runtime error.
     */
    @Override
    public Integer getStatus() {
        return 500;
    }
}