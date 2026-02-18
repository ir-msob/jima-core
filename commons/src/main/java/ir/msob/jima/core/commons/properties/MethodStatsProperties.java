package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class holds the properties embeddeddomain to method statistics logging.
 * It includes properties for warning time threshold, and enabling/disabling info and warning logs.
 * It is marked with the `@ToString` annotation to include all fields in the `toString` method.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class MethodStatsProperties {
    /**
     * The warning time threshold in milliseconds. If a method execution time exceeds this value, a warning log will be generated.
     */
    private long warnTime = 1000;

    /**
     * A flag to enable or disable info level logging for method execution statistics.
     */
    private boolean enabled = false;

    /**
     * A flag to enable or disable warning level logging for method execution statistics.
     */
    private LogLevel logLevel = LogLevel.WARN;

    public enum LogLevel {
        INFO, WARN
    }
}