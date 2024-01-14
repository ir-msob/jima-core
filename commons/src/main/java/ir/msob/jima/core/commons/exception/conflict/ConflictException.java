package ir.msob.jima.core.commons.exception.conflict;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * The 'ConflictException' class extends the 'RuntimeException' class and represents a specific type of exception that is thrown when a conflict occurs.
 * It includes a field for the serial version UID which is used for serialization and deserialization.
 * The class also provides a constructor for creating an instance of the exception with a message.
 *
 *
 */
@Setter
@Getter
public class ConflictException extends RuntimeException {

    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = -8514773215832079870L;

    /**
     * Constructor for the 'ConflictException' class that takes a message as a parameter.
     *
     * @param message The exception message.
     */
    public ConflictException(String message) {
        super(message);
    }
}