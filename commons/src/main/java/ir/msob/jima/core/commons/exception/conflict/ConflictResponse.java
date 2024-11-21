package ir.msob.jima.core.commons.exception.conflict;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.ExceptionResponseAbstract;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The 'ConflictResponse' class extends the 'ExceptionResponseAbstract' class and represents a specific type of response that is returned when a conflict occurs.
 * It includes an additional field for the message associated with the conflict.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'ExceptionResponseAbstract' class to return a status code of 409.
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConflictResponse extends ExceptionResponseAbstract {
    /**
     * The message associated with the conflict.
     */
    private final String message;

    /**
     * Constructor for the 'ConflictResponse' class that takes a message as a parameter.
     *
     * @param message The response message.
     */
    public ConflictResponse(String message) {
        super();
        this.message = message;
    }

    /**
     * Default constructor for the 'ConflictResponse' class.
     */
    public ConflictResponse() {
        super();
        this.message = null;
    }

    /**
     * Overrides the 'getStatus' method from the 'ExceptionResponseAbstract' class to return a status code of 409.
     *
     * @return The status code for a conflict.
     */
    @Override
    public int getStatus() {
        return 409;
    }
}