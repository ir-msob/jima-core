package ir.msob.jima.core.ral.jpa.commons;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.ral.jpa.commons.query.JpaQuery;
import ir.msob.jima.core.ral.jpa.commons.query.JpaUpdate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Base repository interface for JPA-backed domain objects.
 * All blocking JPA calls are wrapped in boundedElastic scheduler.
 */
public interface BaseJpaRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>, C extends BaseCriteria<ID>>
        extends BaseRepository<ID, D, C> {

    EntityManager getEntityManager();

    @Transactional(readOnly = true)
    @MethodStats
    default Mono<@NonNull D> findOne(JpaQuery<D> jpaQuery) {
        return Mono.fromCallable(() -> {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<D> cq = cb.createQuery(getDomainClass());
            Root<D> root = cq.from(getDomainClass());

            Predicate predicate = buildPredicateFromSpecification(cb, root, cq, jpaQuery);
            if (predicate != null) cq.where(predicate);

            applySort(cb, cq, root, jpaQuery);

            TypedQuery<D> typed = getEntityManager().createQuery(cq);
            applyPagination(typed, jpaQuery);
            typed.setMaxResults(1);
            List<D> res = typed.getResultList();
            return res.isEmpty() ? null : res.getFirst();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional(readOnly = true)
    @MethodStats
    default Mono<@NonNull D> findById(ID id) {
        return Mono.fromCallable(() -> getEntityManager().find(getDomainClass(), id))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional
    @MethodStats
    default Mono<@NonNull D> findOneAndModify(JpaQuery<D> jpaQuery, JpaUpdate<D> update) {
        return updateFirst(jpaQuery, update)
                .flatMap(updated -> updated ? findOne(jpaQuery) : Mono.empty());
    }

    @Transactional(readOnly = true)
    @MethodStats
    default Flux<D> find(JpaQuery<D> jpaQuery) {
        return Mono.fromCallable(() -> {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<D> cq = cb.createQuery(getDomainClass());
            Root<D> root = cq.from(getDomainClass());

            Predicate predicate = buildPredicateFromSpecification(cb, root, cq, jpaQuery);
            if (predicate != null) cq.where(predicate);

            applySort(cb, cq, root, jpaQuery);

            TypedQuery<D> typed = getEntityManager().createQuery(cq);
            applyPagination(typed, jpaQuery);
            if (jpaQuery != null && jpaQuery.getLimit() != null) typed.setMaxResults(jpaQuery.getLimit());
            return typed.getResultList();
        }).flatMapMany(Flux::fromIterable).subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional(readOnly = true)
    @MethodStats
    default Mono<@NonNull Page<@NonNull D>> findPage(JpaQuery<D> jpaQuery) {
        return Mono.fromCallable(() -> {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

            // count query
            CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
            Root<D> countRoot = countCq.from(getDomainClass());
            Predicate predicate = buildPredicateFromSpecification(cb, countRoot, countCq, jpaQuery);
            if (predicate != null) countCq.where(predicate);
            countCq.select(cb.count(countRoot));
            Long count = getEntityManager().createQuery(countCq).getSingleResult();
            if (count == null || count < 1L) {
                return new PageImpl<@NonNull D>(new ArrayList<>(), jpaQuery.getPageable(), 0L);
            }

            // data query
            CriteriaQuery<D> dataCq = cb.createQuery(getDomainClass());
            Root<D> dataRoot = dataCq.from(getDomainClass());
            Predicate dataPredicate = buildPredicateFromSpecification(cb, dataRoot, dataCq, jpaQuery);
            if (dataPredicate != null) dataCq.where(dataPredicate);
            applySort(cb, dataCq, dataRoot, jpaQuery);
            TypedQuery<D> typed = getEntityManager().createQuery(dataCq);
            applyPagination(typed, jpaQuery);

            List<D> list = typed.getResultList();
            return (Page<@NonNull D>) new PageImpl<>(list, jpaQuery.getPageable(), count);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional(readOnly = true)
    @MethodStats
    default Mono<@NonNull Boolean> exists(JpaQuery<D> jpaQuery) {
        return Mono.fromCallable(() -> {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
            Root<D> root = countCq.from(getDomainClass());
            Predicate predicate = buildPredicateFromSpecification(cb, root, countCq, jpaQuery);
            if (predicate != null) countCq.where(predicate);
            countCq.select(cb.count(root));
            Long c = getEntityManager().createQuery(countCq).getSingleResult();
            return c != null && c > 0;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional(readOnly = true)
    @MethodStats
    default Mono<@NonNull Boolean> existsNot(JpaQuery<D> jpaQuery) {
        return exists(jpaQuery).map(b -> !b);
    }

    @Transactional
    @MethodStats
    default Mono<@NonNull Boolean> updateFirst(JpaQuery<D> jpaQuery, JpaUpdate<D> jpaUpdate) {
        return Mono.fromCallable(() -> {
            if (jpaUpdate == null) return false;

            // JPQL path
            if (jpaUpdate.hasJpql()) {
                var q = getEntityManager().createQuery(jpaUpdate.getJpqlUpdate());
                for (Map.Entry<String, Object> e : jpaUpdate.getJpqlParams().entrySet()) {
                    q.setParameter(e.getKey(), e.getValue());
                }
                int updated = q.executeUpdate();
                return updated > 0;
            }

            // CriteriaUpdate path via appliers
            if (jpaUpdate.hasAppliers()) {
                CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
                CriteriaUpdate<D> update = cb.createCriteriaUpdate(getDomainClass());
                Root<D> root = update.from(getDomainClass());

                // apply appliers (each modifies the CriteriaUpdate)
                for (var applier : jpaUpdate.getAppliers()) {
                    applier.apply(cb, update, root);
                }

                Predicate predicate = buildPredicateFromSpecification(cb, root, cb.createQuery(getDomainClass()), jpaQuery);
                if (predicate != null) update.where(predicate);

                int updated = getEntityManager().createQuery(update).executeUpdate();
                return updated > 0;
            }

            throw new UnsupportedOperationException("SqlUpdate must contain either JPQL or appliers for CriteriaUpdate");
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional
    @MethodStats
    default Mono<@NonNull Boolean> upsert(JpaQuery<D> jpaQuery, JpaUpdate<D> jpaUpdate) {
        return updateFirst(jpaQuery, jpaUpdate)
                .flatMap(updated -> {
                    if (updated) return Mono.just(true);
                    return Mono.error(new UnsupportedOperationException("Default upsert cannot construct entity from SqlUpdate. Override upsert in concrete repository."));
                });
    }

    @Transactional
    @MethodStats
    default Mono<@NonNull Boolean> updateMulti(JpaQuery<D> jpaQuery, JpaUpdate<D> jpaUpdate) {
        // CriteriaUpdate updates all matching rows by default (same as updateFirst here)
        return updateFirst(jpaQuery, jpaUpdate);
    }

    @Transactional
    @MethodStats
    default Mono<@NonNull Boolean> delete(JpaQuery<D> jpaQuery) {
        return Mono.fromCallable(() -> {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaDelete<D> delete = cb.createCriteriaDelete(getDomainClass());
            Root<D> root = delete.from(getDomainClass());
            Predicate predicate = buildPredicateFromSpecification(cb, root, cb.createQuery(getDomainClass()), jpaQuery);
            if (predicate != null) delete.where(predicate);
            int deleted = getEntityManager().createQuery(delete).executeUpdate();
            return deleted > 0;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional
    @MethodStats
    default Flux<@NonNull D> findAndRemove(JpaQuery<D> jpaQuery) {
        return Flux.error(new UnsupportedOperationException("findAndRemove not implemented by default. Override in concrete repository if you need to return removed entities."));
    }

    @Transactional
    @MethodStats
    default Mono<@NonNull D> findOneAndRemove(JpaQuery<D> jpaQuery) {
        return Mono.error(new UnsupportedOperationException("findOneAndRemove not implemented by default. Override in concrete repository if needed."));
    }

    /* ------------------ helper methods ------------------ */

    default <T> Predicate buildPredicateFromSpecification(CriteriaBuilder cb, Root<T> root, CriteriaQuery<?> cq, JpaQuery<T> jpaQuery) {
        if (jpaQuery == null) return null;
        Specification<@NonNull T> spec = jpaQuery.getSpecification();
        if (spec == null) return null;
        return spec.toPredicate(root, cq, cb);
    }

    default <T> void applySort(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> root, JpaQuery<T> jpaQuery) {
        if (jpaQuery == null) return;
        if (jpaQuery.getSort() != null) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order o : jpaQuery.getSort()) {
                orders.add(o.isAscending() ? cb.asc(root.get(o.getProperty())) : cb.desc(root.get(o.getProperty())));
            }
            if (!orders.isEmpty()) cq.orderBy(orders);
        }
    }

    default void applyPagination(TypedQuery<?> typed, JpaQuery<?> jpaQuery) {
        if (jpaQuery == null) return;
        if (jpaQuery.getPageable() != null) {
            var p = jpaQuery.getPageable();
            if (p.getOffset() >= 0) typed.setFirstResult((int) p.getOffset());
            if (p.getPageSize() > 0) typed.setMaxResults(p.getPageSize());
        } else if (jpaQuery.getLimit() != null) {
            typed.setMaxResults(jpaQuery.getLimit());
        }
    }
}
