package ir.msob.jima.core.api.restful.beans.exception;

import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import ir.msob.jima.core.commons.exception.BaseExceptionMapper;
import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@CommonsLog
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final BaseExceptionMapper exceptionMapper;

    @ExceptionHandler(BaseRuntimeException.class)
    public <ER extends AbstractExceptionResponse, E extends BaseRuntimeException> ResponseEntity<ER> baseRuntimeExceptionHandler(E ex) {
        ER response = exceptionMapper.getExceptionResponse(ex);
        if (response != null) {
            HttpStatus httpStatus = HttpStatus.resolve(response.getStatus());
            if (httpStatus != null) {
                return ResponseEntity.status(httpStatus).body(response);
            } else {
                log.error("Invalid HTTP status returned from exception response.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            log.error("Unhandled exception occurred:", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
