package ir.msob.jima.core.commons.util;

import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestResponse;
import ir.msob.jima.core.commons.exception.conflict.ConflictException;
import ir.msob.jima.core.commons.exception.conflict.ConflictResponse;
import ir.msob.jima.core.commons.exception.datanotfound.DataNotFoundException;
import ir.msob.jima.core.commons.exception.datanotfound.DataNotFoundResponse;
import ir.msob.jima.core.commons.exception.domainnotfound.DomainNotFoundException;
import ir.msob.jima.core.commons.exception.domainnotfound.DomainNotFoundResponse;
import ir.msob.jima.core.commons.exception.duplicate.DuplicateException;
import ir.msob.jima.core.commons.exception.duplicate.DuplicateResponse;
import ir.msob.jima.core.commons.exception.resourcenotfound.ResourceNotFoundException;
import ir.msob.jima.core.commons.exception.resourcenotfound.ResourceNotFoundResponse;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeResponse;
import ir.msob.jima.core.commons.exception.validation.ValidationException;
import ir.msob.jima.core.commons.exception.validation.ValidationResponse;

/**
 * Utility class for casting exceptions to their corresponding response objects.
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    /**
     * Casts a Throwable exception to its corresponding AbstractExceptionResponse.
     *
     * @param ex   The exception to cast.
     * @param <ER> The type of AbstractExceptionResponse.
     * @return The corresponding exception response object.
     */
    public static <ER extends AbstractExceptionResponse> ER cast(Throwable ex) {
        if (ex != null) {
            if (ex instanceof BadRequestException exception) {
                return (ER) new BadRequestResponse(exception.getMessage(), exception.getFieldName(), exception.getValue());
            } else if (ex instanceof ConflictException exception) {
                return (ER) new ConflictResponse(exception.getMessage());
            } else if (ex instanceof DataNotFoundException exception) {
                return (ER) new DataNotFoundResponse(exception.getEntity(), exception.getEntityId(), exception.getEntityClass());
            } else if (ex instanceof DomainNotFoundException exception) {
                return (ER) new DomainNotFoundResponse(exception.getDomainId(), exception.getDomainClass());
            } else if (ex instanceof CommonRuntimeException exception) {
                return (ER) new CommonRuntimeResponse(exception.getMessage());
            } else if (ex instanceof ValidationException exception) {
                return (ER) new ValidationResponse(exception.getMessage(), exception.getInvalidData());
            } else if (ex instanceof DuplicateException exception) {
                return (ER) new DuplicateResponse(exception.getKey(), exception.getValue(), exception.getMessage());
            } else if (ex instanceof ResourceNotFoundException exception) {
                return (ER) new ResourceNotFoundResponse(exception.getMessage(), exception.getResource());
            }
        }
        return null;
    }
}
