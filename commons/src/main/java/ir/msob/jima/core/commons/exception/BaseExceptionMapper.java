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
            switch (ex) {
                case BadRequestException exception -> {
                    return (ER) new BadRequestResponse(exception.getMessage(), exception.getFieldName(), exception.getValue());
                }
                case ConflictException exception -> {
                    return (ER) new ConflictResponse(exception.getMessage());
                }
                case DataNotFoundException exception -> {
                    return (ER) new DataNotFoundResponse(exception.getEntity(), exception.getEntityId(), exception.getEntityClass());
                }
                case DomainNotFoundException exception -> {
                    return (ER) new DomainNotFoundResponse(exception.getDomainId(), exception.getDomainClass());
                }
                case CommonRuntimeException exception -> {
                    return (ER) new CommonRuntimeResponse(exception.getMessage());
                }
                case ValidationException exception -> {
                    return (ER) new ValidationResponse(exception.getMessage(), exception.getInvalidData());
                }
                case DuplicateException exception -> {
                    return (ER) new DuplicateResponse(exception.getKey(), exception.getValue(), exception.getMessage());
                }
                case ResourceNotFoundException exception -> {
                    return (ER) new ResourceNotFoundResponse(exception.getMessage(), exception.getResource());
                }
                default -> {
                    return (ER) new CommonRuntimeResponse(ex.getMessage());
                }
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