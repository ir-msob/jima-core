package ir.msob.jima.core.commons.exception.duplicate;

import ir.msob.jima.core.commons.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * The 'DuplicateException' class extends the 'RuntimeException' class and represents a specific type of exception that is thrown when a duplicate value is encountered.
 * It includes additional fields for the key associated with the duplicate value and the duplicate value itself.
 * The class also provides several constructors for creating an instance of the exception with different sets of parameters.
 * Additionally, it provides static methods for initializing and creating instances of the exception.
 *
 *
 */
@Setter
@Getter
public class DuplicateException extends RuntimeException {
    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /**
     * The key associated with the duplicate value.
     */
    private final transient String key;
    /**
     * The duplicate value.
     */
    private final transient Serializable value;

    /**
     * Default constructor for the 'DuplicateException' class.
     */
    public DuplicateException() {
        this.key = null;
        this.value = null;
    }

    /**
     * Constructor for the 'DuplicateException' class that takes a key and a value as parameters.
     *
     * @param key   The key associated with the duplicate value.
     * @param value The duplicate value.
     */
    public DuplicateException(final Object key, final Serializable value) {
        super(key.toString());
        this.key = key.toString();
        this.value = value;
    }

    /**
     * Constructor for the 'DuplicateException' class that takes a message, a key, and a value as parameters.
     *
     * @param message The exception message.
     * @param key     The key associated with the duplicate value.
     * @param value   The duplicate value.
     */
    public DuplicateException(String message, final String key, final Serializable value) {
        super(message);
        this.key = key;
        this.value = value;
    }

    /**
     * Constructor for the 'DuplicateException' class that takes a key as a parameter.
     *
     * @param key The key associated with the duplicate value.
     */
    public DuplicateException(final Object key) {
        super(key.toString());
        this.key = key.toString();
        this.value = null;
    }

    /**
     * Static method for initializing an instance of the 'DuplicateException' class with a message and a set of variables.
     *
     * @param message The exception message.
     * @param vars    The set of variables.
     * @throws DuplicateException The initialized exception.
     */
    public static void init(String message, Object... vars) throws DuplicateException {
        throw new DuplicateException(MessageUtil.prepareMessage(message, vars), null, null);
    }

    /**
     * Static method for creating an instance of the 'DuplicateException' class with a message and a set of variables.
     *
     * @param message The exception message.
     * @param vars    The set of variables.
     * @return The created exception.
     */
    public static DuplicateException of(String message, Object... vars) {
        return new DuplicateException(MessageUtil.prepareMessage(message, vars), null, null);
    }
}