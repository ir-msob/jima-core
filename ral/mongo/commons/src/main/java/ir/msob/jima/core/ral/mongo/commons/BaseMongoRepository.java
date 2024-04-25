package ir.msob.jima.core.ral.mongo.commons;

import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.data.BaseRepository;
import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.ral.mongo.commons.operator.QueryUtil;
import ir.msob.jima.core.ral.mongo.commons.query.QueryBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * A base repository interface for MongoDB, providing common CRUD operations for domain entities.
 *
 * @param <USER> The type of the user.
 * @param <D>    The type of the domain.
 *
 */
public interface BaseMongoRepository<USER extends BaseUser, D extends BaseDomain<ObjectId>> extends
        BaseRepository<ObjectId, USER, D> {

    /**
     * Get the ReactiveMongoTemplate for performing database operations.
     *
     * @return The ReactiveMongoTemplate instance.
     */
    ReactiveMongoTemplate getReactiveMongoTemplate();

    /**
     * Find a single entity based on the provided query.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Mono that emits the found entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findOne(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().findOne(queryBuilder.getQuery(), getDomainClass());
    }

    /**
     * Find an entity by its ID.
     *
     * @param id The ID of the entity to find.
     * @return A Mono that emits the found entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findById(ObjectId id) {
        return getReactiveMongoTemplate().findById(id, getDomainClass());
    }

    /**
     * Find a single entity based on the provided query and apply modifications atomically.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Mono that emits the modified entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findOneAndModify(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().findAndModify(queryBuilder.getQuery(), queryBuilder.getUpdate(),
                new FindAndModifyOptions().returnNew(false), getDomainClass());
    }

    /**
     * Modify an entity based on the provided query and return the modified entity.
     *
     * @param queryBuilder The query builder containing the search criteria and modifications.
     * @return A Mono that emits the modified entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> modifyAndFindOne(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().findAndModify(queryBuilder.getQuery(), queryBuilder.getUpdate(),
                new FindAndModifyOptions().returnNew(true), getDomainClass());
    }

    /**
     * Find entities based on the provided query.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Flux that emits the found entities.
     */
    @MethodStats
    default Flux<D> find(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().find(queryBuilder.getQuery(), getDomainClass());
    }

    /**
     * Find entities and return them as a pageable result.
     *
     * @param queryBuilder The query builder containing the search criteria and paging information.
     * @return A Mono that emits a Page of found entities.
     */
    @MethodStats
    default Mono<Page<D>> findPage(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().count(queryBuilder.getQuery(), getDomainClass()).flatMap(count -> {
            if (count == null || count < 1L) {
                return Mono.just(new PageImpl<>(new ArrayList<>(), queryBuilder.getPageable(), 0L));
            } else {
                queryBuilder.with(queryBuilder.getPageable());
                return getReactiveMongoTemplate().find(queryBuilder.getQuery(), getDomainClass()).collectList()
                        .flatMap(list -> Mono.just(new PageImpl<>(list, queryBuilder.getPageable(), count)));
            }
        });
    }

    /**
     * Check if at least one entity exists based on the provided query.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Mono that emits true if at least one entity exists, or false if none is found.
     */
    @MethodStats
    default Mono<Boolean> exists(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().exists(queryBuilder.getQuery(), getDomainClass());
    }

    /**
     * Check if no entities exist based on the provided query.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Mono that emits true if no entities exist, or false if at least one is found.
     */
    @MethodStats
    default Mono<Boolean> existsNot(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().exists(queryBuilder.getQuery(), getDomainClass()).map(result -> !result);
    }

    /**
     * Update the first entity based on the provided query and modifications.
     *
     * @param queryBuilder The query builder containing the search criteria and modifications.
     * @return A Mono that emits true if the update is successful, or false if no entity is updated.
     */
    @MethodStats
    default Mono<Boolean> updateFirst(QueryBuilder queryBuilder) {
        return QueryUtil.updateResultBoolean(
                getReactiveMongoTemplate().updateFirst(queryBuilder.getQuery(), queryBuilder.getUpdate(), getDomainClass()));
    }

    /**
     * Update an entity or insert it if it doesn't exist based on the provided query and modifications.
     *
     * @param queryBuilder The query builder containing the search criteria and modifications.
     * @return A Mono that emits true if the update or insert is successful.
     */
    @MethodStats
    default Mono<Boolean> upsert(QueryBuilder queryBuilder) {
        return QueryUtil
                .updateResultBoolean(getReactiveMongoTemplate().upsert(queryBuilder.getQuery(), queryBuilder.getUpdate(), getDomainClass()));
    }

    /**
     * Update multiple entities based on the provided query and modifications.
     *
     * @param queryBuilder The query builder containing the search criteria and modifications.
     * @return A Mono that emits true if the update is successful.
     */
    @MethodStats
    default Mono<Boolean> updateMulti(QueryBuilder queryBuilder) {
        return QueryUtil.updateResultBoolean(
                getReactiveMongoTemplate().updateMulti(queryBuilder.getQuery(), queryBuilder.getUpdate(), getDomainClass()));
    }

    /**
     * Delete entities based on the provided query.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Mono that emits true if the delete is successful, or false if no entity is deleted.
     */
    @MethodStats
    default Mono<Boolean> delete(QueryBuilder queryBuilder) {
        return QueryUtil.deleteResultBoolean(getReactiveMongoTemplate().remove(queryBuilder.getQuery(), getDomainClass()));
    }

    /**
     * Find and remove entities based on the provided query.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Flux that emits the found and removed entities.
     */
    @MethodStats
    default Flux<D> findAndRemove(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().findAllAndRemove(queryBuilder.getQuery(), getDomainClass());
    }

    /**
     * Find and remove a single entity based on the provided query.
     *
     * @param queryBuilder The query builder containing the search criteria.
     * @return A Mono that emits the found and removed entity or completes empty if none is found.
     */
    @MethodStats
    default Mono<D> findOneAndRemove(QueryBuilder queryBuilder) {
        return getReactiveMongoTemplate().findAndRemove(queryBuilder.getQuery(), getDomainClass());
    }
}
