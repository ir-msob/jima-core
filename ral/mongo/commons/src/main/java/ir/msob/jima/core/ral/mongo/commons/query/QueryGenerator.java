package ir.msob.jima.core.ral.mongo.commons.query;

import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import ir.msob.jima.core.ral.mongo.commons.criteria.MongoCriteria;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A Query Generator for creating MongoDB queries based on filtering criteria.
 *
 * @param <C> The type of the criteria, extending BaseCriteria with ObjectId.
 */
public class QueryGenerator<C extends BaseCriteria<ObjectId>> {

    /**
     * Prepares an OR operation in the query builder using the provided criteria.
     *
     * @param queryBuilder   the QueryBuilder instance to modify.
     * @param orOperatorList the list of Criteria to be combined with an OR operator.
     */
    private static void prepareOrOperation(QueryBuilder queryBuilder, Collection<Criteria> orOperatorList) {
        if (orOperatorList != null && !orOperatorList.isEmpty()) {
            queryBuilder.orOperator(orOperatorList);
        }
    }

    /**
     * Sets OR conditions based on the provided field and filter.
     *
     * @param orOperatorList the list of Criteria to be combined with an OR operator.
     * @param field          the field to apply the filter on.
     * @param fieldFilter    the filter containing OR conditions.
     */
    private static void setOrConditions(Collection<Criteria> orOperatorList, Field field, Filter<?> fieldFilter) {
        if (fieldFilter.getOr().getEq() != null) {
            orOperatorList.add(MongoCriteria.is(field.getName(), fieldFilter.getOr().getEq()));
        } else if (fieldFilter.getOr().getExists() != null) {
            orOperatorList.add(MongoCriteria.exists(field.getName(), fieldFilter.getOr().getExists()));
        } else if (fieldFilter.getOr().getGt() != null) {
            orOperatorList.add(MongoCriteria.gt(field.getName(), fieldFilter.getOr().getGt()));
        } else if (fieldFilter.getOr().getGte() != null) {
            orOperatorList.add(MongoCriteria.gte(field.getName(), fieldFilter.getOr().getGte()));
        } else if (fieldFilter.getOr().getLt() != null) {
            orOperatorList.add(MongoCriteria.lt(field.getName(), fieldFilter.getOr().getLt()));
        } else if (fieldFilter.getOr().getLte() != null) {
            orOperatorList.add(MongoCriteria.lte(field.getName(), fieldFilter.getOr().getLte()));
        } else if (fieldFilter.getOr().getNe() != null) {
            orOperatorList.add(MongoCriteria.ne(field.getName(), fieldFilter.getOr().getNe()));
        } else if (fieldFilter.getOr().getRegex() != null) {
            orOperatorList.add(MongoCriteria.regex(field.getName(), fieldFilter.getOr().getRegex()));
        } else if (fieldFilter.getOr().getIn() != null) {
            orOperatorList.add(MongoCriteria.in(field.getName(), fieldFilter.getOr().getIn()));
        } else if (fieldFilter.getOr().getNin() != null) {
            orOperatorList.add(MongoCriteria.nin(field.getName(), fieldFilter.getOr().getNin()));
        }
    }

    /**
     * Generates a MongoDB query based on the provided criteria and pageable information.
     *
     * @param criteria The filtering criteria.
     * @param pageable The pagination information.
     * @return A QueryBuilder instance representing the generated MongoDB query.
     */
    public QueryBuilder generateQuery(C criteria, Pageable pageable) {
        QueryBuilder queryBuilder = new QueryBuilder();
        List<Criteria> orOperatorList = new ArrayList<>();
        Collection<Field> fields = getFields(criteria);
        setFieldsCondition(criteria, queryBuilder, orOperatorList, fields);
        prepareOrOperation(queryBuilder, orOperatorList);
        prepareIncludes(criteria, queryBuilder);
        preparePagination(pageable, queryBuilder);
        return queryBuilder;
    }

