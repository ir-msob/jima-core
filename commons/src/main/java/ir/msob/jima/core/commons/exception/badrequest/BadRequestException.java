package ir.msob.jima.core.commons.exception.badrequest;

import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import ir.msob.jima.core.commons.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * The 'BadRequestException' class extends the 'BaseRuntimeException' class and represents a specific type of exception that is thrown when a bad request is made.
 * It includes additional fields for the name of the field that has an invalid value and the invalid value itself.
 * The class also provides several constructors for creating an instance of the exception with different sets of parameters.
 * Additionally, it provides static methods for initializing and creating an instance of the exception with a formatted message.
 */
@Setter
@Getter
public class BadRequestException extends BaseRuntimeException {
    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /**
     * The name of the field that has an invalid value.
     */
    private final transient String fieldName;
    /**
     * The invalid value.
     */
    private final transient Serializable value;

    /**
     * Default constructor for the 'BadRequestException' class.
     */
    public BadRequestException() {
        this.fieldName = null;
        this.value = null;
    }

    /**
     * Constructor for the 'BadRequestException' class that takes a field name and a value as parameters.
     *
     * @param fieldName The name of the field that has an invalid value.
     * @param value     The invalid value.
     */
    public BadRequestException(final Object fieldName, final Serializable value) {
        super(fieldName.toString());
        this.fieldName = fieldName.toString();
        this.value = value;
    }

    /**
     * Constructor for the 'BadRequestException' class that takes a message, a field name, and a value as parameters.
     *
     * @param message   The exception message.
     * @param fieldName The name of the field that has an invalid value.
     * @param value     The invalid value.
     */
    public BadRequestException(String message, final String fieldName, final Serializable value) {
        super(message);
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Constructor for the 'BadRequestException' class that takes a field name as a parameter.
     *
     * @param fieldName The name of the field that has an invalid value.
     */
    public BadRequestException(final Object fieldName) {
        super(fieldName.toString());
        this.fieldName = fieldName.toString();
        this.value = null;
    }

    /**
     * Static method for initializing a 'BadRequestException' with a formatted message.
     *
     * @param message The exception message.
     * @param vars    The variables to be included in the formatted message.
     * @throws BadRequestException The initialized exception.
     */
    public static void init(String message, Object... vars) throws BadRequestException {
        throw new BadRequestException(MessageUtil.prepareMessage(message, vars), null, null);
    }

    /**
     * Static method for creating a 'BadRequestException' with a formatted message.
     *
     * @param message The exception message.
     * @param vars    The variables to be included in the formatted message.
     * @return The created exception.
     */
    public static BadRequestException of(String message, Object... vars) {
        return new BadRequestException(MessageUtil.prepareMessage(message, vars), null, null);
    }
}