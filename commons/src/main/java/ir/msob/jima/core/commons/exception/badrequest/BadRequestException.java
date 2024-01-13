package ir.msob.jima.core.commons.exception.badrequest;

import ir.msob.jima.core.commons.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
public class BadRequestException extends RuntimeException {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /*
     * Name of field has invalid value
     */
    private final transient String fieldName;
    /*
     * Invalid value
     */
    private final transient Serializable value;

    public BadRequestException() {
        this.fieldName = null;
        this.value = null;
    }

    public BadRequestException(final Object fieldName, final Serializable value) {
        super(fieldName.toString());
        this.fieldName = fieldName.toString();
        this.value = value;
    }

    public BadRequestException(String message, final String fieldName, final Serializable value) {
        super(message);
        this.fieldName = fieldName;
        this.value = value;
    }

    public BadRequestException(final Object fieldName) {
        super(fieldName.toString());
        this.fieldName = fieldName.toString();
        this.value = null;
    }

    public static void init(String message, Object... vars) throws BadRequestException {
        throw new BadRequestException(MessageUtil.prepareMessage(message, vars), null, null);
    }

    public static BadRequestException of(String message, Object... vars) {
        return new BadRequestException(MessageUtil.prepareMessage(message, vars), null, null);
    }
}
