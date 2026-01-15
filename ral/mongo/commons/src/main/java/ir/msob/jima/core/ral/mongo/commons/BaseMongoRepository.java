package ir.msob.jima.core.ral.mongo.commons;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.ral.mongo.commons.operator.QueryUtil;
import ir.msob.jima.core.ral.mongo.commons.query.MongoQuery;
import ir.msob.jima.core.ral.mongo.commons.query.MongoUpdate;
import org.jspecify.annotations.NonNull;
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
public interface BaseMongoRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>, C extends BaseCriteria<ID>> extends
        BaseRepository<ID, D, C> {

    /**
     * Get the ReactiveMongoTemplate for performing database operations.
     *
     * @return The ReactiveMongoTemplate instance.
     */
    ReactiveMongoTemplate getReactiveMongoTemplate();

    /**
     * Find a single entity based on the provided query.
     *
     * @param criteria The criteria containing the search criteria.
     * @return A Mono that emits the found entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<@NonNull D> findOne(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().findOne(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Find an entity by its ID.
     *
     * @param id The ID of the entity to find.
     * @return A Mono that emits the found entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<@NonNull D> findById(ID id) {
        return getReactiveMongoTemplate().findById(id, getDomainClass());
    }

    /**
     * Find a single entity based on the provided query and apply modifications atomically.
     *
     * @param criteria The criteria containing the search criteria.
     * @return A Mono that emits the modified entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<@NonNull D> findOneAndModify(C criteria, MongoUpdate mongoUpdate) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().findAndModify(mongoQuery.getQuery(), mongoUpdate.getUpdate(),
                new FindAndModifyOptions().returnNew(false), getDomainClass());
    }

    /**
     * Modify an entity based on the provided query and return the modified entity.
     *
     * @param criteria The criteria containing the search criteria and modifications.
     * @return A Mono that emits the modified entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<@NonNull D> modifyAndFindOne(C criteria, MongoUpdate mongoUpdate) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().findAndModify(mongoQuery.getQuery(), mongoUpdate.getUpdate(),
                new FindAndModifyOptions().returnNew(true), getDomainClass());
    }

    /**
     * Find entities based on the provided query.
     *
     * @param criteria The criteria containing the search criteria.
     * @return A Flux that emits the found entities.
     */
    @MethodStats
    default Flux<D> find(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().find(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Find entities and return them as a pageable result.
     *
     * @param criteria The criteria containing the search criteria and paging information.
     * @return A Mono that emits a Page of found entities.
     */
    @MethodStats
    default Mono<@NonNull Page<D>> findPage(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
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
     * @param criteria The criteria containing the search criteria.
     * @return A Mono that emits true if at least one entity exists, or false if none is found.
     */
    @MethodStats
    default Mono<@NonNull Boolean> exists(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().exists(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Check if no entities exist based on the provided query.
     *
     * @param criteria The criteria containing the search criteria.
     * @return A Mono that emits true if no entities exist, or false if at least one is found.
     */
    @MethodStats
    default Mono<@NonNull Boolean> existsNot(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().exists(mongoQuery.getQuery(), getDomainClass()).map(result -> !result);
    }

    /**
     * Update the first entity based on the provided query and modifications.
     *
     * @param criteria The criteria containing the search criteria and modifications.
     * @return A Mono that emits true if the update is successful, or false if no entity is updated.
     */
    @MethodStats
    default Mono<@NonNull Boolean> updateFirst(C criteria, MongoUpdate mongoUpdate) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().updateFirst(mongoQuery.getQuery(), mongoUpdate.getUpdate(), getDomainClass())
                .map(QueryUtil::updateResultBoolean);
    }

    /**
     * Update an entity or insert it if it doesn't exist based on the provided query and modifications.
     *
     * @param criteria The criteria containing the search criteria and modifications.
     * @return A Mono that emits true if the update or insert is successful.
     */
    @MethodStats
    default Mono<@NonNull Boolean> upsert(C criteria, MongoUpdate mongoUpdate) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().upsert(mongoQuery.getQuery(), mongoUpdate.getUpdate(), getDomainClass())
                .map(QueryUtil::updateResultBoolean);
    }

    /**
     * Update multiple entities based on the provided query and modifications.
     *
     * @param criteria The criteria containing the search criteria and modifications.
     * @return A Mono that emits true if the update is successful.
     */
    @MethodStats
    default Mono<@NonNull Boolean> updateMulti(C criteria, MongoUpdate mongoUpdate) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().updateMulti(mongoQuery.getQuery(), mongoUpdate.getUpdate(), getDomainClass())
                .map(QueryUtil::updateResultBoolean);
    }

    /**
     * Delete entities based on the provided query.
     *
     * @param criteria The criteria containing the search criteria.
     * @return A Mono that emits true if the delete is successful, or false if no entity is deleted.
     */
    @MethodStats
    default Mono<@NonNull Boolean> delete(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().remove(mongoQuery.getQuery(), getDomainClass())
                .map(QueryUtil::deleteResultBoolean);
    }

    /**
     * Find and remove entities based on the provided query.
     *
     * @param criteria The criteria containing the search criteria.
     * @return A Flux that emits the found and removed entities.
     */
    @MethodStats
    default Flux<@NonNull D> findAndRemove(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().findAllAndRemove(mongoQuery.getQuery(), getDomainClass());
    }

    /**
     * Find and remove a single entity based on the provided query.
     *
     * @param criteria The criteria containing the search criteria.
     * @return A Mono that emits the found and removed entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<@NonNull D> findOneAndRemove(C criteria) {
        MongoQuery mongoQuery = this.getQueryBuilder().build(criteria);
        return getReactiveMongoTemplate().findAndRemove(mongoQuery.getQuery(), getDomainClass());
    }
}
