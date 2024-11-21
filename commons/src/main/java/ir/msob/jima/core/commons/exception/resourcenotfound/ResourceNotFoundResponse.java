package ir.msob.jima.core.commons.exception.resourcenotfound;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.ExceptionResponseAbstract;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The 'ResourceNotFoundResponse' class extends the 'ExceptionResponseAbstract' class and represents a specific type of response that is returned when a requested resource is not found.
 * It includes additional fields for the message associated with the exception and the name of the resource that was not found.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'ExceptionResponseAbstract' class to return a status code of 404.
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceNotFoundResponse extends ExceptionResponseAbstract {
    /**
     * The message associated with the exception.
     */
    private final String message;
    /**
     * The name of the resource that was not found.
     */
    private final String resource;

    /**
     * Default constructor for the 'ResourceNotFoundResponse' class.
     */
    public ResourceNotFoundResponse() {
        this.message = null;
        this.resource = null;
    }

    /**
     * Constructor for the 'ResourceNotFoundResponse' class that takes a message and a resource name as parameters.
     *
     * @param message  The message associated with the exception.
     * @param resource The name of the resource that was not found.
     */
    public ResourceNotFoundResponse(String message, String resource) {
        this.message = message;
        this.resource = resource;
    }

    /**
     * Overrides the 'getStatus' method from the 'ExceptionResponseAbstract' class to return a status code of 404.
     *
     * @return The status code for a resource not found error.
     */
    @Override
    public int getStatus() {
        return 404;
    }
}