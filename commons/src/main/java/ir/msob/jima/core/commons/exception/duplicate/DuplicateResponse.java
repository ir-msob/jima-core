package ir.msob.jima.core.commons.exception.duplicate;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'DuplicateResponse' class extends the 'AbstractExceptionResponse' class and represents a specific type of response that is returned when a duplicate value is encountered.
 * It includes additional fields for the key associated with the duplicate value, the duplicate value itself, and a message.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 409.
 *
 *
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DuplicateResponse extends AbstractExceptionResponse {
    /**
     * The key associated with the duplicate value.
     */
    private final String key;
    /**
     * The duplicate value.
     */
    private final Serializable value;
    /**
     * The message associated with the duplicate value.
     */
    private final String message;

    /**
     * Constructor for the 'DuplicateResponse' class that takes a key and a value as parameters.
     *
     * @param key   The key associated with the duplicate value.
     * @param value The duplicate value.
     */
    public DuplicateResponse(String key, Serializable value) {
        super();
        this.key = key;
        this.value = value;
        this.message = null;
    }

    /**
     * Default constructor for the 'DuplicateResponse' class.
     */
    public DuplicateResponse() {
        super();
        this.key = null;
        this.value = null;
        this.message = null;
    }

    /**
     * Constructor for the 'DuplicateResponse' class that takes a key, a value, and a message as parameters.
     *
     * @param key     The key associated with the duplicate value.
     * @param value   The duplicate value.
     * @param message The message associated with the duplicate value.
     */
    public DuplicateResponse(String key, Serializable value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    /**
     * Overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 409.
     *
     * @return The status code for a duplicate value error.
     */
    @Override
    public Integer getStatus() {
        return 409;
    }
}