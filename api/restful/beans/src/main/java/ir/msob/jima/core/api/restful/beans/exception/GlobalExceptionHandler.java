package ir.msob.jima.core.api.restful.beans.exception;

import ir.msob.jima.core.commons.exception.BaseExceptionMapper;
import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import ir.msob.jima.core.commons.exception.ExceptionResponseAbstract;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions across the application.
 * This class provides centralized exception handling using Spring's {@link ControllerAdvice}.
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final BaseExceptionMapper exceptionMapper;
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles exceptions of type {@link BaseRuntimeException}.
     * Maps the exception to a response entity with the appropriate HTTP status.
     *
     * @param <ER> the type of the exception response
     * @param <E>  the type of the runtime exception
     * @param ex   the exception to handle
     * @return a {@link ResponseEntity} containing the exception response
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public <ER extends ExceptionResponseAbstract, E extends BaseRuntimeException> ResponseEntity<@NonNull ER> baseRuntimeExceptionHandler(E ex) {
        ER response = exceptionMapper.getExceptionResponse(ex);
        if (response != null) {
            HttpStatus httpStatus = HttpStatus.resolve(response.getStatus());
            if (httpStatus != null) {
                return ResponseEntity.status(httpStatus).body(response);
            } else {
                logger.error("Invalid HTTP status returned from exception response.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            logger.error("Unhandled exception occurred:", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
