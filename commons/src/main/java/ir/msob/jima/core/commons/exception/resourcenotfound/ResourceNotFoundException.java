package ir.msob.jima.core.commons.exception.resourcenotfound;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    private final String message;
    private final String resource;

    public ResourceNotFoundException() {
        this.message = null;
        this.resource = null;
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
        this.resource = null;
    }

    public ResourceNotFoundException(String message, String resource) {
        this.message = message;
        this.resource = resource;
    }
}
