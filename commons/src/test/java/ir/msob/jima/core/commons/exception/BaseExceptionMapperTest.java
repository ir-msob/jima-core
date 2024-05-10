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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class BaseExceptionMapperTest {

    private BaseExceptionMapper baseExceptionMapper;

    @BeforeEach
    void setUp() {
        baseExceptionMapper = new BaseExceptionMapper() {
            @Override
            public <ER extends AbstractExceptionResponse> ER cast(Throwable ex) {
                return null;
            }
        };
    }

    @Test
    @DisplayName("Should map BadRequestException to BadRequestResponse")
    void shouldMapBadRequestException() {
        BadRequestException exception = new BadRequestException("message", "field", "value");
        assertInstanceOf(BadRequestResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    @Test
    @DisplayName("Should map ConflictException to ConflictResponse")
    void shouldMapConflictException() {
        ConflictException exception = new ConflictException("message");
        assertInstanceOf(ConflictResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    @Test
    @DisplayName("Should map DataNotFoundException to DataNotFoundResponse")
    void shouldMapDataNotFoundException() {
        DataNotFoundException exception = new DataNotFoundException("entity", "id", "class");
        assertInstanceOf(DataNotFoundResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    @Test
    @DisplayName("Should map DomainNotFoundException to DomainNotFoundResponse")
    void shouldMapDomainNotFoundException() {
        DomainNotFoundException exception = new DomainNotFoundException("id", null);
        assertInstanceOf(DomainNotFoundResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    @Test
    @DisplayName("Should map CommonRuntimeException to CommonRuntimeResponse")
    void shouldMapCommonRuntimeException() {
        CommonRuntimeException exception = new CommonRuntimeException("message");
        assertInstanceOf(CommonRuntimeResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    @Test
    @DisplayName("Should map ValidationException to ValidationResponse")
    void shouldMapValidationException() {
        ValidationException exception = new ValidationException("message", null);
        assertInstanceOf(ValidationResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    @Test
    @DisplayName("Should map DuplicateException to DuplicateResponse")
    void shouldMapDuplicateException() {
        DuplicateException exception = new DuplicateException("key", "value", "message");
        assertInstanceOf(DuplicateResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    @Test
    @DisplayName("Should map ResourceNotFoundException to ResourceNotFoundResponse")
    void shouldMapResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("message", "resource");
        assertInstanceOf(ResourceNotFoundResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }
}