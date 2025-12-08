package ir.msob.jima.core.ral.sql.commons.query;

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
 * SqlQueryBuilder: translate BaseCriteria + Filter fields into SqlQuery (relational Criteria).
 */
@Component
public class SqlQueryBuilder implements BaseQueryBuilder {

    private static void prepareOrOperation(BaseQuery baseQuery, Collection<Criteria> orOperatorList) {
        if (orOperatorList != null && !orOperatorList.isEmpty() && baseQuery instanceof SqlQuery sqlQuery) {
            sqlQuery.orOperator(orOperatorList);
        }
    }

    private static void setOrConditions(Collection<Criteria> orOperatorList, Field field, Filter<?> fieldFilter) {
        if (fieldFilter.getOr() == null) return;
        var f = fieldFilter.getOr();
        if (f.getEq() != null) orOperatorList.add(Criteria.where(field.getName()).is(f.getEq()));
        else if (f.getExists() != null) orOperatorList.add(f.getExists() ?
                Criteria.where(field.getName()).isNotNull() : Criteria.where(field.getName()).isNull());
        else if (f.getGt() != null) orOperatorList.add(Criteria.where(field.getName()).greaterThan(f.getGt()));
        else if (f.getGte() != null)
            orOperatorList.add(Criteria.where(field.getName()).greaterThanOrEquals(f.getGte()));
        else if (f.getLt() != null) orOperatorList.add(Criteria.where(field.getName()).lessThan(f.getLt()));
        else if (f.getLte() != null) orOperatorList.add(Criteria.where(field.getName()).lessThanOrEquals(f.getLte()));
        else if (f.getNe() != null) orOperatorList.add(Criteria.where(field.getName()).not(f.getNe()));
        else if (f.getRegex() != null) orOperatorList.add(Criteria.where(field.getName()).like(f.getRegex()));
        else if (f.getIn() != null) orOperatorList.add(Criteria.where(field.getName()).in(f.getIn()));
        else if (f.getNin() != null) orOperatorList.add(Criteria.where(field.getName()).notIn(f.getNin()));
    }

    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria) {
        return build(criteria, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria, Pageable pageable) {
        SqlQuery sqlQuery = new SqlQuery();
        List<Criteria> orOperatorList = new ArrayList<>();

        Collection<Field> fields = getFields(criteria);
        setFieldsCondition(criteria, sqlQuery, orOperatorList, fields);
        prepareOrOperation(sqlQuery, orOperatorList);
        prepareIncludes(criteria, sqlQuery);
        preparePagination(pageable, sqlQuery);

        return (Q) sqlQuery;
    }

    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> void setFieldsCondition(
            C criteria, SqlQuery sqlQuery, Collection<Criteria> orOperatorList, Collection<Field> fields) {

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == Filter.class) {
                try {
                    Filter<?> fieldFilter = (Filter<?>) field.get(criteria);
                    if (fieldFilter != null) {
                        setConditions(sqlQuery, orOperatorList, field, fieldFilter);
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }
    }

    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> void prepareIncludes(C criteria, SqlQuery sqlQuery) {
        if (criteria == null) return;
        if (criteria.getIncludesLimitation() != null && !criteria.getIncludesLimitation().isEmpty()) {
            if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
                criteria.getIncludes().forEach(inc1 -> criteria.getIncludesLimitation()
                        .stream().map(Object::toString).filter(inc1::equals).forEach(sqlQuery::include));
            } else {
                sqlQuery.include(criteria.getIncludesLimitation().stream().map(Object::toString).toList());
            }
        } else if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
            sqlQuery.include(criteria.getIncludes().stream().map(Object::toString).toList());
        }
    }

    private void preparePagination(Pageable pageable, SqlQuery sqlQuery) {
        if (pageable != null) sqlQuery.add(pageable);
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

    private void setConditions(SqlQuery sqlQuery, Collection<Criteria> orOperatorList, Field field, Filter<?> fieldFilter) {
        if (fieldFilter.getEq() != null) sqlQuery.is(field.getName(), fieldFilter.getEq());
        else if (fieldFilter.getExists() != null) sqlQuery.exists(field.getName(), fieldFilter.getExists());
        else if (fieldFilter.getGt() != null) sqlQuery.gt(field.getName(), fieldFilter.getGt());
        else if (fieldFilter.getGte() != null) sqlQuery.gte(field.getName(), fieldFilter.getGte());
        else if (fieldFilter.getLt() != null) sqlQuery.lt(field.getName(), fieldFilter.getLt());
        else if (fieldFilter.getLte() != null) sqlQuery.lte(field.getName(), fieldFilter.getLte());
        else if (fieldFilter.getNe() != null) sqlQuery.ne(field.getName(), fieldFilter.getNe());
        else if (fieldFilter.getRegex() != null) sqlQuery.regex(field.getName(), fieldFilter.getRegex());
        else if (fieldFilter.getIn() != null) sqlQuery.in(field.getName(), fieldFilter.getIn());
        else if (fieldFilter.getNin() != null) sqlQuery.nin(field.getName(), fieldFilter.getNin());
        else if (fieldFilter.getOr() != null) setOrConditions(orOperatorList, field, fieldFilter);
    }
}
