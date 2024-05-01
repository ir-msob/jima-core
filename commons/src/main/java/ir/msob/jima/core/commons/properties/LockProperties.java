package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;


/**
 * This class represents configuration properties for a locking mechanism.
 * <p>
 * It provides properties for specifying the lock expiration duration (`expiration`) and
 * retry behavior (`retry`) in case acquiring the lock fails.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class LockProperties {

    /**
     * The duration after which the acquired lock will automatically expire.
     * <p>
     * This value is specified using the `java.time.Duration` class. Setting this to null
     * or `Duration.ZERO` indicates the lock will not expire automatically.
     */
    private Duration expiration;

    /**
     * Configuration options for retrying to acquire a lock in case of initial failures.
     * <p>
     * This nested `Retry` class provides properties to control the number of attempts
     * (`maxAttempts`), delay between attempts (`delay`, `maxDelay`, `delayExpression`,
     * `maxDelayExpression`), randomization of delays (`random`, `randomExpression`), and
     * backoff multiplier (`multiplier`, `multiplierExpression`).
     */
    private Retry retry = new Retry();


    /**
     * This nested class holds configuration options for retrying to acquire a lock.
     * <p>
     * It allows customization of the retry behavior in case the initial attempt to acquire a lock fails.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    public static class Retry {

        /**
         * The maximum number of attempts to acquire the lock before giving up.
         * <p>
         * Defaults to 30 attempts.
         */
        private long maxAttempts = 30L;

        /**
         * The initial delay (in milliseconds) between retry attempts.
         * <p>
         * Defaults to 500 milliseconds.
         */
        private long delay = 500L;

        /**
         * The maximum delay (in milliseconds) between retry attempts.
         * <p>
         * Defaults to 1000 milliseconds.
         */
        private long maxDelay = 1000L;

        /**
         * A multiplier applied to the delay between retries on subsequent attempts.
         * <p>
         * This allows for increasing delays between attempts. Defaults to 0.1 (10%).
         */
        private double multiplier = 0.1;

        /**
         * An expression (e.g., SpEL) to dynamically calculate the delay between retries.
         * <p>
         * Overrides the static `delay` property if provided.
         */
        private String delayExpression = "";

        /**
         * An expression (e.g., SpEL) to dynamically calculate the maximum delay between retries.
         * <p>
         * Overrides the static `maxDelay` property if provided.
         */
        private String maxDelayExpression = "";

        /**
         * An expression (e.g., SpEL) to dynamically calculate the backoff multiplier for delays.
         * <p>
         * Overrides the static `multiplier` property if provided.
         */
        private String multiplierExpression = "";

        /**
         * A flag indicating if delays between retries should be randomized.
         * <p>
         * Defaults to false. If enabled, the actual delay will be within the range of
         * `delay` and `maxDelay`.
         */
        private boolean random = false;

        /**
         * An expression (e.g., SpEL) to dynamically calculate a random offset for the delay.
         * <p>
         * This property is only used if `random` is set to true.
         */
        private String randomExpression = "";
    }
}