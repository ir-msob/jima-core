package ir.msob.jima.core.ral.hr.commons.query;

import ir.msob.jima.core.commons.repository.BaseUpdate;
import org.springframework.data.relational.core.query.Update;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SqlUpdate: small wrapper around org.springframework.data.relational.core.query.Update
 * It offers convenience helpers like set/unset/inc. Collection-array style helpers (push/pull) are
 * not supported for relational databases and intentionally throw UnsupportedOperationException.
 */
public class R2dbcUpdate<T> implements BaseUpdate {

    private final Update update = Update.from(Collections.emptyMap());
    private final Map<String, Object> sqlParams = new LinkedHashMap<>();
    private String customSql = null; // optional raw SQL path (if you want to execute raw SQL)

    public R2dbcUpdate() {
    }

    public R2dbcUpdate<T> set(String field, Object value) {
        if (field == null) return this;
        update.set(field, value);
        return this;
    }

    public R2dbcUpdate<T> unset(String field) {
        if (field == null) return this;
        update.set(field, null);
        return this;
    }

    public R2dbcUpdate<T> inc(String field, Number amount) {
        // R2DBC Update does not have arithmetic helpers — implement using raw SQL path or use db-specific expression
        // We'll store a hint that repository implementation can interpret and build SQL/Expression.
        throw new UnsupportedOperationException("inc is database-specific in R2DBC; use custom SQL or implement in repository");
    }

    // Raw SQL helpers (optional)
    public R2dbcUpdate<T> sql(String sql) {
        this.customSql = sql;
        return this;
    }

    public R2dbcUpdate<T> param(String name, Object value) {
        if (name != null) this.sqlParams.put(name, value);
        return this;
    }

    public Update getUpdate() {
        return update;
    }

    public boolean hasRawSql() {
        return customSql != null && !customSql.isBlank();
    }

    public String getRawSql() {
        return customSql;
    }

    public Map<String, Object> getSqlParams() {
        return sqlParams;
    }

    // unsupported array-style operations for relational DBs
    @Override
    public R2dbcUpdate<T> pull(String field, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R2dbcUpdate<T> pullAll(String field, Collection<?> valueList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R2dbcUpdate<T> push(String field, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R2dbcUpdate<T> push(String field, Object value, Integer slice) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R2dbcUpdate<T> pushAll(String field, Collection<?> values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R2dbcUpdate<T> pushAll(String field, Collection<?> values, Integer slice) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R2dbcUpdate<T> addToSet(String field, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R2dbcUpdate<T> addToSetAll(String field, Collection<?> values) {
        throw new UnsupportedOperationException();
    }
}
