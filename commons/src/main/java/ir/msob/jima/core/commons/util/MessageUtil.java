package ir.msob.jima.core.commons.util;

import java.util.regex.Matcher;

/**
 * Utility class for preparing messages with placeholders and parameters.
 */
public final class MessageUtil {

    private MessageUtil() {
    }

    /**
     * Prepares a formatted message by replacing '{}' placeholders with the provided parameters.
     *
     * @param message The message template with '{}' placeholders.
     * @param params  The parameters to replace placeholders in the message.
     * @return The formatted message with replaced placeholders.
     */
    public static String prepareMessage(String message, Object... params) {
        if (message == null || params == null) {
            return message;
        }

        for (Object param : params) {
            String value = (param == null) ? "null" : String.valueOf(param);

            // IMPORTANT:
            // quoteReplacement prevents IllegalArgumentException
            // when value contains '$' or '\'
            message = message.replaceFirst(
                    "\\{\\}",
                    Matcher.quoteReplacement(value)
            );
        }

        return message;
    }
}
