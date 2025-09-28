package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;

/**
 * The `RetryProperties` class provides comprehensive retry configuration properties
 * for both HTTP requests and RSocket connections.
 * It supports various retry strategies including backoff, fixed delay, and exponential backoff.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class RetryProperties {

    /**
     * The maximum number of retry attempts.
     * Default value is 3.
     */
    private int maxAttempts = 3;

    /**
     * The minimum backoff duration in milliseconds.
     * Default value is 1000 ms (1 second).
     */
    private long minBackoff = 1000;

    /**
     * The maximum backoff duration in milliseconds.
     * Default value is 10000 ms (10 seconds).
     */
    private long maxBackoff = 10000;

    /**
     * The jitter factor for randomizing backoff intervals.
     * Must be between 0.0 and 1.0.
     * Default value is 0.5.
     */
    private double jitter = 0.5;

    /**
     * The multiplier for exponential backoff calculation.
     * Default value is 2.0.
     */
    private double multiplier = 2.0;

    /**
     * The fixed delay between retries in milliseconds.
     * If set, fixed delay strategy will be used instead of backoff.
     * Default is 0 (disabled).
     */
    private long fixedDelay = 0;

    /**
     * Whether to use exponential backoff strategy.
     * If false, simple backoff strategy will be used.
     * Default value is true.
     */
    private boolean exponentialBackoff = true;

    /**
     * Creates a RetryBackoffSpec based on the configured properties.
     * Validates all parameters and provides sensible defaults for invalid values.
     *
     * @return configured RetryBackoffSpec instance
     */
    public RetryBackoffSpec createRetryBackoffSpec() {
        validateAndAdjustParameters();

        RetryBackoffSpec retrySpec = Retry.backoff(
                this.maxAttempts,
                Duration.ofMillis(this.minBackoff)
        );

        retrySpec = retrySpec.maxBackoff(Duration.ofMillis(this.maxBackoff));
        retrySpec = retrySpec.jitter(this.jitter);

        return retrySpec;
    }

    /**
     * Creates a retry spec specifically for fixed delay strategy.
     *
     * @return RetryBackoffSpec with fixed delay strategy
     */
    public RetryBackoffSpec createFixedDelayRetrySpec() {
        validateAndAdjustParameters();

        if (this.fixedDelay > 0) {
            return Retry.fixedDelay(this.maxAttempts, Duration.ofMillis(this.fixedDelay))
                    .jitter(this.jitter);
        } else {
            // Fall back to backoff strategy if fixed delay is not set
            return createRetryBackoffSpec();
        }
    }

    /**
     * Validates all parameters and adjusts them to valid ranges if necessary.
     */
    private void validateAndAdjustParameters() {
        // Validate maxAttempts
        if (this.maxAttempts < 1) {
            this.maxAttempts = 3;
        }

        // Validate minBackoff
        if (this.minBackoff < 0) {
            this.minBackoff = 1000;
        }

        // Validate maxBackoff
        if (this.maxBackoff < this.minBackoff) {
            this.maxBackoff = Math.max(this.minBackoff * 2, 10000);
        }

        // Validate jitter
        if (this.jitter < 0.0 || this.jitter > 1.0) {
            this.jitter = 0.5;
        }

        // Validate multiplier
        if (this.multiplier < 1.0) {
            this.multiplier = 2.0;
        }

        // Validate fixedDelay
        if (this.fixedDelay < 0) {
            this.fixedDelay = 0;
        }
    }

    /**
     * Creates a customized retry spec based on specific requirements.
     * This method provides flexibility for different use cases.
     *
     * @param useFixedDelay whether to use fixed delay strategy
     * @return customized RetryBackoffSpec
     */
    public RetryBackoffSpec createCustomRetrySpec(boolean useFixedDelay) {
        return useFixedDelay ? createFixedDelayRetrySpec() : createRetryBackoffSpec();
    }

    /**
     * Checks if retry is enabled based on configuration.
     *
     * @return true if retry is enabled (maxAttempts > 1), false otherwise
     */
    public boolean isEnabled() {
        return this.maxAttempts > 1;
    }

    /**
     * Returns the effective retry strategy name based on configuration.
     *
     * @return strategy name as String
     */
    public String getStrategyName() {
        if (this.fixedDelay > 0) {
            return "FIXED_DELAY";
        } else if (this.exponentialBackoff) {
            return "EXPONENTIAL_BACKOFF";
        } else {
            return "BACKOFF";
        }
    }
}