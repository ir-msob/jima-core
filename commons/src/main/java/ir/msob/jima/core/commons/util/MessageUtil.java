package ir.msob.jima.core.commons.util;

/**
 * Utility class for preparing messages with placeholders and parameters.
 */
public class MessageUtil {
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
        if (params == null)
            return message;
        for (Object param : params) {
            if (param == null)
                param = "null";
            message = message.replaceFirst("\\{\\}", String.valueOf(param));
        }
        return message;
    }
}
