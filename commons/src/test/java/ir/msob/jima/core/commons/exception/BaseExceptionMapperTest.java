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

/**
 * Unit tests for the {@link BaseExceptionMapper} class.
 * This class tests the mapping of various exceptions to their corresponding response types.
 */
class BaseExceptionMapperTest {

    private BaseExceptionMapper baseExceptionMapper;

    /**
     * Sets up the test environment before each test method.
     * Initializes the BaseExceptionMapper instance with a mock implementation.
     */
    @BeforeEach
    void setUp() {
        baseExceptionMapper = new BaseExceptionMapper() {
            @Override
            public <ER extends AbstractExceptionResponse> ER cast(Throwable ex) {
                return null;
            }
        };
    }

    /**
     * Tests the mapping of {@link BadRequestException} to {@link BadRequestResponse}.
     */
    @Test
    @DisplayName("Should map BadRequestException to BadRequestResponse")
    void shouldMapBadRequestException() {
        BadRequestException exception = new BadRequestException("message", "field", "value");
        assertInstanceOf(BadRequestResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    /**
     * Tests the mapping of {@link ConflictException} to {@link ConflictResponse}.
     */
    @Test
    @DisplayName("Should map ConflictException to ConflictResponse")
    void shouldMapConflictException() {
        ConflictException exception = new ConflictException("message");
        assertInstanceOf(ConflictResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    /**
     * Tests the mapping of {@link DataNotFoundException} to {@link DataNotFoundResponse}.
     */
    @Test
    @DisplayName("Should map DataNotFoundException to DataNotFoundResponse")
    void shouldMapDataNotFoundException() {
        DataNotFoundException exception = new DataNotFoundException("entity", "id", "class");
        assertInstanceOf(DataNotFoundResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    /**
     * Tests the mapping of {@link DomainNotFoundException} to {@link DomainNotFoundResponse}.
     */
    @Test
    @DisplayName("Should map DomainNotFoundException to DomainNotFoundResponse")
    void shouldMapDomainNotFoundException() {
        DomainNotFoundException exception = new DomainNotFoundException("id", null);
        assertInstanceOf(DomainNotFoundResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    /**
     * Tests the mapping of {@link CommonRuntimeException} to {@link CommonRuntimeResponse}.
     */
    @Test
    @DisplayName("Should map CommonRuntimeException to CommonRuntimeResponse")
    void shouldMapCommonRuntimeException() {
        CommonRuntimeException exception = new CommonRuntimeException("message");
        assertInstanceOf(CommonRuntimeResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    /**
     * Tests the mapping of {@link ValidationException} to {@link ValidationResponse}.
     */
    @Test
    @DisplayName("Should map ValidationException to ValidationResponse")
    void shouldMapValidationException() {
        ValidationException exception = new ValidationException("message", null);
        assertInstanceOf(ValidationResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    /**
     * Tests the mapping of {@link DuplicateException} to {@link DuplicateResponse}.
     */
    @Test
    @DisplayName("Should map DuplicateException to DuplicateResponse")
    void shouldMapDuplicateException() {
        DuplicateException exception = new DuplicateException("key", "value", "message");
        assertInstanceOf(DuplicateResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }

    /**
     * Tests the mapping of {@link ResourceNotFoundException} to {@link ResourceNotFoundResponse}.
     */
    @Test
    @DisplayName("Should map ResourceNotFoundException to ResourceNotFoundResponse")
    void shouldMapResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("message", "resource");
        assertInstanceOf(ResourceNotFoundResponse.class, baseExceptionMapper.getExceptionResponse(exception));
    }
}