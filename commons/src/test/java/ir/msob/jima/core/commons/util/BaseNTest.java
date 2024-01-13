package ir.msob.jima.core.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseNTest {

    @Test
    void testEncodeUrlSafe() {
        // Test encoding a number into a URL-safe string
        Long number = 123456789L;
        String encoded = BaseN.encodeUrlSafe(number);

        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());

        Long decoded = BaseN.decodeUrlSafe(encoded);
        assertEquals(number, decoded);
    }

    @Test
    void testEncodeWithCustomCharacterSet() {
        // Test encoding a number into a string using a custom character set
        Long number = 987654321L;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encoded = BaseN.encode(number, characters);

        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());

        Long decoded = BaseN.decode(encoded, characters);
        assertEquals(number, decoded);
    }

}
