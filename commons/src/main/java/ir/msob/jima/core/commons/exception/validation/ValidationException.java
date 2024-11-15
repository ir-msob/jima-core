package ir.msob.jima.core.commons.exception.validation;

import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Collection;

/**
 * The 'ValidationException' class extends the 'RuntimeException' class and represents a specific type of exception that is thrown when a validation error occurs.
 * It includes an additional field for a collection of invalid data associated with the exception.
 * The class also provides several constructors for creating an instance of the exception with different sets of parameters.
 * Additionally, it includes a serial version UID for the serializable class.
 */
@Setter
@Getter
public class ValidationException extends BaseRuntimeException {
    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /**
     * A collection of invalid data associated with the exception.
     */
    private final transient Collection<InvalidData> invalidData;

    /**
     * Constructor for the 'ValidationException' class that takes a message and a collection of invalid data as parameters.
     *
     * @param message     The message associated with the exception.
     * @param invalidData A collection of invalid data associated with the exception.
     */
    public ValidationException(String message, Collection<InvalidData> invalidData) {
        super(message);
        this.invalidData = invalidData;
    }

    /**
     * Constructor for the 'ValidationException' class that takes a collection of invalid data as a parameter.
     *
     * @param invalidData A collection of invalid data associated with the exception.
     */
    public ValidationException(Collection<InvalidData> invalidData) {
        this.invalidData = invalidData;
    }

    /**
     * Default constructor for the 'ValidationException' class.
     */
    public ValidationException() {
        invalidData = null;
    }
}