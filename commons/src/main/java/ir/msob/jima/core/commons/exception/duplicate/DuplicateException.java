package ir.msob.jima.core.commons.exception.duplicate;

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
public class DuplicateException extends RuntimeException {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /*
     * Name of field has invalid value
     */
    private final transient String key;
    /*
     * Invalid value
     */
    private final transient Serializable value;

    public DuplicateException() {
        this.key = null;
        this.value = null;
    }

    public DuplicateException(final Object key, final Serializable value) {
        super(key.toString());
        this.key = key.toString();
        this.value = value;
    }

    public DuplicateException(String message, final String key, final Serializable value) {
        super(message);
        this.key = key;
        this.value = value;
    }

    public DuplicateException(final Object key) {
        super(key.toString());
        this.key = key.toString();
        this.value = null;
    }

    public static void init(String message, Object... vars) throws DuplicateException {
        throw new DuplicateException(MessageUtil.prepareMessage(message, vars), null, null);
    }

    public static DuplicateException of(String message, Object... vars) {
        return new DuplicateException(MessageUtil.prepareMessage(message, vars), null, null);
    }
}
