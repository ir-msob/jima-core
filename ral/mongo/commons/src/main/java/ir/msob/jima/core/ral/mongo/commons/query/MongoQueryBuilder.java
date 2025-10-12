package ir.msob.jima.core.ral.mongo.commons.query;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.commons.repository.BaseQuery;
import ir.msob.jima.core.commons.repository.BaseQueryBuilder;
import ir.msob.jima.core.ral.mongo.commons.criteria.MongoCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * {@code MongoQueryBuilder} is a concrete implementation of {@link BaseQueryBuilder}
 * that builds MongoDB-specific query objects based on {@link BaseCriteria}.
 * <p>
 * It dynamically constructs {@link MongoQuery} instances by reflecting over the provided
 * criteria fields and translating {@link Filter} definitions into {@link Criteria}
 * compatible with MongoDB query semantics.
 * <p>
 * Supports:
 * <ul>
 *   <li>AND / OR logical operations</li>
 *   <li>Field-level comparison operators (e.g., eq, gt, lt, regex)</li>
 *   <li>Include field projection handling</li>
 *   <li>Pagination via {@link Pageable}</li>
 * </ul>
 */
@Component
public class MongoQueryBuilder implements BaseQueryBuilder {

    /**
     * Combines multiple {@link Criteria} with an OR operator into the given {@link MongoQuery}.
     *
     * @param baseQuery      the target query object
     * @param orOperatorList the list of OR conditions to combine
     */
    private static void prepareOrOperation(BaseQuery baseQuery, Collection<Criteria> orOperatorList) {
        if (orOperatorList != null && !orOperatorList.isEmpty() && baseQuery instanceof MongoQuery mongoQuery) {
            mongoQuery.orOperator(orOperatorList);
        }
    }

    /**
     * Adds OR-based conditions for a given field, using the specified {@link Filter}.
     *
     * @param orOperatorList collection of OR conditions
     * @param field          the field to apply filters on
     * @param fieldFilter    the filter defining the OR conditions
     */
    private static void setOrConditions(Collection<Criteria> orOperatorList, Field field, Filter<?> fieldFilter) {
        if (fieldFilter.getOr() == null) return;

        if (fieldFilter.getOr().getEq() != null)
            orOperatorList.add(MongoCriteria.is(field.getName(), fieldFilter.getOr().getEq()));
        else if (fieldFilter.getOr().getExists() != null)
            orOperatorList.add(MongoCriteria.exists(field.getName(), fieldFilter.getOr().getExists()));
        else if (fieldFilter.getOr().getGt() != null)
            orOperatorList.add(MongoCriteria.gt(field.getName(), fieldFilter.getOr().getGt()));
        else if (fieldFilter.getOr().getGte() != null)
            orOperatorList.add(MongoCriteria.gte(field.getName(), fieldFilter.getOr().getGte()));
        else if (fieldFilter.getOr().getLt() != null)
            orOperatorList.add(MongoCriteria.lt(field.getName(), fieldFilter.getOr().getLt()));
        else if (fieldFilter.getOr().getLte() != null)
            orOperatorList.add(MongoCriteria.lte(field.getName(), fieldFilter.getOr().getLte()));
        else if (fieldFilter.getOr().getNe() != null)
            orOperatorList.add(MongoCriteria.ne(field.getName(), fieldFilter.getOr().getNe()));
        else if (fieldFilter.getOr().getRegex() != null)
            orOperatorList.add(MongoCriteria.regex(field.getName(), fieldFilter.getOr().getRegex()));
        else if (fieldFilter.getOr().getIn() != null)
            orOperatorList.add(MongoCriteria.in(field.getName(), fieldFilter.getOr().getIn()));
        else if (fieldFilter.getOr().getNin() != null)
            orOperatorList.add(MongoCriteria.nin(field.getName(), fieldFilter.getOr().getNin()));
    }

