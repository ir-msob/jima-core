package ir.msob.jima.core.commons.logger;

import ir.msob.jima.core.commons.util.MessageUtil;
import org.apache.commons.logging.Log;

/**
 * The 'Logger' class provides logging functionality and wraps an underlying Log implementation.
 * It includes methods for logging debug, error, fatal, info, trace, and warning messages, with optional parameters for formatting the message and an associated exception.
 * The class also includes a constructor for creating an instance of the logger with a specified Log implementation.
 *
 * @param logger The underlying Log implementation.
 */
public record Logger(Log logger) {

    /**
     * Log a debug message.
     *
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void debug(String message, Object... params) {
        logger.debug(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a debug message with an associated exception.
     *
     * @param t       The associated exception.
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void debug(Throwable t, String message, Object... params) {
        logger.debug(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log an error message.
     *
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void error(String message, Object... params) {
        logger.error(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log an error message with an associated exception.
     *
     * @param t       The associated exception.
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void error(Throwable t, String message, Object... params) {
        logger.error(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log an error exception.
     *
     * @param t The exception to logger.
     */
    public void error(Throwable t) {
        logger.error(t);
    }

    /**
     * Log a fatal message.
     *
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void fatal(String message, Object... params) {
        logger.fatal(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a fatal message with an associated exception.
     *
     * @param message The message to logger.
     * @param t       The associated exception.
     * @param params  Optional parameters to format the message.
     */
    public void fatal(String message, Throwable t, Object... params) {
        logger.fatal(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log an info message.
     *
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void info(String message, Object... params) {
        logger.info(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log an info message with an associated exception.
     *
     * @param t       The associated exception.
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void info(Throwable t, String message, Object... params) {
        logger.info(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log a trace message.
     *
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void trace(String message, Object... params) {
        logger.trace(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a trace message with an associated exception.
     *
     * @param t       The associated exception.
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void trace(Throwable t, String message, Object... params) {
        logger.trace(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log a warning message.
     *
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void warn(String message, Object... params) {
        logger.warn(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a warning message with an associated exception.
     *
     * @param t       The associated exception.
     * @param message The message to logger.
     * @param params  Optional parameters to format the message.
     */
    public void warn(Throwable t, String message, Object... params) {
        logger.warn(MessageUtil.prepareMessage(message, params), t);
    }
}