package ir.msob.jima.core.commons.logger;

import ir.msob.jima.core.commons.util.MessageUtil;
import org.apache.commons.logging.Log;

/**
 * The 'Logger' class provides logging functionality and wraps an underlying Log implementation.
 * It includes methods for logging debug, error, fatal, info, trace, and warning messages, with optional parameters for formatting the message and an associated exception.
 * The class also includes a constructor for creating an instance of the logger with a specified Log implementation.
 *
 *
 */
public class Logger {
    /**
     * The underlying Log implementation.
     */
    private final Log log;

    /**
     * Constructor for the 'Logger' class that takes a Log implementation as a parameter.
     *
     * @param log The Log implementation to use for logging.
     */
    public Logger(Log log) {
        this.log = log;
    }

    /**
     * Log a debug message.
     *
     * @param message The message to log.
     * @param params  Optional parameters to format the message.
     */
    public void debug(String message, Object... params) {
        log.debug(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a debug message with an associated exception.
     *
     * @param message The message to log.
     * @param t       The associated exception.
     * @param params  Optional parameters to format the message.
     */
    public void debug(String message, Throwable t, Object... params) {
        log.debug(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log an error message.
     *
     * @param message The message to log.
     * @param params  Optional parameters to format the message.
     */
    public void error(String message, Object... params) {
        log.error(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log an error message with an associated exception.
     *
     * @param message The message to log.
     * @param t       The associated exception.
     * @param params  Optional parameters to format the message.
     */
    public void error(String message, Throwable t, Object... params) {
        log.error(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log an error exception.
     *
     * @param t The exception to log.
     */
    public void error(Throwable t) {
        log.error(t);
    }

    /**
     * Log a fatal message.
     *
     * @param message The message to log.
     * @param params  Optional parameters to format the message.
     */
    public void fatal(String message, Object... params) {
        log.fatal(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a fatal message with an associated exception.
     *
     * @param message The message to log.
     * @param t       The associated exception.
     * @param params  Optional parameters to format the message.
     */
    public void fatal(String message, Throwable t, Object... params) {
        log.fatal(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log an info message.
     *
     * @param message The message to log.
     * @param params  Optional parameters to format the message.
     */
    public void info(String message, Object... params) {
        log.info(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log an info message with an associated exception.
     *
     * @param message The message to log.
     * @param t       The associated exception.
     * @param params  Optional parameters to format the message.
     */
    public void info(String message, Throwable t, Object... params) {
        log.info(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log a trace message.
     *
     * @param message The message to log.
     * @param params  Optional parameters to format the message.
     */
    public void trace(String message, Object... params) {
        log.trace(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a trace message with an associated exception.
     *
     * @param message The message to log.
     * @param t       The associated exception.
     * @param params  Optional parameters to format the message.
     */
    public void trace(String message, Throwable t, Object... params) {
        log.trace(MessageUtil.prepareMessage(message, params), t);
    }

    /**
     * Log a warning message.
     *
     * @param message The message to log.
     * @param params  Optional parameters to format the message.
     */
    public void warn(String message, Object... params) {
        log.warn(MessageUtil.prepareMessage(message, params));
    }

    /**
     * Log a warning message with an associated exception.
     *
     * @param message The message to log.
     * @param t       The associated exception.
     * @param params  Optional parameters to format the message.
     */
    public void warn(String message, Throwable t, Object... params) {
        log.warn(MessageUtil.prepareMessage(message, params), t);
    }
}