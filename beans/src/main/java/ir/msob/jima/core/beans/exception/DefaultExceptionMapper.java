package ir.msob.jima.core.beans.exception;

import ir.msob.jima.core.commons.exception.BaseExceptionMapper;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeResponse;
import org.springframework.stereotype.Component;

/**
 * The `DefaultExceptionMapper` class is a Spring component that implements the `BaseExceptionMapper` interface.
 * It provides a default implementation for mapping exceptions to `CommonRuntimeResponse` objects.
 * This class is typically used when no other specific exception mapper is available.
 */
@Component
public class DefaultExceptionMapper implements BaseExceptionMapper {

    /**
     * This method overrides the `cast` method from the `BaseExceptionMapper` interface.
     * It takes a `Throwable` as input and returns a `CommonRuntimeResponse` object.
     * The `CommonRuntimeResponse` object is initialized with the message from the input exception.
     *
     * @param ex The exception to be mapped.
     * @return The mapped `CommonRuntimeResponse`.
     */
    @Override
    @SuppressWarnings("unchecked")
    public CommonRuntimeResponse cast(Throwable ex) {
        return new CommonRuntimeResponse(ex.getMessage());
    }
}