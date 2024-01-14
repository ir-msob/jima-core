package ir.msob.jima.core.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for encoding and decoding numbers in base N.
 * <p>
 * BaseN provides methods to encode and decode numbers using a custom character set.
 * It allows you to encode a number into a custom base using a specified character set, and then decode it back into a number.
 * This can be useful for creating short URLs or unique identifiers with a custom character set.
 */
public class BaseN {
    // A URL-safe character set for encoding
    public static final String CHARACTERS_URL_SAFE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private BaseN() {
    }

    /**
     * Encodes a number into a URL-safe string using the specified character set.
     *
     * @param number The number to encode.
     * @return The encoded string.
     */
    public static String encodeUrlSafe(Long number) {
        return encode(number, CHARACTERS_URL_SAFE);
    }

    /**
     * Encodes a number into a string using the specified character set.
     *
     * @param number     The number to encode.
     * @param characters The character set to use for encoding.
     * @return The encoded string.
     */
    public static String encode(Long number, String characters) {
        char[] chars = characters.toCharArray();
        Long base = (long) (characters.length() - 1);

        List<Long> list = new ArrayList<>();
        divide(number, base, list);
        return prepareEncoded(chars, list);
    }

    /**
     * Helper method to prepare the encoded string.
     *
     * @param chars The character set to use for encoding.
     * @param list  The list of numbers to encode.
     * @return The encoded string.
     */
    private static String prepareEncoded(char[] chars, List<Long> list) {
        StringBuilder result = new StringBuilder();
        for (Long l : list) {
            result.append(chars[l.intValue()]);
        }
        return result.toString();
    }

    /**
     * Decodes a URL-safe string into a number using the specified character set.
     *
     * @param encoded The encoded string to decode.
     * @return The decoded number.
     */
    public static Long decodeUrlSafe(String encoded) {
        return decode(encoded, CHARACTERS_URL_SAFE);
    }

    /**
     * Decodes a string into a number using the specified character set.
     *
     * @param str        The encoded string to decode.
     * @param characters The character set used for encoding.
     * @return The decoded number.
     */
    public static Long decode(String str, String characters) {
        char[] chars = characters.toCharArray();
        Long base = (long) (characters.length() - 1);

        List<Long> list = new ArrayList<>();
        prepareIndexes(str, chars, list);
        return prepareNumber(base, list);
    }

    /**
     * Helper method to prepare the indexes for decoding.
     *
     * @param str   The encoded string to decode.
     * @param chars The character set used for encoding.
     * @param list  The list to store the indexes.
     */
    private static void prepareIndexes(String str, char[] chars, List<Long> list) {
        Map<Character, Long> charIndexMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            charIndexMap.put(chars[i], (long) i);
        }

        char[] chs = str.toCharArray();
        for (char c : chs) {
            if (charIndexMap.containsKey(c)) {
                list.add(charIndexMap.get(c));
            }
        }
    }

    /**
     * Helper method to prepare the number from the list of indexes.
     *
     * @param base The base of the number system.
     * @param list The list of indexes.
     * @return The decoded number.
     */
    private static Long prepareNumber(Long base, List<Long> list) {
        for (int j = list.size() - 1; j >= 0; j--) {
            if (j > 0) {
                list.set(j - 1, list.get(j) * base + list.get(j - 1));
            }
        }
        return list.get(0);
    }

    /**
     * Helper method to divide the number by the base and store the remainder in the list.
     *
     * @param number The number to divide.
     * @param base   The base of the number system.
     * @param list   The list to store the remainder.
     */
    private static void divide(Long number, Long base, List<Long> list) {
        if (number > base) {
            list.add(number % base);
            divide(number / base, base, list);
        } else {
            list.add(number);
        }
    }
}