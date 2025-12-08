package ir.msob.jima.core.ral.sql.commons.query;

import ir.msob.jima.core.commons.repository.BaseUpdate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.query.Update;

import java.util.*;

/**
 * SqlUpdate: operations that make sense for relational DBs.
 * <p>
 * - set(field, value): SET column = value
 * - unset(field): SET column = NULL
 * - inc(field, amount): a logical representation of increment; note: org.springframework Update
 * does NOT have arithmetic expressions; to apply inc you should override repository method
 * and execute DB-specific SQL (e.g., "col = col + :inc") or use DatabaseClient.
 * <p>
 * This class can be converted to Spring's Update for the simple cases (set / null).
 * For advanced operations (inc / raw expressions) repository must override and implement DB-specific SQL.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
public class SqlUpdate implements BaseUpdate {
    // plain sets
    private final Map<String, Object> set = new LinkedHashMap<>();
    // fields to set to null
    private final Set<String> unset = new LinkedHashSet<>();
    // increments (logical; repository should handle)
    private final Map<String, Number> inc = new LinkedHashMap<>();
    // raw expressions (DB-specific) — key: column, value: expression (e.g. "col = col + 1")
    private final Map<String, String> rawExpressions = new LinkedHashMap<>();

    public SqlUpdate set(String field, Object value) {
        this.set.put(field, value);
        return this;
    }

    /**
     * Mark a field to be set to NULL.
     */
    public SqlUpdate unset(String field) {
        this.unset.add(field);
        return this;
    }

    /**
     * Logical increment. NOTE: default conversion to Update is not possible for inc
     * (Update doesn't support arithmetic expression directly). Please override repository
     * methods (updateFirst/updateMulti) to apply increments with DB-specific SQL.
     */
    public SqlUpdate inc(String field, Number amount) {
        this.inc.put(field, amount);
        return this;
    }

    @Override
    public BaseUpdate pull(String field, Object value) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public BaseUpdate pullAll(String field, Collection<?> valueList) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public BaseUpdate push(String field, Object value) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public BaseUpdate push(String field, Object value, Integer slice) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public BaseUpdate pushAll(String field, Collection<?> values) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public BaseUpdate pushAll(String field, Collection<?> values, Integer slice) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public BaseUpdate addToSet(String field, Object value) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public BaseUpdate addToSetAll(String field, Collection<?> values) {
        throw new RuntimeException("not implemented");
    }

    /**
     * Add a raw DB expression to be applied in SET part. Repository must safely inject parameters.
     * Example usage: addRaw("counter", "counter = counter + 1")
     */
    public SqlUpdate addRawExpression(String column, String expression) {
        this.rawExpressions.put(column, expression);
        return this;
    }

    /**
     * Convert simple set/unset to Spring Data Update object.
     * WARNING: inc and rawExpressions are NOT converted here and must be handled by repository.
     */
    public Update toUpdate() {
        Update update = new SqlUpdateBuilder().build().toUpdate();
        for (Map.Entry<String, Object> e : set.entrySet()) {
            update = update.set(e.getKey(), e.getValue());
        }
        for (String f : unset) {
            update = update.set(f, null);
        }
        // inc/rawExpressions intentionally NOT applied here.
        return update;
    }

    public boolean hasIncrements() {
        return !inc.isEmpty();
    }

    public boolean hasRawExpressions() {
        return !rawExpressions.isEmpty();
    }
}
