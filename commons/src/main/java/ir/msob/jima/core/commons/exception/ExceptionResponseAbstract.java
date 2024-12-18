package ir.msob.jima.core.commons.exception;

import ir.msob.jima.core.commons.shared.ModelType;

/**
 * The {@code ExceptionResponseAbstract} class extends the {@code ModelType} class and serves as a base class for all types of exception responses.
 * It includes an abstract method {@code getStatus()} that must be implemented by any class extending {@code ExceptionResponseAbstract}.
 * This method is intended to return the status code associated with the exception response.
 */
public abstract class ExceptionResponseAbstract extends ModelType {

    /**
     * Abstract method that returns the status code associated with the exception response.
     * This method must be implemented by any class that extends 'ExceptionResponseAbstract'.
     *
     * @return The status code associated with the exception response.
     */
    public abstract int getStatus();
}