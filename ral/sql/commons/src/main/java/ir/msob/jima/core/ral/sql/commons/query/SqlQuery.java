package ir.msob.jima.core.ral.sql.commons.query;

import ir.msob.jima.core.commons.repository.BaseQuery;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;

import java.util.*;

/**
 * SqlQuery — wrapper for relational Criteria and other SQL-ish options.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlQuery implements BaseQuery {
    private final List<Criteria> criteriaList = new ArrayList<>();
    private final Set<String> includes = new LinkedHashSet<>();
    private final Set<String> excludes = new LinkedHashSet<>();
    private Pageable pageable;
    private Sort sort;
    private Integer limit;

    public Query getQuery() {
        Query query;
        if (criteriaList.isEmpty()) {
            query = Query.empty();
        } else {
            Criteria combined = criteriaList.get(0);
            for (int i = 1; i < criteriaList.size(); i++) {
                combined = combined.and(criteriaList.get(i));
            }
            query = Query.query(combined);
        }

        if (pageable != null) query = query.with(pageable);
        else if (limit != null) query = query.limit(limit);

        if (sort != null) query = query.sort(sort);

        return query;
    }

    public SqlQuery with(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public SqlQuery add(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public SqlQuery withSort(String field, Sort.Direction direction) {
        this.sort = Sort.by(direction, field);
        return this;
    }

    public SqlQuery withSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public SqlQuery limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /* SQL-style criteria helpers */
    public SqlQuery is(String field, Object value) {
        this.criteriaList.add(Criteria.where(field).is(value));
        return this;
    }

    public SqlQuery gt(String field, Object value) {
        this.criteriaList.add(Criteria.where(field).greaterThan(value));
        return this;
    }

    public SqlQuery gte(String field, Object value) {
        this.criteriaList.add(Criteria.where(field).greaterThanOrEquals(value));
        return this;
    }

    public SqlQuery lt(String field, Object value) {
        this.criteriaList.add(Criteria.where(field).lessThan(value));
        return this;
    }

    public SqlQuery lte(String field, Object value) {
        this.criteriaList.add(Criteria.where(field).lessThanOrEquals(value));
        return this;
    }

    public SqlQuery ne(String field, Object value) {
        this.criteriaList.add(Criteria.where(field).not(value));
        return this;
    }

    public SqlQuery exists(String field, Boolean value) {
        if (Boolean.TRUE.equals(value)) this.criteriaList.add(Criteria.where(field).isNotNull());
        else this.criteriaList.add(Criteria.where(field).isNull());
        return this;
    }

    public SqlQuery in(String field, Collection<?> values) {
        this.criteriaList.add(Criteria.where(field).in(values));
        return this;
    }

    public SqlQuery nin(String field, Collection<?> values) {
        this.criteriaList.add(Criteria.where(field).notIn(values));
        return this;
    }

    /**
     * For relational DBs regex is typically LIKE; caller should provide pattern (e.g. "%foo%").
     */
    public SqlQuery regex(String field, Object value) {
        this.criteriaList.add(Criteria.where(field).like(value.toString()));
        return this;
    }

    public SqlQuery include(Collection<String> fieldList) {
        this.includes.addAll(fieldList);
        return this;
    }

    public SqlQuery include(String field) {
        this.includes.add(field);
        return this;
    }

    public SqlQuery exclude(String... fields) {
        Collections.addAll(this.excludes, fields);
        return this;
    }

    public SqlQuery orOperator(Collection<Criteria> orList) {
        if (orList == null || orList.isEmpty()) return this;
        Iterator<Criteria> it = orList.iterator();
        Criteria orCombined = it.next();
        while (it.hasNext()) {
            orCombined = orCombined.or(it.next());
        }
        this.criteriaList.add(orCombined);
        return this;
    }
}
