package ir.msob.jima.core.commons.exception.resourcenotfound;

import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * The 'ResourceNotFoundException' class extends the 'BaseRuntimeException' class and represents a specific type of exception that is thrown when a requested resource is not found.
 * It includes additional fields for the message associated with the exception and the name of the resource that was not found.
 * The class also provides several constructors for creating an instance of the exception with different sets of parameters.
 *
 *
 */
@Setter
@Getter
public class ResourceNotFoundException extends BaseRuntimeException {
    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /**
     * The message associated with the exception.
     */
    private final String message;
    /**
     * The name of the resource that was not found.
     */
    private final String resource;

    /**
     * Default constructor for the 'ResourceNotFoundException' class.
     */
    public ResourceNotFoundException() {
        this.message = null;
        this.resource = null;
    }

    /**
     * Constructor for the 'ResourceNotFoundException' class that takes a message as a parameter.
     *
     * @param message The message associated with the exception.
     */
    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
        this.resource = null;
    }

    /**
     * Constructor for the 'ResourceNotFoundException' class that takes a message and a resource name as parameters.
     *
     * @param message  The message associated with the exception.
     * @param resource The name of the resource that was not found.
     */
    public ResourceNotFoundException(String message, String resource) {
        this.message = message;
        this.resource = resource;
    }
}