package ir.msob.jima.core.ral.hr.commons.query;

import ir.msob.jima.core.commons.repository.BaseQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * SqlQuery: lightweight holder for a Spring Data R2DBC Criteria + pagination/sort/include/exclude info.
 */
@Getter
public class R2dbcQuery<T> implements BaseQuery {

    private final Set<String> includes = new LinkedHashSet<>();
    private final Set<String> excludes = new LinkedHashSet<>();
    private Criteria criteria;
    private Pageable pageable;
    private Sort sort;
    private Integer limit;

    public R2dbcQuery() {
    }

    public R2dbcQuery<T> where(Criteria c) {
        if (c == null) return this;
        if (this.criteria == null) this.criteria = c;
        else this.criteria = this.criteria.and(c);
        return this;
    }

    public R2dbcQuery<T> with(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public R2dbcQuery<T> add(Pageable pageable) {
        return with(pageable);
    }

    public R2dbcQuery<T> withSort(String field, Sort.Direction direction) {
        this.sort = Sort.by(direction, field);
        return this;
    }

    public R2dbcQuery<T> withSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public R2dbcQuery<T> limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public R2dbcQuery<T> include(Collection<String> fields) {
        if (fields != null) this.includes.addAll(fields);
        return this;
    }

    public R2dbcQuery<T> include(String field) {
        if (field != null) this.includes.add(field);
        return this;
    }

    public R2dbcQuery<T> exclude(String... fields) {
        if (fields != null) {
            Collections.addAll(this.excludes, fields);
        }
        return this;
    }

    /**
     * Build a Spring Data Query from the held Criteria/sort/page/limit.
     */
    public Query toQuery() {
        Query q = (criteria != null) ? Query.query(criteria) : Query.empty();
        if (sort != null) q = q.sort(sort);
        if (pageable != null) {
            if (pageable.getOffset() >= 0) q = q.offset(pageable.getOffset());
            if (pageable.getPageSize() > 0) q = q.limit(pageable.getPageSize());
        } else if (limit != null) {
            q = q.limit(limit);
        }
        return q;
    }
}
