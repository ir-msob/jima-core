package ir.msob.jima.core.ral.sql.commons;

import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.ral.sql.commons.query.SqlQuery;
import ir.msob.jima.core.ral.sql.commons.query.SqlUpdate;
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
public interface BaseSqlRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>> extends BaseRepository<ID, D> {

    R2dbcEntityTemplate getR2dbcEntityTemplate();

    @MethodStats
    default Mono<D> findOne(SqlQuery<D> sqlQuery) {
        Query q = (sqlQuery == null) ? Query.empty() : sqlQuery.toQuery();
        return getR2dbcEntityTemplate().selectOne(q, getDomainClass());
    }

    @MethodStats
    default Mono<D> findById(ID id) {
        return getR2dbcEntityTemplate().selectOne(Query.query(Criteria.where("id").is(id)), getDomainClass());
    }

    @MethodStats
    default Flux<D> find(SqlQuery<D> sqlQuery) {
        Query q = (sqlQuery == null) ? Query.empty() : sqlQuery.toQuery();
        if (sqlQuery != null && sqlQuery.getLimit() != null) q = q.limit(sqlQuery.getLimit());
        return getR2dbcEntityTemplate().select(q, getDomainClass());
    }

    @MethodStats
    default Mono<Page<D>> findPage(SqlQuery<D> sqlQuery) {
        Query base = (sqlQuery == null) ? Query.empty() : sqlQuery.toQuery();
        Query countQuery = (sqlQuery != null && sqlQuery.getCriteria() != null) ? Query.query(sqlQuery.getCriteria()) : Query.empty();

        return getR2dbcEntityTemplate().count(countQuery, getDomainClass())
                .flatMap(total -> {
                    if (total == null || total < 1) {
                        Pageable p = (sqlQuery != null) ? sqlQuery.getPageable() : Pageable.unpaged();
                        return Mono.just(new PageImpl<D>(new ArrayList<>(), p, 0L));
                    }
                    Query q = base;
                    return getR2dbcEntityTemplate().select(q, getDomainClass()).collectList()
                            .map(list -> (Page<D>) new PageImpl<>(list, sqlQuery.getPageable(), total));
                });
    }

    @MethodStats
    default Mono<Boolean> exists(SqlQuery<D> sqlQuery) {
        Query q = (sqlQuery == null) ? Query.empty() : (sqlQuery.getCriteria() != null ? Query.query(sqlQuery.getCriteria()) : Query.empty());
        return getR2dbcEntityTemplate().count(q, getDomainClass()).map(c -> c != null && c > 0);
    }

    @MethodStats
    default Mono<Boolean> updateFirst(SqlQuery<D> sqlQuery, SqlUpdate<D> update) {
        if (update == null) return Mono.just(false);

        if (update.hasRawSql()) {
            // Raw SQL path: delegate to DatabaseClient for custom SQL
            throw new UnsupportedOperationException("Raw SQL update path is not implemented in base interface. Override in concrete repository to use DatabaseClient.");
        }

        Query q = (sqlQuery == null) ? Query.empty() : sqlQuery.toQuery();
        return getR2dbcEntityTemplate().update(q, update.getUpdate(), getDomainClass()).map(i -> i != null && i > 0);
    }

    @MethodStats
    default Mono<Boolean> delete(SqlQuery<D> sqlQuery) {
        Query q = (sqlQuery == null) ? Query.empty() : sqlQuery.toQuery();
        return getR2dbcEntityTemplate().delete(q, getDomainClass()).map(i -> i != null && i > 0);
    }
}
