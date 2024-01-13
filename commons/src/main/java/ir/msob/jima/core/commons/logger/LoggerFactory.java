package ir.msob.jima.core.commons.logger;

import org.apache.commons.logging.LogFactory;

/**
 * A factory class for obtaining loggers.
 */
public class LoggerFactory {

    private LoggerFactory() {
    }

    /**
     * Get a logger for a given class.
     *
     * @param clazz The class for which the logger is obtained.
     * @return A logger instance for the specified class.
     */
    public static Logger getLog(Class<?> clazz) {
        return new Logger(LogFactory.getLog(clazz));
    }

    /**
     * Get a logger by name.
     *
     * @param name The name of the logger.
     * @return A logger instance with the specified name.
     */
    public static Logger getLog(String name) {
        return new Logger(LogFactory.getLog(name));
    }
}
