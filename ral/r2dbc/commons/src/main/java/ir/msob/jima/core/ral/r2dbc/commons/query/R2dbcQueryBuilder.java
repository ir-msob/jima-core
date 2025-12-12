package ir.msob.jima.core.ral.r2dbc.commons.query;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.commons.repository.BaseQuery;
import ir.msob.jima.core.commons.repository.BaseQueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Converts BaseCriteria (Filter fields) into SqlQuery (Criteria + Query).
 * This intentionally mirrors the JPA builder but produces Criteria for R2DBC.
 */
@Component
class R2dbcQueryBuilder implements BaseQueryBuilder {

    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria) {
        return build(criteria, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria, Pageable pageable) {
        R2dbcQuery<Object> r2dbcQuery = new R2dbcQuery<>();
        if (criteria == null) return (Q) r2dbcQuery;

        Collection<Field> fields = getFields(criteria);
        for (Field field : fields) {
            field.setAccessible(true);
            if (Filter.class.isAssignableFrom(field.getType())) {
                try {
                    var filter = (Filter<?>) field.get(criteria);
                    if (filter != null) {
                        Criteria c = toCriteria(field.getName(), filter);
                        if (c != null) r2dbcQuery.where(c);
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }

        // includes
        if (criteria.getIncludesLimitation() != null && !criteria.getIncludesLimitation().isEmpty()) {
            if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
                criteria.getIncludes().forEach(inc1 ->
                        criteria.getIncludesLimitation().stream()
                                .map(Object::toString).filter(inc1::equals)
                                .forEach(r2dbcQuery::include));
            } else {
                criteria.getIncludesLimitation().forEach(r2dbcQuery::include);
            }
        } else if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
            criteria.getIncludes().forEach(r2dbcQuery::include);
        }

        if (pageable != null) r2dbcQuery.add(pageable);

        return (Q) r2dbcQuery;
    }

    private Criteria toCriteria(String fieldName, Filter<?> filter) {
        if (filter == null) return null;

        // OR group
        if (filter.getOr() != null) {
            var f = filter.getOr();
            List<Criteria> orList = new ArrayList<>();
            if (f.getEq() != null) orList.add(equalsCriteria(fieldName, f.getEq()));
            if (f.getExists() != null) orList.add(existsCriteria(fieldName, f.getExists()));
            if (f.getGt() != null) orList.add(gtCriteria(fieldName, f.getGt()));
            if (f.getGte() != null) orList.add(gteCriteria(fieldName, f.getGte()));
            if (f.getLt() != null) orList.add(ltCriteria(fieldName, f.getLt()));
            if (f.getLte() != null) orList.add(lteCriteria(fieldName, f.getLte()));
            if (f.getNe() != null) orList.add(neCriteria(fieldName, f.getNe()));
            if (f.getRegex() != null) orList.add(likeCriteria(fieldName, f.getRegex()));
            if (f.getIn() != null) orList.add(inCriteria(fieldName, f.getIn()));
            if (f.getNin() != null) orList.add(ninCriteria(fieldName, f.getNin()));

            if (orList.isEmpty()) return null;
            Criteria combined = orList.getFirst();
            for (int i = 1; i < orList.size(); i++) combined = combined.or(orList.get(i));
            return combined;
        }

        if (filter.getEq() != null) return equalsCriteria(fieldName, filter.getEq());
        if (filter.getExists() != null) return existsCriteria(fieldName, filter.getExists());
        if (filter.getGt() != null) return gtCriteria(fieldName, filter.getGt());
        if (filter.getGte() != null) return gteCriteria(fieldName, filter.getGte());
        if (filter.getLt() != null) return ltCriteria(fieldName, filter.getLt());
        if (filter.getLte() != null) return lteCriteria(fieldName, filter.getLte());
        if (filter.getNe() != null) return neCriteria(fieldName, filter.getNe());
        if (filter.getRegex() != null) return likeCriteria(fieldName, filter.getRegex());
        if (filter.getIn() != null) return inCriteria(fieldName, filter.getIn());
        if (filter.getNin() != null) return ninCriteria(fieldName, filter.getNin());

        return null;
    }

    private Criteria equalsCriteria(String field, Object value) {
        return Criteria.where(field).is(value);
    }

    private Criteria neCriteria(String field, Object value) {
        return Criteria.where(field).not(value);
    }

    private Criteria inCriteria(String field, Object collection) {
        if (!(collection instanceof Collection<?>)) return Criteria.empty();
        return Criteria.where(field).in((Collection<?>) collection);
    }

    private Criteria ninCriteria(String field, Object collection) {
        if (!(collection instanceof Collection<?>)) return Criteria.empty();
        return Criteria.where(field).notIn((Collection<?>) collection);
    }

    private Criteria likeCriteria(String field, Object pattern) {
        // R2DBC Criteria supports matching via like in many drivers; this uses SQL LIKE semantics
        return Criteria.where(field).like(pattern.toString());
    }

    private Criteria existsCriteria(String field, Object value) {
        Boolean v = (Boolean) value;
        return v ? Criteria.where(field).isNotNull() : Criteria.where(field).isNull();
    }

    private Criteria gtCriteria(String field, Object value) {
        return Criteria.where(field).greaterThan(value);
    }

    private Criteria gteCriteria(String field, Object value) {
        return Criteria.where(field).greaterThanOrEquals(value);
    }

    private Criteria ltCriteria(String field, Object value) {
        return Criteria.where(field).lessThan(value);
    }

    private Criteria lteCriteria(String field, Object value) {
        return Criteria.where(field).lessThanOrEquals(value);
    }

    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> Collection<Field> getFields(C criteria) {
        List<Field> fields = new ArrayList<>();
        if (criteria != null) {
            Class<?> type = criteria.getClass();
            while (type != null && type != BaseCriteria.class) {
                fields.addAll(Arrays.asList(type.getDeclaredFields()));
                type = type.getSuperclass();
            }
        }
        return fields;
    }
}