    /**
     * Builds a {@link MongoQuery} based on the given criteria.
     *
     * @param criteria the filtering criteria
     * @return a MongoDB query built from the provided criteria
     */
    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria) {
        return build(criteria, null);
    }

    /**
     * Builds a {@link MongoQuery} based on the given criteria and pagination information.
     *
     * @param criteria the filtering criteria
     * @param pageable pagination and sorting information
     * @return a MongoDB query built from the provided criteria and pageable configuration
     */
    @Override
    @SuppressWarnings("unchecked")
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria, Pageable pageable) {
        MongoQuery mongoQuery = new MongoQuery();
        List<Criteria> orOperatorList = new ArrayList<>();

        Collection<Field> fields = getFields(criteria);
        setFieldsCondition(criteria, mongoQuery, orOperatorList, fields);
        prepareOrOperation(mongoQuery, orOperatorList);
        prepareIncludes(criteria, mongoQuery);
        preparePagination(pageable, mongoQuery);

        return (Q) mongoQuery;
    }

    /**
     * Iterates over all {@link Filter} fields within the provided criteria and applies
     * corresponding MongoDB conditions to the query.
     */
    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> void setFieldsCondition(
            C criteria, MongoQuery mongoQuery, Collection<Criteria> orOperatorList, Collection<Field> fields) {

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == Filter.class) {
                try {
                    Filter<?> fieldFilter = (Filter<?>) field.get(criteria);
                    if (fieldFilter != null) {
                        setConditions(mongoQuery, orOperatorList, field, fieldFilter);
                    }
                } catch (IllegalAccessException ignored) {
                    // Skip inaccessible fields
                }
            }
        }
    }

    /**
     * Configures which fields should be included in the MongoDB query projection.
     */
    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> void prepareIncludes(C criteria, MongoQuery mongoQuery) {
        if (criteria == null)
            return;

        if (criteria.getIncludesLimitation() != null && !criteria.getIncludesLimitation().isEmpty()) {
            if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
                criteria.getIncludes().forEach(inc1 -> criteria.getIncludesLimitation()
                        .stream()
                        .map(Object::toString)
                        .filter(inc1::equals)
                        .forEach(mongoQuery::include));
            } else {
                mongoQuery.include(criteria.getIncludesLimitation().stream().map(Object::toString).toList());
            }
        } else if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
            mongoQuery.include(criteria.getIncludes().stream().map(Object::toString).toList());
        }
    }

    /**
     * Applies pagination parameters from {@link Pageable} to the query.
     */
    private void preparePagination(Pageable pageable, MongoQuery mongoQuery) {
        if (pageable != null) {
            mongoQuery.add(pageable);
        }
    }

    /**
     * Retrieves all declared fields (including inherited ones up to {@link BaseCriteria})
     * from the provided criteria class.
     */
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

    /**
     * Translates a single {@link Filter} into the corresponding MongoDB {@link Criteria} condition.
     */
    private void setConditions(MongoQuery mongoQuery, Collection<Criteria> orOperatorList, Field field, Filter<?> fieldFilter) {
        if (fieldFilter.getEq() != null)
            mongoQuery.is(field.getName(), fieldFilter.getEq());
        else if (fieldFilter.getExists() != null)
            mongoQuery.exists(field.getName(), fieldFilter.getExists());
        else if (fieldFilter.getGt() != null)
            mongoQuery.gt(field.getName(), fieldFilter.getGt());
        else if (fieldFilter.getGte() != null)
            mongoQuery.gte(field.getName(), fieldFilter.getGte());
        else if (fieldFilter.getLt() != null)
            mongoQuery.lt(field.getName(), fieldFilter.getLt());
        else if (fieldFilter.getLte() != null)
            mongoQuery.lte(field.getName(), fieldFilter.getLte());
        else if (fieldFilter.getNe() != null)
            mongoQuery.ne(field.getName(), fieldFilter.getNe());
        else if (fieldFilter.getRegex() != null)
            mongoQuery.regex(field.getName(), fieldFilter.getRegex());
        else if (fieldFilter.getIn() != null)
            mongoQuery.in(field.getName(), fieldFilter.getIn());
        else if (fieldFilter.getNin() != null)
            mongoQuery.nin(field.getName(), fieldFilter.getNin());
        else if (fieldFilter.getOr() != null)
            setOrConditions(orOperatorList, field, fieldFilter);
    }
}
