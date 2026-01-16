package ir.msob.jima.core.ral.mongo.sequence;

import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.ral.mongo.commons.query.MongoQuery;
import ir.msob.jima.core.ral.mongo.commons.query.MongoUpdate;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository for managing and retrieving sequences using MongoDB.
 */
@Repository
@RequiredArgsConstructor
public class SequenceRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    /**
     * Get the next sequence value for the specified key.
     *
     * @param key The key for which to retrieve the sequence.
     * @return A Mono that emits the next sequence value.
     */
    public Mono<@NonNull Long> getSequence(String key) {
        return getSequence(key, 1);
    }

    /**
     * Get the next sequence value for the specified key with an incremental step.
     *
     * @param key The key for which to retrieve the sequence.
     * @param inc The incremental step by which to increase the sequence value.
     * @return A Mono that emits the next sequence value.
     * @throws CommonRuntimeException if 'inc' is less than 1.
     */
    public Mono<@NonNull Long> getSequence(String key, long inc) {
        if (inc < 1)
            throw new CommonRuntimeException("inc param must be greater than zero.");
        return this.findAndModify(
                        MongoQuery.builder()
                                .build()
                                .is(Sequence.FN.id.name(), key),
                        MongoUpdate.builder().build()
                                .inc(Sequence.FN.value.name(), inc),
                        new FindAndModifyOptions()
                                .returnNew(true)
                                .upsert(true))
                .map(Sequence::getValue);
    }

    /**
     * Find and modify a sequence document based on the provided query and options.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @param options    The FindAndModifyOptions for the update operation.
     * @return A Mono that emits the modified sequence document.
     */
    private Mono<@NonNull Sequence> findAndModify(MongoQuery mongoQuery, MongoUpdate mongoUpdate, FindAndModifyOptions options) {
        return reactiveMongoTemplate
                .findAndModify(
                        mongoQuery.getQuery()
                        , mongoUpdate.getUpdate()
                        , options
                        , Sequence.class);
    }
}
