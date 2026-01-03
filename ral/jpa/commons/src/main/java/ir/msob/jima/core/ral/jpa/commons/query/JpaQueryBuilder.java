package ir.msob.jima.core.ral.jpa.commons.query;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.commons.repository.BaseQuery;
import ir.msob.jima.core.commons.repository.BaseQueryBuilder;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Converts a BaseCriteria (which contains Filter<?> fields) into a SqlQuery that holds a Specification.
 * This removes custom intermediate "Condition" objects and uses JPA Specification directly.
 * <p>
 * Assumptions: Filter class has the usual getters used previously: getEq(), getExists(), getGt(), getGte(),
 * getLt(), getLte(), getNe(), getRegex(), getIn(), getNin(), getOr().
 */
@Component
public class JpaQueryBuilder implements BaseQueryBuilder {

    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria) {
        return build(criteria, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria, Pageable pageable) {
        JpaQuery<Object> jpaQuery = new JpaQuery<>();
        if (criteria == null) return (Q) jpaQuery;

        Collection<Field> fields = getFields(criteria);
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == Filter.class) {
                try {
                    var filter = (Filter<?>) field.get(criteria);
                    if (filter != null) {
                        Specification<@NonNull Object> spec = toSpecification(field.getName(), filter);
                        if (spec != null) jpaQuery.where(spec);
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
                                .forEach(jpaQuery::include));
            } else {
                jpaQuery.include(criteria.getIncludesLimitation().stream().map(Object::toString).toList());
            }
        } else if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
            jpaQuery.include(criteria.getIncludes().stream().map(Object::toString).toList());
        }

        if (pageable != null) jpaQuery.add(pageable);

        return (Q) jpaQuery;
    }

    /**
     * Convert a single Filter for a field into a Specification.
     * Returns null if no content in filter.
     */
    private Specification<@NonNull Object> toSpecification(String fieldName, Filter<?> filter) {
        if (filter == null) return null;

        // If OR exists, we produce a specification that ORs sub-conditions.
        if (filter.getOr() != null) {
            var f = filter.getOr();
            List<Specification<@NonNull Object>> orSpecs = new ArrayList<>();
            if (f.getEq() != null) orSpecs.add(equalsSpec(fieldName, f.getEq()));
            if (f.getExists() != null) orSpecs.add(existsSpec(fieldName, f.getExists()));
            if (f.getGt() != null) orSpecs.add(gtSpec(fieldName, f.getGt()));
            if (f.getGte() != null) orSpecs.add(gteSpec(fieldName, f.getGte()));
            if (f.getLt() != null) orSpecs.add(ltSpec(fieldName, f.getLt()));
            if (f.getLte() != null) orSpecs.add(lteSpec(fieldName, f.getLte()));
            if (f.getNe() != null) orSpecs.add(neSpec(fieldName, f.getNe()));
            if (f.getRegex() != null) orSpecs.add(likeSpec(fieldName, f.getRegex()));
            if (f.getIn() != null) orSpecs.add(inSpec(fieldName, f.getIn()));
            if (f.getNin() != null) orSpecs.add(ninSpec(fieldName, f.getNin()));

            if (orSpecs.isEmpty()) return null;
            Specification<@NonNull Object> combined = orSpecs.getFirst();
            for (int i = 1; i < orSpecs.size(); i++) combined = combined.or(orSpecs.get(i));
            return combined;
        }

        // single-value filters (eq, gt, lt, ...)
        if (filter.getEq() != null) return equalsSpec(fieldName, filter.getEq());
        if (filter.getExists() != null) return existsSpec(fieldName, filter.getExists());
        if (filter.getGt() != null) return gtSpec(fieldName, filter.getGt());
        if (filter.getGte() != null) return gteSpec(fieldName, filter.getGte());
        if (filter.getLt() != null) return ltSpec(fieldName, filter.getLt());
        if (filter.getLte() != null) return lteSpec(fieldName, filter.getLte());
        if (filter.getNe() != null) return neSpec(fieldName, filter.getNe());
        if (filter.getRegex() != null) return likeSpec(fieldName, filter.getRegex());
        if (filter.getIn() != null) return inSpec(fieldName, filter.getIn());
        if (filter.getNin() != null) return ninSpec(fieldName, filter.getNin());

        return null;
    }

    /* ------------------- Specification helpers ------------------- */

    private Specification<@NonNull Object> equalsSpec(String field, Object value) {
        return (root, cq, cb) -> cb.equal(root.get(field), value);
    }

    private Specification<@NonNull Object> neSpec(String field, Object value) {
        return (root, cq, cb) -> cb.notEqual(root.get(field), value);
    }

    @SuppressWarnings("unchecked")
    private Specification<@NonNull Object> inSpec(String field, Object collection) {
        return (root, cq, cb) -> root.get(field).in((Collection<?>) collection);
    }

    @SuppressWarnings("unchecked")
    private Specification<@NonNull Object> ninSpec(String field, Object collection) {
        return (root, cq, cb) -> cb.not(root.get(field).in((Collection<?>) collection));
    }

    private Specification<@NonNull Object> likeSpec(String field, Object pattern) {
        return (root, cq, cb) -> cb.like(root.get(field), pattern.toString());
    }

    private Specification<@NonNull Object> existsSpec(String field, Object value) {
        return (root, cq, cb) -> {
            Boolean v = (Boolean) value;
            return v ? cb.isNotNull(root.get(field)) : cb.isNull(root.get(field));
        };
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Specification<@NonNull Object> gtSpec(String field, Object value) {
        return (root, cq, cb) -> cb.greaterThan(root.get(field), (Comparable) value);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Specification<@NonNull Object> gteSpec(String field, Object value) {
        return (root, cq, cb) -> cb.greaterThanOrEqualTo(root.get(field), (Comparable) value);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Specification<@NonNull Object> ltSpec(String field, Object value) {
        return (root, cq, cb) -> cb.lessThan(root.get(field), (Comparable) value);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Specification<@NonNull Object> lteSpec(String field, Object value) {
        return (root, cq, cb) -> cb.lessThanOrEqualTo(root.get(field), (Comparable) value);
    }

    /* ------------------- reflection helpers ------------------- */

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
