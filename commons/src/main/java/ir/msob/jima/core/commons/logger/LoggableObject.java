package ir.msob.jima.core.commons.logger;

/**
 * Marker interface for objects that provide custom log representation.
 * Similar to toString(), but explicitly for logging.
 */
public interface LoggableObject {
    /**
     * Return a string representation of this object for logging purposes.
     */
    String toLog();
}