package ir.msob.jima.core.ral.jpa.commons.query;

import ir.msob.jima.core.commons.repository.BaseUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;

import java.util.*;

/**
 * SqlUpdate:
 * - supports JPQL update string + named params (pure JPA)
 * - supports a list of "applier" operations that will be executed at runtime to build CriteriaUpdate (also pure JPA)
 * <p>
 * We avoid storing CriteriaUpdate instances; instead we store small lambdas (UpdateApplier) that are executed
 * inside Method that has CriteriaBuilder/EntityManager.
 */

@Getter
public class JpaUpdate<T> implements BaseUpdate {

    private final Map<String, Object> jpqlParams = new LinkedHashMap<>();
    // CriteriaUpdate-based path: multiple appliers that will be executed in the repository method
    private final List<UpdateApplier<T>> appliers = new ArrayList<>();
    // JPQL-based update (string + params) — pure JPA path
    private String jpqlUpdate;

    public JpaUpdate() {
    }

    /* JPQL helpers */
    public JpaUpdate<T> jpql(String jpql) {
        this.jpqlUpdate = jpql;
        return this;
    }

    public JpaUpdate<T> param(String name, Object value) {
        if (name != null) this.jpqlParams.put(name, value);
        return this;
    }

    public boolean hasJpql() {
        return jpqlUpdate != null && !jpqlUpdate.isBlank();
    }

    /* CriteriaUpdate applier helpers */
    public JpaUpdate<T> withApplier(UpdateApplier<T> applier) {
        if (applier != null) this.appliers.add(applier);
        return this;
    }

    /**
     * Convenience: set a field to a constant value via CriteriaUpdate when appliers are executed.
     */
    public JpaUpdate<T> set(String field, Object value) {
        if (field == null) return this;
        this.appliers.add((cb, update, root) -> update.set(root.get(field), value));
        return this;
    }

    /**
     * Convenience: unset (set to null)
     */
    public JpaUpdate<T> unset(String field) {
        if (field == null) return this;
        this.appliers.add((cb, update, root) -> update.set(root.get(field), (Object) null));
        return this;
    }

    /**
     * Convenience: increment numeric column by a given amount
     */
    public JpaUpdate<T> inc(String field, Number amount) {
        if (field == null || amount == null) return this;
        this.appliers.add((cb, update, root) -> update.set(root.<Number>get(field), cb.sum(root.get(field), amount)));
        return this;
    }

    @Override
    public BaseUpdate pull(String field, Object value) {
        return null;
    }

    @Override
    public BaseUpdate pullAll(String field, Collection<?> valueList) {
        return null;
    }

    @Override
    public BaseUpdate push(String field, Object value) {
        return null;
    }

    @Override
    public BaseUpdate push(String field, Object value, Integer slice) {
        return null;
    }

    @Override
    public BaseUpdate pushAll(String field, Collection<?> values) {
        return null;
    }

    @Override
    public BaseUpdate pushAll(String field, Collection<?> values, Integer slice) {
        return null;
    }

    @Override
    public BaseUpdate addToSet(String field, Object value) {
        return null;
    }

    @Override
    public BaseUpdate addToSetAll(String field, Collection<?> values) {
        return null;
    }

    /**
     * Add a low-level raw expression using CriteriaBuilder expressions — caller provides the applier.
     */
    public JpaUpdate<T> addRawApplier(UpdateApplier<T> applier) {
        return withApplier(applier);
    }

    public boolean hasAppliers() {
        return !appliers.isEmpty();
    }

    @FunctionalInterface
    public interface UpdateApplier<T> {
        void apply(CriteriaBuilder cb, CriteriaUpdate<T> update, Root<T> root);
    }
}
