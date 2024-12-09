package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;

/**
 * The `RetryProperties` class is a simple POJO (Plain Old Java Object) that holds retry-child properties.
 * It uses Lombok annotations for automatic generation of getters, setters, a no-argument constructor, and a toString method.
 * The `maxAttempts` field is an integer that represents the maximum number of retry attempts.
 * The `backoff` field is an instance of the nested `Backoff` class, which holds backoff-child properties.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class RetryProperties {
    /**
     * The `maxAttempts` field is an integer that represents the maximum number of retry attempts.
     */
    private int maxAttempts;

    /**
     * The `backoff` field is an instance of the `Backoff` class.
     * This field holds backoff-child properties for the retry mechanism.
     */
    private Backoff backoff = new Backoff();

    public RetryBackoffSpec createRetryBackoffSpec() {
        return Retry.backoff(this.getMaxAttempts(), Duration.ofMillis(this.getBackoff().getInitialInterval()))
                .maxBackoff(Duration.ofMillis(this.getBackoff().getMaxInterval()))
                .jitter(this.getBackoff().getMultiplier());
    }

    /**
     * The `Backoff` class is a static nested class inside `RetryProperties`.
     * It holds backoff-child properties such as `initialInterval`, `multiplier`, and `maxInterval`.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Backoff {
        /**
         * The `initialInterval` field is a long that represents the initial interval for the backoff mechanism.
         */
        private long initialInterval;

        /**
         * The `multiplier` field is a double that represents the multiplier for the backoff mechanism.
         */
        private double multiplier;

        /**
         * The `maxInterval` field is a long that represents the maximum interval for the backoff mechanism.
         */
        private long maxInterval;
    }
}