package ir.msob.jima.core.commons.exception.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.ExceptionResponseAbstract;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * The 'ValidationResponse' class extends the 'ExceptionResponseAbstract' class and represents a specific type of response that is returned when a objectvalidation error occurs.
 * It includes additional fields for a collection of invalid data associated with the exception and the message associated with the exception.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'ExceptionResponseAbstract' class to return a status code of 400.
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationResponse extends ExceptionResponseAbstract {
    /**
     * A collection of invalid data associated with the exception.
     */
    private final Collection<InvalidData> invalidData;
    /**
     * The message associated with the exception.
     */
    private final String message;

    /**
     * Constructor for the 'ValidationResponse' class that takes a collection of invalid data as a parameter.
     *
     * @param invalidData A collection of invalid data associated with the exception.
     */
    public ValidationResponse(Collection<InvalidData> invalidData) {
        this.invalidData = invalidData;
        this.message = null;
    }

    /**
     * Constructor for the 'ValidationResponse' class that takes a message and a collection of invalid data as parameters.
     *
     * @param message     The message associated with the exception.
     * @param invalidData A collection of invalid data associated with the exception.
     */
    public ValidationResponse(String message, Collection<InvalidData> invalidData) {
        this.message = message;
        this.invalidData = invalidData;
    }

    /**
     * Default constructor for the 'ValidationResponse' class.
     */
    public ValidationResponse() {
        this.invalidData = null;
        this.message = null;
    }

    /**
     * Overrides the 'getStatus' method from the 'ExceptionResponseAbstract' class to return a status code of 400.
     *
     * @return The status code for a objectvalidation error.
     */
    @Override
    public int getStatus() {
        return 400;
    }
}