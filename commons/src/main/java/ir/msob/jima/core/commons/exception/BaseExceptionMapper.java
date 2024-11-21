package ir.msob.jima.core.commons.exception;

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
 * The `BaseExceptionMapper` interface provides methods for mapping exceptions to their corresponding response objects.
 * It defines a method `cast` that should be implemented by classes that want to provide their own mapping logic.
 * It also provides a default implementation for getting the exception response.
 */
public interface BaseExceptionMapper {

    /**
     * This method should be implemented by classes that want to provide their own logic for mapping exceptions to their corresponding response objects.
     *
     * @param ex   The exception to be mapped.
     * @param <ER> The type of the exception response.
     * @return The mapped exception response.
     */
    <ER extends ExceptionResponseAbstract> ER cast(Throwable ex);

    /**
     * This method provides a default implementation for mapping exceptions to their corresponding response objects.
     *
     * @param ex   The exception to be mapped.
     * @param <ER> The type of the exception response.
     * @return The mapped exception response.
     */
    @SuppressWarnings("unchecked")
    private <ER extends ExceptionResponseAbstract, E extends BaseRuntimeException> ER castException(E ex) {
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

    /**
     * This method provides a default implementation for getting the exception response.
     * It first tries to cast the exception using the `castException` method.
     * If that fails, it tries to cast the exception using the `cast` method.
     *
     * @param ex   The exception to be mapped.
     * @param <ER> The type of the exception response.
     * @return The mapped exception response.
     */
    default <ER extends ExceptionResponseAbstract, E extends BaseRuntimeException> ER getExceptionResponse(E ex) {
        return castException(ex);
    }
}