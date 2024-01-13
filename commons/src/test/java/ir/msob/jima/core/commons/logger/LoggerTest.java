package ir.msob.jima.core.commons.logger;

import org.apache.commons.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoggerTest {

    private static Logger logger;
    private static Log log;

    @BeforeEach
    void setUp() {
        log = mock(Log.class);
        logger = new Logger(log);
    }

    @Test
    void testDebugWithoutException() {
        String message = "Debug message without exception";
        logger.debug(message);

        // Verify that the debug method of the underlying log is called with the formatted message
        verify(log).debug(message);
    }

    @Test
    void testDebugWithException() {
        String message = "Debug message with exception";
        Exception exception = new RuntimeException("Test Exception");

        logger.debug(message, exception);

        // Verify that the debug method of the underlying log is called with the formatted message and exception
        verify(log).debug(message, exception);
    }

    // Similar tests for other log levels: error, fatal, info, trace, and warn

    @Test
    void testErrorWithoutException() {
        String message = "Error message without exception";
        logger.error(message);

        verify(log).error(message);
    }

    @Test
    void testErrorWithException() {
        String message = "Error message with exception";
        Exception exception = new RuntimeException("Test Exception");

        logger.error(message, exception);

        verify(log).error(message, exception);
    }

    @Test
    void testErrorWithThrowable() {
        Exception exception = new RuntimeException("Test Exception");

        logger.error(exception);

        verify(log).error(exception);
    }

    @Test
    void testFatalWithoutException() {
        String message = "Fatal message without exception";
        logger.fatal(message);

        verify(log).fatal(message);
    }

    @Test
    void testFatalWithException() {
        String message = "Fatal message with exception";
        Exception exception = new RuntimeException("Test Exception");

        logger.fatal(message, exception);

        verify(log).fatal(message, exception);
    }

    // Similar tests for info, trace, and warn methods

    // Additional tests can be written to cover edge cases and different parameter variations

    @Test
    void testDebugWithParams() {
        String message = "Debug message with parameters: {} and {}";
        Object[] params = {"param1", "param2"};

        logger.debug(message, params);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(log).debug(captor.capture());
        assertEquals("Debug message with parameters: param1 and param2", captor.getValue());
    }

    @Test
    void testDebugWithNullParams() {
        String message = "Debug message with null parameters: {} and {}";
        String[] params = {null, null};

        logger.debug(message, params);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(log).debug(captor.capture());
        assertEquals("Debug message with null parameters: null and null", captor.getValue());
    }
}
