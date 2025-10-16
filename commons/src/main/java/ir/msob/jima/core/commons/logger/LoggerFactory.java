package ir.msob.jima.core.commons.logger;

import org.apache.commons.logging.LogFactory;

/**
 * The 'LoggerFactory' class is a factory class for obtaining instances of the 'Logger' class.
 * It includes methods for getting a logger for a given class and getting a logger by name.
 * The class also includes a private constructor to prevent instantiation of the factory class.
 */
public class LoggerFactory {

    /**
     * Private constructor to prevent instantiation of the factory class.
     */
    private LoggerFactory() {
    }

    /**
     * Get a logger for a given class.
     * This method creates a new instance of the 'Logger' class using the LogFactory's 'getLog' method with the class as a parameter.
     *
     * @param clazz The class for which the logger is obtained.
     * @return A logger instance for the specified class.
     */
    public static Logger getLogger(Class<?> clazz) {
        return new Logger(LogFactory.getLog(clazz));
    }

    /**
     * Get a logger by name.
     * This method creates a new instance of the 'Logger' class using the LogFactory's 'getLog' method with the name as a parameter.
     *
     * @param name The name of the logger.
     * @return A logger instance with the specified name.
     */
    public static Logger getLogger(String name) {
        return new Logger(LogFactory.getLog(name));
    }
}