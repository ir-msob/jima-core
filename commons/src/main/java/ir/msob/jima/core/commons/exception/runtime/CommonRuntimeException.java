package ir.msob.jima.core.commons.exception.runtime;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRuntimeException extends RuntimeException {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 5220126156186090729L;

    public CommonRuntimeException(String message, Object... vars) {
        super(MessageUtil.prepareMessage(message, vars));
    }

    public CommonRuntimeException(Throwable cause) {
        super(cause);
    }

    public CommonRuntimeException(Throwable cause, String message, Object... vars) {
        super(MessageUtil.prepareMessage(message, vars), cause);
    }

    public CommonRuntimeException() {

    }
}
