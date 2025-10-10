package ir.msob.jima.core.ral.mongo.commons;

import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.ral.mongo.commons.operator.QueryUtil;
import ir.msob.jima.core.ral.mongo.commons.query.MongoQuery;
import ir.msob.jima.core.ral.mongo.commons.query.MongoUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A base repository interface for MongoDB, providing common CRUD operations for domain entities.
 *
 * @param <D> The type of the domain.
 */
public interface BaseMongoRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>> extends
        BaseRepository<ID, D> {

    /**
     * Get the ReactiveMongoTemplate for performing database operations.
     *
     * @return The ReactiveMongoTemplate instance.
     */
    ReactiveMongoTemplate getReactiveMongoTemplate();

    /**
     * Find a single entity based on the provided query.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Mono that emits the found entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findOne(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().findOne(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Find an entity by its ID.
     *
     * @param id The ID of the entity to find.
     * @return A Mono that emits the found entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findById(ID id) {
        return getReactiveMongoTemplate().findById(id, getDomainClass());
    }

    /**
     * Find a single entity based on the provided query and apply modifications atomically.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Mono that emits the modified entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findOneAndModify(MongoQuery mongoQuery, MongoUpdate mongoUpdate) {
        return getReactiveMongoTemplate().findAndModify(mongoQuery.getQuery(), mongoUpdate.getUpdate(),
                new FindAndModifyOptions().returnNew(false), getDomainClass());
    }

    /**
     * Modify an entity based on the provided query and return the modified entity.
     *
     * @param mongoQuery The query builder containing the search criteria and modifications.
     * @return A Mono that emits the modified entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> modifyAndFindOne(MongoQuery mongoQuery, MongoUpdate mongoUpdate) {
        return getReactiveMongoTemplate().findAndModify(mongoQuery.getQuery(), mongoUpdate.getUpdate(),
                new FindAndModifyOptions().returnNew(true), getDomainClass());
    }

    /**
     * Find entities based on the provided query.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Flux that emits the found entities.
     */
    @MethodStats
    default Flux<D> find(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().find(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Find entities and return them as a pageable result.
     *
     * @param mongoQuery The query builder containing the search criteria and paging information.
     * @return A Mono that emits a Page of found entities.
     */
    @MethodStats
    default Mono<Page<D>> findPage(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().count(mongoQuery.getQuery(), getDomainClass()).flatMap(count -> {
            if (count == null || count < 1L) {
                return Mono.just(new PageImpl<>(new ArrayList<>(), mongoQuery.getPageable(), 0L));
            } else {
                mongoQuery.getQuery().with(mongoQuery.getPageable());
                return getReactiveMongoTemplate().find(mongoQuery.getQuery(), getDomainClass()).collectList()
                        .flatMap(list -> Mono.just(new PageImpl<>(list, mongoQuery.getPageable(), count)));
            }
        });
    }

    /**
     * Check if at least one entity exists based on the provided query.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Mono that emits true if at least one entity exists, or false if none is found.
     */
    @MethodStats
    default Mono<Boolean> exists(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().exists(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Check if no entities exist based on the provided query.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Mono that emits true if no entities exist, or false if at least one is found.
     */
    @MethodStats
    default Mono<Boolean> existsNot(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().exists(mongoQuery.getQuery(), getDomainClass()).map(result -> !result);
    }

    /**
     * Update the first entity based on the provided query and modifications.
     *
     * @param mongoQuery The query builder containing the search criteria and modifications.
     * @return A Mono that emits true if the update is successful, or false if no entity is updated.
     */
    @MethodStats
    default Mono<Boolean> updateFirst(MongoQuery mongoQuery, MongoUpdate mongoUpdate) {
        return getReactiveMongoTemplate().updateFirst(mongoQuery.getQuery(), mongoUpdate.getUpdate(), getDomainClass())
                .map(QueryUtil::updateResultBoolean);
    }

    /**
     * Update an entity or insert it if it doesn't exist based on the provided query and modifications.
     *
     * @param mongoQuery The query builder containing the search criteria and modifications.
     * @return A Mono that emits true if the update or insert is successful.
     */
    @MethodStats
    default Mono<Boolean> upsert(MongoQuery mongoQuery, MongoUpdate mongoUpdate) {
        return getReactiveMongoTemplate().upsert(mongoQuery.getQuery(), mongoUpdate.getUpdate(), getDomainClass())
                .map(QueryUtil::updateResultBoolean);
    }

    /**
     * Update multiple entities based on the provided query and modifications.
     *
     * @param mongoQuery The query builder containing the search criteria and modifications.
     * @return A Mono that emits true if the update is successful.
     */
    @MethodStats
    default Mono<Boolean> updateMulti(MongoQuery mongoQuery, MongoUpdate mongoUpdate) {
        return getReactiveMongoTemplate().updateMulti(mongoQuery.getQuery(), mongoUpdate.getUpdate(), getDomainClass())
                .map(QueryUtil::updateResultBoolean);
    }

    /**
     * Delete entities based on the provided query.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Mono that emits true if the delete is successful, or false if no entity is deleted.
     */
    @MethodStats
    default Mono<Boolean> delete(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().remove(mongoQuery.getQuery(), getDomainClass())
                .map(QueryUtil::deleteResultBoolean);
    }

    /**
     * Find and remove entities based on the provided query.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Flux that emits the found and removed entities.
     */
    @MethodStats
    default Flux<D> findAndRemove(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().findAllAndRemove(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Find and remove a single entity based on the provided query.
     *
     * @param mongoQuery The query builder containing the search criteria.
     * @return A Mono that emits the found and removed entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findOneAndRemove(MongoQuery mongoQuery) {
        return getReactiveMongoTemplate().findAndRemove(mongoQuery.getQuery(), getDomainClass());
    }
}
