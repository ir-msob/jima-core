package ir.msob.jima.core.ral.hr.commons;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.ral.hr.commons.query.R2dbcQuery;
import ir.msob.jima.core.ral.hr.commons.query.R2dbcUpdate;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * BaseR2dbcSqlRepository: default repository utilities for R2DBC using R2dbcEntityTemplate.
 * Concrete repositories should implement getR2dbcEntityTemplate() and getDomainClass().
 */
public interface BaseR2dbcRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>, C extends BaseCriteria<ID>> extends BaseRepository<ID, D, C> {

    R2dbcEntityTemplate getR2dbcEntityTemplate();

    @MethodStats
    default Mono<@NonNull D> findOne(R2dbcQuery<D> r2dbcQuery) {
        Query q = (r2dbcQuery == null) ? Query.empty() : r2dbcQuery.toQuery();
        return getR2dbcEntityTemplate().selectOne(q, getDomainClass());
    }

    @MethodStats
    default Mono<@NonNull D> findById(ID id) {
        return getR2dbcEntityTemplate().selectOne(Query.query(Criteria.where("id").is(id)), getDomainClass());
    }

    @MethodStats
    default Flux<@NonNull D> find(R2dbcQuery<D> r2dbcQuery) {
        Query q = (r2dbcQuery == null) ? Query.empty() : r2dbcQuery.toQuery();
        if (r2dbcQuery != null && r2dbcQuery.getLimit() != null) q = q.limit(r2dbcQuery.getLimit());
        return getR2dbcEntityTemplate().select(q, getDomainClass());
    }

    @MethodStats
    default Mono<@NonNull Page<@NonNull D>> findPage(R2dbcQuery<D> r2dbcQuery) {
        Query q = (r2dbcQuery == null) ? Query.empty() : r2dbcQuery.toQuery();
        Query countQuery = (r2dbcQuery != null && r2dbcQuery.getCriteria() != null) ? Query.query(r2dbcQuery.getCriteria()) : Query.empty();

        return getR2dbcEntityTemplate().count(countQuery, getDomainClass())
                .flatMap(total -> {
                    if (total < 1) {
                        Pageable p = (r2dbcQuery != null) ? r2dbcQuery.getPageable() : Pageable.unpaged();
                        return Mono.just(new PageImpl<>(new ArrayList<>(), p, 0L));
                    }
                    return getR2dbcEntityTemplate().select(q, getDomainClass()).collectList()
                            .map(list -> (Page<@NonNull D>) new PageImpl<>(list, r2dbcQuery.getPageable(), total));
                });
    }

    @MethodStats
    default Mono<@NonNull Boolean> exists(R2dbcQuery<D> r2dbcQuery) {
        Query q = (r2dbcQuery == null) ? Query.empty() : (r2dbcQuery.getCriteria() != null ? Query.query(r2dbcQuery.getCriteria()) : Query.empty());
        return getR2dbcEntityTemplate().count(q, getDomainClass()).map(c -> c > 0);
    }

    @MethodStats
    default Mono<@NonNull Boolean> updateFirst(R2dbcQuery<D> r2dbcQuery, R2dbcUpdate<D> update) {
        if (update == null) return Mono.just(false);

        if (update.hasRawSql()) {
            // Raw SQL path: delegate to DatabaseClient for custom SQL
            throw new UnsupportedOperationException("Raw SQL update path is not implemented in base interface. Override in concrete repository to use DatabaseClient.");
        }

        Query q = (r2dbcQuery == null) ? Query.empty() : r2dbcQuery.toQuery();
        return getR2dbcEntityTemplate().update(q, update.getUpdate(), getDomainClass()).map(i -> i != null && i > 0);
    }

    @MethodStats
    default Mono<@NonNull Boolean> delete(R2dbcQuery<D> r2dbcQuery) {
        Query q = (r2dbcQuery == null) ? Query.empty() : r2dbcQuery.toQuery();
        return getR2dbcEntityTemplate().delete(q, getDomainClass()).map(i -> i != null && i > 0);
    }
}
