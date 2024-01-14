package ir.msob.jima.core.commons.util;

import java.security.SecureRandom;

/**
 * Utility class for generating random strings from predefined character sets.
 * <p>
 * This class provides methods to generate random strings using various character sets,
 * such as uppercase letters, lowercase letters, numbers, and Persian/Farsi numbers.
 *
 *
 */
public class RandomCharsUtil {

    // Character sets
    public static final String ALPHABET_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBER = "0123456789";
    public static final String FA_NUMBER = "۰۱۲۳۴۵۶۷۸۹";

    // SecureRandom instance for generating random numbers
    private static final SecureRandom random = new SecureRandom();

    private RandomCharsUtil() {
        // Private constructor to prevent instantiation; this class is meant to be used statically.
    }

    /**
     * Generate a random string of a specified length using a given character set.
     *
     * @param candidateChars The character set to choose from.
     * @param length         The desired length of the generated string.
     * @return The generated random string.
     */
    public static String generate(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }

    /**
     * Generate a random string of a specified length using a combination of uppercase letters,
     * lowercase letters, and numbers.
     *
     * @param length The desired length of the generated string.
     * @return The generated random string.
     */
    public static String generate(int length) {
        return generate(ALPHABET_UPPERCASE + ALPHABET_LOWERCASE + NUMBER, length);
    }

    /**
     * Generate a random string of numbers with a specified length.
     *
     * @param length The desired length of the generated number string.
     * @return The generated random number string.
     */
    public static String generateNumber(int length) {
        return generate(NUMBER, length);
    }

    /**
     * Generate a random string of Persian/Farsi numbers with a specified length.
     *
     * @param length The desired length of the generated Farsi number string.
     * @return The generated random Farsi number string.
     */
    public static String generateFaNumber(int length) {
        return generate(FA_NUMBER, length);
    }
}
