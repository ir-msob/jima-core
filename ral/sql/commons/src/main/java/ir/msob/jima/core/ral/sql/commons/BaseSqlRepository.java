package ir.msob.jima.core.ral.sql.commons;

import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.commons.util.GenericTypeUtil;
import ir.msob.jima.core.ral.sql.commons.query.SqlQuery;
import ir.msob.jima.core.ral.sql.commons.query.SqlUpdate;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Base repository interface for R2DBC-backed relational domain objects.
 * Implementations should provide R2dbcEntityTemplate instance.
 */
public interface BaseSqlRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>>
        extends BaseRepository<ID, D> {

    R2dbcEntityTemplate getR2dbcEntityTemplate();

    @MethodStats
    default Mono<D> findOne(SqlQuery sqlQuery) {
        Query q = sqlQuery.getQuery();
        return getR2dbcEntityTemplate().select(getDomainClass()).matching(q).one();
    }

    @SneakyThrows
    @MethodStats
    default Mono<D> findById(ID id) {
        return getR2dbcEntityTemplate().select(getDomainClass())
                .matching(Query.query(Criteria.where(getDomainClass().getConstructor().newInstance().getIdName()).is(id))).one();
    }

    @MethodStats
    default Mono<D> findOneAndModify(SqlQuery sqlQuery, SqlUpdate update) {
        // Default: try updateFirst (which uses R2dbcEntityTemplate.update for simple sets) then re-fetch.
        return updateFirst(sqlQuery, update)
                .flatMap(updated -> updated ? findOne(sqlQuery) : Mono.empty());
    }

    @MethodStats
    default Flux<D> find(SqlQuery sqlQuery) {
        return getR2dbcEntityTemplate().select(getDomainClass()).matching(sqlQuery.getQuery()).all();
    }

    @MethodStats
    default Mono<Page<D>> findPage(SqlQuery sqlQuery) {
        Query q = sqlQuery.getQuery();
        return getR2dbcEntityTemplate().count(q, getDomainClass())
                .flatMap(count -> {
                    if (count == null || count < 1L) {
                        return Mono.just(new PageImpl<>(new ArrayList<>(), sqlQuery.getPageable(), 0L));
                    } else {
                        return getR2dbcEntityTemplate().select(getDomainClass()).matching(q).all().collectList()
                                .map(list -> new PageImpl<>(list, sqlQuery.getPageable(), count));
                    }
                });
    }

    @MethodStats
    default Mono<Boolean> exists(SqlQuery sqlQuery) {
        return getR2dbcEntityTemplate().count(sqlQuery.getQuery(), getDomainClass()).map(c -> c != null && c > 0);
    }

    @MethodStats
    default Mono<Boolean> existsNot(SqlQuery sqlQuery) {
        return exists(sqlQuery).map(b -> !b);
    }

    /**
     * Default updateFirst: converts simple set/unset to Update and calls template.update.
     * NOTE: If SqlUpdate contains increments or rawExpressions you MUST override this method
     * in concrete repository to execute DB-specific SQL.
     */
    @MethodStats
    default Mono<Boolean> updateFirst(SqlQuery sqlQuery, SqlUpdate sqlUpdate) {
        if (sqlUpdate == null) return Mono.just(false);
        if (sqlUpdate.hasIncrements() || sqlUpdate.hasRawExpressions()) {
            return Mono.error(new UnsupportedOperationException("SqlUpdate contains increments/raw expressions. Override updateFirst in concrete repository to apply DB-specific SQL."));
        }
        Query q = sqlQuery.getQuery();
        var update = sqlUpdate.toUpdate();
        return getR2dbcEntityTemplate().update(q, update, getDomainClass())
                .map(count -> count != null && count > 0);
    }

    /**
     * Default upsert: try updateFirst, if nothing updated then insert.
     * This is a generic approach and might need customization on PK/unique keys.
     */
    @MethodStats
    default Mono<Boolean> upsert(SqlQuery sqlQuery, SqlUpdate sqlUpdate) {
        return updateFirst(sqlQuery, sqlUpdate)
                .flatMap(updated -> {
                    if (updated) return Mono.just(true);
                    // fallback: cannot build entity from update easily; so upsert semantics are application-specific.
                    return Mono.error(new UnsupportedOperationException("Default upsert cannot construct entity from SqlUpdate. Override upsert in concrete repository."));
                });
    }

    @MethodStats
    default Mono<Boolean> updateMulti(SqlQuery sqlQuery, SqlUpdate sqlUpdate) {
        if (sqlUpdate == null) return Mono.just(false);
        if (sqlUpdate.hasIncrements() || sqlUpdate.hasRawExpressions()) {
            return Mono.error(new UnsupportedOperationException("SqlUpdate contains increments/raw expressions. Override updateMulti in concrete repository to apply DB-specific SQL."));
        }
        Query q = sqlQuery.getQuery();
        var update = sqlUpdate.toUpdate();
        return getR2dbcEntityTemplate().update(q, update, getDomainClass())
                .map(count -> count != null && count > 0);
    }

    @MethodStats
    default Mono<Boolean> delete(SqlQuery sqlQuery) {
        return getR2dbcEntityTemplate().delete(getDomainClass()).matching(sqlQuery.getQuery()).all()
                .map(count -> count != null && count > 0);
    }

    @MethodStats
    default Flux<D> findAndRemove(SqlQuery sqlQuery) {
        return Flux.error(new UnsupportedOperationException("findAndRemove not implemented by default. Override in concrete repository if you need to return removed entities."));
    }

    @MethodStats
    default Mono<D> findOneAndRemove(SqlQuery sqlQuery) {
        return Mono.error(new UnsupportedOperationException("findOneAndRemove not implemented by default. Override in concrete repository if needed."));
    }
}
