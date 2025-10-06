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
public class TestProperties {

    private Kafka kafka = new Kafka();

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Kafka extends TestContainerProperties.BaseTestContainer {
        private Duration messageWaitDuration = Duration.ofSeconds(1);
    }
}