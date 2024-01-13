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
import ir.msob.jima.core.commons.exception.validation.InvalidData;
import ir.msob.jima.core.commons.exception.validation.ValidationException;
import ir.msob.jima.core.commons.exception.validation.ValidationResponse;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionUtilTest {

    @Test
    void testCastBadRequestException() {
        // Test casting a BadRequestException to a BadRequestResponse
        String fieldName = "field";
        String value = "invalid";
        BadRequestException ex = new BadRequestException(fieldName, value);
        BadRequestResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(fieldName, response.getFieldName());
        assertEquals(value, response.getValue());
    }

    @Test
    void testCastConflictException() {
        // Test casting a ConflictException to a ConflictResponse
        String message = "Conflict occurred";
        ConflictException ex = new ConflictException(message);
        ConflictResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(message, response.getMessage());
    }

    @Test
    void testCastDataNotFoundException() {
        // Test casting a DataNotFoundException to a DataNotFoundResponse
        String entity = "Entity";
        String entityId = "123";
        Class<?> entityClass = Object.class;
        DataNotFoundException ex = new DataNotFoundException(entity, entity, entityId, entityClass);
        DataNotFoundResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(entity, response.getEntity());
        assertEquals(entityId, response.getEntityId());
        assertEquals(entityClass, response.getEntityClass());
    }

    @Test
    void testCastDomainNotFoundException() {
        // Test casting a DomainNotFoundException to a DomainNotFoundResponse
        String domainId = "456";
        Class<?> domainClass = String.class;
        DomainNotFoundException ex = new DomainNotFoundException(domainId, domainClass);
        DomainNotFoundResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(domainId, response.getDomainId());
        assertEquals(domainClass, response.getDomainClass());
    }

    @Test
    void testCastResourceNotFoundException() {
        // Test casting a ResourceNotFoundException to a ResourceNotFoundResponse
        String message = "456";
        String resource = "456";
        ResourceNotFoundException ex = new ResourceNotFoundException(message, resource);
        ResourceNotFoundResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(message, response.getMessage());
        assertEquals(resource, response.getResource());
    }

    @Test
    void testCastCommonRuntimeException() {
        // Test casting a CommonRuntimeException to a CommonRuntimeResponse
        String message = "Runtime error";
        CommonRuntimeException ex = new CommonRuntimeException(message);
        CommonRuntimeResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(message, response.getMessage());
    }

    @Test
    void testCastValidationException() {
        // Test casting a ValidationException to a ValidationResponse
        String message = "Validation failed";
        Collection<InvalidData> invalidData = null; // Replace with a valid collection
        ValidationException ex = new ValidationException(message, invalidData);
        ValidationResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(message, response.getMessage());
        assertEquals(invalidData, response.getInvalidData());
    }

    @Test
    void testCastDuplicateException() {
        // Test casting a DuplicateException to a DuplicateResponse
        String key = "key";
        String value = "duplicate";
        String message = "Duplicate entry";
        DuplicateException ex = new DuplicateException(message, key, value);
        DuplicateResponse response = ExceptionUtil.cast(ex);

        assertNotNull(response);
        assertEquals(key, response.getKey());
        assertEquals(value, response.getValue());
        assertEquals(message, response.getMessage());
    }

    @Test
    void testCastNullException() {
        // Test casting a null exception, should return null response
        AbstractExceptionResponse response = ExceptionUtil.cast(null);
        assertNull(response);
    }
}
