package ir.msob.jima.core.ral.redisson.commons;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Redisson as a distributed Redis client.
 */
@Configuration
@RequiredArgsConstructor
public class RedissonConfiguration {

    /**
     * Configures a RedissonClient bean that connects to a Redis server.
     *
     * @param properties The LockRedisDatasourceProperties used to obtain Redis server connection information.
     * @return A RedissonClient instance for working with Redis.
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(@Qualifier("lockRedisDatasourceProperties") LockRedisDatasourceProperties properties) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(properties.getUrl());
        return Redisson.create(config);
    }
}
