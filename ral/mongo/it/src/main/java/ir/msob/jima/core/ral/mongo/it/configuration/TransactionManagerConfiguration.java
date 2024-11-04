package ir.msob.jima.core.ral.mongo.it.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class for setting up transaction management in a MongoDB context.
 * This class defines beans for both synchronous and reactive transaction managers.
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagerConfiguration {

    /**
     * Creates a primary transaction manager for MongoDB.
     *
     * @param mongoDatabaseFactory the factory to create MongoDB database instances.
     * @return a PlatformTransactionManager for managing transactions.
     */
    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }

    /**
     * Creates a reactive transaction manager for MongoDB.
     *
     * @param reactiveMongoDatabaseFactory the factory to create reactive MongoDB database instances.
     * @return a ReactiveMongoTransactionManager for managing reactive transactions.
     */
    @Bean
    ReactiveMongoTransactionManager reactiveMongoTransactionManager(ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory) {
        return new ReactiveMongoTransactionManager(reactiveMongoDatabaseFactory);
    }
}
