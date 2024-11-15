package ir.msob.jima.core.commons.exception.runtime;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import ir.msob.jima.core.commons.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * The 'CommonRuntimeException' class extends the 'BaseRuntimeException' class and represents a common type of runtime exception.
 * It includes a method for preparing a message with variables and several constructors for creating an instance of the exception with different sets of parameters.
 * The class also includes a serial version UID for the serializable class.
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRuntimeException extends BaseRuntimeException {
    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = 5220126156186090729L;

    /**
     * Constructor for the 'CommonRuntimeException' class that takes a message and a set of variables as parameters.
     *
     * @param message The exception message.
     * @param vars    The set of variables.
     */
    public CommonRuntimeException(String message, Object... vars) {
        super(MessageUtil.prepareMessage(message, vars));
    }

    /**
     * Constructor for the 'CommonRuntimeException' class that takes a cause as a parameter.
     *
     * @param cause The cause of the exception.
     */
    public CommonRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for the 'CommonRuntimeException' class that takes a cause, a message, and a set of variables as parameters.
     *
     * @param cause   The cause of the exception.
     * @param message The exception message.
     * @param vars    The set of variables.
     */
    public CommonRuntimeException(Throwable cause, String message, Object... vars) {
        super(MessageUtil.prepareMessage(message, vars), cause);
    }

    /**
     * Default constructor for the 'CommonRuntimeException' class.
     */
    public CommonRuntimeException() {

    }
}