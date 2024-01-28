package ir.msob.jima.core.commons.util;

import ir.msob.jima.core.commons.exception.validation.ValidationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

/**
 * This service class provides methods for validating objects or collections of objects.
 */
@Service
@Validated
public class ValidatorService {

    /**
     * Validates a collection of objects.
     *
     * @param objects The collection of objects to validate.
     * @param <T>     The type of objects in the collection.
     * @throws ValidationException if validation fails.
     */
    public <T> void validate(Collection<T> objects) throws ValidationException {
        for (T object : objects) {
            validateObject(object);
        }
    }

    /**
     * Validates a single object.
     *
     * @param object The object to validate.
     * @param <T>    The type of the object.
     * @throws ValidationException if validation fails.
     */
    public <T> void validate(T object) throws ValidationException {
        validateObject(object);
    }

    /**
     * Performs validation operations on a single object.
     *
     * @param object The object to validate.
     * @param <T>    The type of the object.
     * @throws ValidationException if validation fails.
     */
    private <T> void validateObject(@Valid T object) throws ValidationException {
        // Perform the validation operations on the object
        // If the validation operation fails, you can throw a ValidationException.
    }
}