    /**
     * Sets conditions for each field in the criteria.
     *
     * @param criteria       The filtering criteria.
     * @param queryBuilder   The QueryBuilder instance to modify.
     * @param orOperatorList The list of Criteria to be combined with an OR operator.
     * @param fields         The fields to apply the filters on.
     */
    private void setFieldsCondition(C criteria, QueryBuilder queryBuilder, Collection<Criteria> orOperatorList, Collection<Field> fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == Filter.class) {
                Filter<?> fieldFilter;
                try {
                    fieldFilter = (Filter<?>) field.get(criteria);
                } catch (IllegalAccessException e) {
                    continue;
                }
                if (fieldFilter != null) {
                    setConditions(queryBuilder, orOperatorList, field, fieldFilter);
                }
            }
        }
    }

    /**
     * Sets conditions based on the provided field and filter.
     *
     * @param queryBuilder   The QueryBuilder instance to modify.
     * @param orOperatorList The list of Criteria to be combined with an OR operator.
     * @param field          The field to apply the filter on.
     * @param fieldFilter    The filter containing conditions.
     */
    private void setConditions(QueryBuilder queryBuilder, Collection<Criteria> orOperatorList, Field field, Filter<?> fieldFilter) {
        if (fieldFilter.getEq() != null) {
            queryBuilder.is(field.getName(), fieldFilter.getEq());
        } else if (fieldFilter.getExists() != null) {
            queryBuilder.exclude(field.getName(), fieldFilter.getExists());
        } else if (fieldFilter.getGt() != null) {
            queryBuilder.gt(field.getName(), fieldFilter.getGt());
        } else if (fieldFilter.getGte() != null) {
            queryBuilder.gte(field.getName(), fieldFilter.getGte());
        } else if (fieldFilter.getLt() != null) {
            queryBuilder.lt(field.getName(), fieldFilter.getLt());
        } else if (fieldFilter.getLte() != null) {
            queryBuilder.lte(field.getName(), fieldFilter.getLte());
        } else if (fieldFilter.getNe() != null) {
            queryBuilder.ne(field.getName(), fieldFilter.getNe());
        } else if (fieldFilter.getRegex() != null) {
            queryBuilder.regex(field.getName(), fieldFilter.getRegex());
        } else if (fieldFilter.getIn() != null) {
            queryBuilder.in(field.getName(), fieldFilter.getIn());
        } else if (fieldFilter.getNin() != null) {
            queryBuilder.nin(field.getName(), fieldFilter.getNin());
        } else if (fieldFilter.getOr() != null) {
            setOrConditions(orOperatorList, field, fieldFilter);
        }
    }

    /**
     * Retrieves all fields from the criteria class and its superclasses up to BaseCriteria.
     *
     * @param criteria The filtering criteria.
     * @return A collection of fields.
     */
    private Collection<Field> getFields(C criteria) {
        List<Field> fields = new ArrayList<>();
        if (criteria != null) {
            Class<?> type = criteria.getClass();
            while (type != null && type != BaseCriteria.class) {
                Field[] classFields = type.getDeclaredFields();
                if (classFields.length > 0)
                    fields.addAll(Arrays.asList(classFields));
                type = type.getSuperclass();
            }
        }
        return fields;
    }

    /**
     * Prepares pagination settings in the query builder.
     *
     * @param pageable     The pagination information.
     * @param queryBuilder The QueryBuilder instance to modify.
     */
    private void preparePagination(Pageable pageable, QueryBuilder queryBuilder) {
        if (pageable != null) {
            queryBuilder.add(pageable);
        }
    }

    /**
     * Prepares include fields in the query builder based on the criteria.
     *
     * @param criteria     The filtering criteria.
     * @param queryBuilder The QueryBuilder instance to modify.
     */
    private void prepareIncludes(C criteria, QueryBuilder queryBuilder) {
        if (criteria == null)
            return;

        if (criteria.getIncludesLimitation() != null && !criteria.getIncludesLimitation().isEmpty()) {
            if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
                criteria
                        .getIncludes()
                        .forEach(inc1 -> criteria
                                .getIncludesLimitation()
                                .stream()
                                .map(Object::toString)
                                .filter(inc1::equals)
                                .forEach(inc2 -> queryBuilder.include(inc1))
                        );
            } else {
                queryBuilder.include(criteria.getIncludesLimitation()
                        .stream()
                        .map(Object::toString)
                        .toList());
            }
        } else {
            if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
                queryBuilder.include(criteria.getIncludes()
                        .stream()
                        .map(Object::toString)
                        .toList());
            }
        }
    }
}
