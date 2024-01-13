package ir.msob.jima.core.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomCharsUtilTest {

    @Test
    void testGenerateWithCustomCharacterSet() {
        // Test the generate method with a custom character set
        String customCharacterSet = "ABC123";
        int length = 10;
        String randomString = RandomCharsUtil.generate(customCharacterSet, length);

        // Check if the generated string has the expected length
        assertEquals(length, randomString.length());

        // Check if all characters in the generated string belong to the custom character set
        for (char c : randomString.toCharArray()) {
            assertTrue(customCharacterSet.contains(String.valueOf(c)));
        }
    }

    @Test
    void testGenerateDefaultCharacterSet() {
        // Test the generate method with the default character set (uppercase, lowercase, and numbers)
        int length = 15;
        String randomString = RandomCharsUtil.generate(length);

        // Check if the generated string has the expected length
        assertEquals(length, randomString.length());

        // Check if all characters in the generated string belong to the default character set
        for (char c : randomString.toCharArray()) {
            assertTrue(RandomCharsUtil.ALPHABET_UPPERCASE.contains(String.valueOf(c))
                    || RandomCharsUtil.ALPHABET_LOWERCASE.contains(String.valueOf(c))
                    || RandomCharsUtil.NUMBER.contains(String.valueOf(c)));
        }
    }

    @Test
    void testGenerateNumber() {
        // Test the generateNumber method for generating random numbers
        int length = 8;
        String randomNumber = RandomCharsUtil.generateNumber(length);

        // Check if the generated number string has the expected length
        assertEquals(length, randomNumber.length());

        // Check if all characters in the generated number string are digits
        for (char c : randomNumber.toCharArray()) {
            assertTrue(Character.isDigit(c));
        }
    }

    @Test
    void testGenerateFaNumber() {
        // Test the generateFaNumber method for generating random Persian/Farsi numbers
        int length = 5;
        String randomFaNumber = RandomCharsUtil.generateFaNumber(length);

        // Check if the generated Persian/Farsi number string has the expected length
        assertEquals(length, randomFaNumber.length());

        // Check if all characters in the generated Persian/Farsi number string are Persian/Farsi digits
        for (char c : randomFaNumber.toCharArray()) {
            assertTrue(RandomCharsUtil.FA_NUMBER.contains(String.valueOf(c)));
        }
    }
}

