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
}