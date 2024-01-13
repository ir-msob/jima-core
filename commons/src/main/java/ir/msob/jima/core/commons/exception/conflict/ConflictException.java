package ir.msob.jima.core.commons.exception.conflict;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
public class ConflictException extends RuntimeException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -8514773215832079870L;

    public ConflictException(String message) {
        super(message);
    }
}
