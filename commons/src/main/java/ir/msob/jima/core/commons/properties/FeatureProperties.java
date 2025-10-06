package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class FeatureProperties {

    private String databaseType = "none"; // mongodb, oracle, postgres and ...
    private boolean databaseEnabled = false;
}