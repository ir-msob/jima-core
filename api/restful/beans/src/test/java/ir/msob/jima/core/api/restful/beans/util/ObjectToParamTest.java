package ir.msob.jima.core.api.restful.beans.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.msob.jima.core.commons.util.JsonParser;
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

class ObjectToParamTest {

    @Mock
    private JsonParser jsonParser;

    private ObjectToParam objectToParam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectToParam = new ObjectToParam(jsonParser);
    }

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
        MultiValueMap<String, String> result = objectToParam.convert(expectedResult);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testShouldIncludeProperty() {
        // Prepare test data
        Object validValue = "valid";
        Object nullValue = null;
        Object emptyValue = "";

        // Act and Assert
        assertTrue(objectToParam.shouldIncludeProperty(validValue));
        assertFalse(objectToParam.shouldIncludeProperty(nullValue));
        assertFalse(objectToParam.shouldIncludeProperty(emptyValue));
    }
}
