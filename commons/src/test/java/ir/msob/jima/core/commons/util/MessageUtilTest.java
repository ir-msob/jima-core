package ir.msob.jima.core.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageUtilTest {

    @Test
    void testPrepareMessageWithNoPlaceholders() {
        // Test when there are no placeholders in the message.
        String message = "This is a sample message";
        String formattedMessage = MessageUtil.prepareMessage(message);
        assertEquals(message, formattedMessage);
    }

    @Test
    void testPrepareMessageWithPlaceholders() {
        // Test when there are placeholders and parameters.
        String message = "Hello, {}! Today is {}.";
        String name = "John";
        String day = "Monday";
        String expected = "Hello, John! Today is Monday.";
        String formattedMessage = MessageUtil.prepareMessage(message, name, day);
        assertEquals(expected, formattedMessage);
    }

    @Test
    void testPrepareMessageWithEscapedPlaceholders() {
        // Test when the message contains escaped placeholders.
        String message = "This is an escaped placeholder: {{}}";
        String formattedMessage = MessageUtil.prepareMessage(message);
        assertEquals(message, formattedMessage);
    }

    @Test
    void testPrepareMessageWithAdditionalPlaceholders() {
        // Test when there are more placeholders than parameters.
        String message = "Hello, {}! Today is {}.";
        String name = "John";
        String expected = "Hello, John! Today is {}."; // Second placeholder remains unfulfilled.
        String formattedMessage = MessageUtil.prepareMessage(message, name);
        assertEquals(expected, formattedMessage);
    }

    @Test
    void testPrepareMessageWithAdditionalParameters() {
        // Test when there are more parameters than placeholders.
        String message = "Hello, {}!";
        String name = "John";
        String additionalParam = "Smith";
        String expected = "Hello, John!";
        String formattedMessage = MessageUtil.prepareMessage(message, name, additionalParam);
        assertEquals(expected, formattedMessage);
    }

    @Test
    void testPrepareMessageWithNullParameters() {
        // Test when parameters include null values.
        String message = "Hello, {}!";
        String expected = "Hello, null!";
        String formattedMessage = MessageUtil.prepareMessage(message, (Object) null);
        assertEquals(expected, formattedMessage);
    }
}
