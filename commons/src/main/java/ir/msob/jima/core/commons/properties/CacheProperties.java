package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class CacheProperties {

    private List<CacheConfiguration> cacheConfigs = new ArrayList<>();
    private long defaultTtl = 300; // seconds

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class CacheConfiguration {
        private Set<String> cacheName = new HashSet<>();
        private long ttl = 0; // seconds
    }

}
