package ir.msob.jima.core.commons.exception;

import ir.msob.jima.core.commons.model.dto.ModelType;

/**
 * The 'AbstractExceptionResponse' class extends the 'ModelType' class and represents a base class for all types of exception responses.
 * It includes an abstract method 'getStatus' that must be implemented by any class that extends 'AbstractExceptionResponse'.
 * This method is intended to return the status code associated with the exception response.
 */
public abstract class AbstractExceptionResponse extends ModelType {

    /**
     * Abstract method that returns the status code associated with the exception response.
     * This method must be implemented by any class that extends 'AbstractExceptionResponse'.
     *
     * @return The status code associated with the exception response.
     */
    public abstract Integer getStatus();
}