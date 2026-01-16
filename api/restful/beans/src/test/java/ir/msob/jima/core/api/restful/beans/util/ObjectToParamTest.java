package ir.msob.jima.core.api.restful.beans.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.msob.jima.core.beans.util.JsonParser;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ObjectToParam} class.
 * This class tests the conversion of objects to parameters using the ObjectToParam utility.
 */
class ObjectToParamTest {

    @Mock
    private JsonParser jsonParser;

    private ObjectToParam objectToParam;

    /**
     * Sets up the test environment before each test method.
     * Initializes mocks and the ObjectToParam instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectToParam = new ObjectToParam(jsonParser);
    }

    /**
     * Tests the conversion of objects to parameters.
     * This test is currently disabled.
     *
     * @throws JsonProcessingException if an error occurs during JSON processing.
     */
    @Disabled
    @Test
    void testConvertWithObjects() throws JsonProcessingException {
        // Prepare test data
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("property1", "value1");
        expectedResult.put("property2", "value2");

        Map<String, Object> jsonParserResult = new HashMap<>();
        expectedResult.put("property1", "value1");
        expectedResult.put("property2", "value2");

        // Mock behavior
        Mockito.when(jsonParser.getJsonPaths(expectedResult)).thenReturn(jsonParserResult);

        // Act
        MultiValueMap<@NonNull String, String> result = objectToParam.convert(expectedResult);

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Tests the shouldIncludeProperty method of ObjectToParam.
     * Verifies that the method correctly identifies valid, null, and empty values.
     */
    @Test
    void testShouldIncludeProperty() {
        // Prepare test data
        Object validValue = "valid";
        Object emptyValue = "";

        // Act and Assert
        assertTrue(objectToParam.shouldIncludeProperty(validValue));
        assertFalse(objectToParam.shouldIncludeProperty(null));
        assertFalse(objectToParam.shouldIncludeProperty(emptyValue));
    }
}
