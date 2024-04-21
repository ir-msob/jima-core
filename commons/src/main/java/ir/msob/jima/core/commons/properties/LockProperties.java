package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class LockProperties {
    private Duration expiration;

    private Retry retry = new Retry();


    @Setter
    @Getter
    @NoArgsConstructor
    public static class Retry {
        private long maxAttempts = 30L;
        private long delay = 500L;
        private long maxDelay = 1000L;
        private double multiplier = 0.1;
        private String delayExpression = "";
        private String maxDelayExpression = "";
        private String multiplierExpression = "";
        private boolean random = false;
        private String randomExpression = "";
    }
}