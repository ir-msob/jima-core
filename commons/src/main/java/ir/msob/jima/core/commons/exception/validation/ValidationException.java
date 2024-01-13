package ir.msob.jima.core.commons.exception.validation;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Collection;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
public class ValidationException extends RuntimeException {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    private final transient Collection<InvalidData> invalidData;

    public ValidationException(String message, Collection<InvalidData> invalidData) {
        super(message);
        this.invalidData = invalidData;
    }

    public ValidationException(Collection<InvalidData> invalidData) {
        this.invalidData = invalidData;
    }

    public ValidationException() {
        invalidData = null;
    }
}
