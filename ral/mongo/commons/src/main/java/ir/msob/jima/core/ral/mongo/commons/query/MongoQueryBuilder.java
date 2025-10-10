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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class MongoQueryBuilder implements BaseQueryBuilder {

    /**
     * Prepares an OR operation in the query builder using the provided criteria.
     *
     * @param baseQuery      the QueryBuilder instance to modify.
     * @param orOperatorList the list of Criteria to be combined with an OR operator.
     */
    private static void prepareOrOperation(BaseQuery baseQuery, Collection<Criteria> orOperatorList) {
        if (orOperatorList != null && !orOperatorList.isEmpty() && baseQuery instanceof MongoQuery mongoQuery) {
            mongoQuery.orOperator(orOperatorList);
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

    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> BaseQuery build(C criteria) {
        return build(criteria, null);
    }

    @Override
    public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> BaseQuery build(C criteria, Pageable pageable) {
        BaseQuery baseQuery = new MongoQuery();
        List<Criteria> orOperatorList = new ArrayList<>();
        Collection<Field> fields = getFields(criteria);
        setFieldsCondition(criteria, baseQuery, orOperatorList, fields);
        prepareOrOperation(baseQuery, orOperatorList);
        prepareIncludes(criteria, baseQuery);
        preparePagination(pageable, baseQuery);
        return baseQuery;
    }

    /**
     * Sets conditions for each field in the criteria.
     *
     * @param criteria       The filtering criteria.
     * @param baseQuery      The QueryBuilder instance to modify.
     * @param orOperatorList The list of Criteria to be combined with an OR operator.
     * @param fields         The fields to apply the filters on.
     */
    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> void setFieldsCondition(C criteria, BaseQuery baseQuery, Collection<Criteria> orOperatorList, Collection<Field> fields) {
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
                    setConditions(baseQuery, orOperatorList, field, fieldFilter);
                }
            }
        }
    }

    /**
     * Prepares include fields in the query builder based on the criteria.
     *
     * @param criteria  The filtering criteria.
     * @param baseQuery The QueryBuilder instance to modify.
     */
    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> void prepareIncludes(C criteria, BaseQuery baseQuery) {
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
                                .forEach(inc2 -> baseQuery.include(inc1))
                        );
            } else {
                baseQuery.include(criteria.getIncludesLimitation()
                        .stream()
                        .map(Object::toString)
                        .toList());
            }
        } else {
            if (criteria.getIncludes() != null && !criteria.getIncludes().isEmpty()) {
                baseQuery.include(criteria.getIncludes()
                        .stream()
                        .map(Object::toString)
                        .toList());
            }
        }
    }

    /**
     * Prepares pagination settings in the query builder.
     *
     * @param pageable  The pagination information.
     * @param baseQuery The QueryBuilder instance to modify.
     */
    private void preparePagination(Pageable pageable, BaseQuery baseQuery) {
        if (pageable != null) {
            baseQuery.add(pageable);
        }
    }

    /**
     * Retrieves all fields from the criteria class and its superclasses up to BaseCriteria.
     *
     * @param criteria The filtering criteria.
     * @return A collection of fields.
     */
    private <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> Collection<Field> getFields(C criteria) {
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
     * Sets conditions based on the provided field and filter.
     *
     * @param baseQuery      The QueryBuilder instance to modify.
     * @param orOperatorList The list of Criteria to be combined with an OR operator.
     * @param field          The field to apply the filter on.
     * @param fieldFilter    The filter containing conditions.
     */
    private void setConditions(BaseQuery baseQuery, Collection<Criteria> orOperatorList, Field field, Filter<?> fieldFilter) {
        if (fieldFilter.getEq() != null) {
            baseQuery.is(field.getName(), fieldFilter.getEq());
        } else if (fieldFilter.getExists() != null) {
            baseQuery.exists(field.getName(), fieldFilter.getExists());
        } else if (fieldFilter.getGt() != null) {
            baseQuery.gt(field.getName(), fieldFilter.getGt());
        } else if (fieldFilter.getGte() != null) {
            baseQuery.gte(field.getName(), fieldFilter.getGte());
        } else if (fieldFilter.getLt() != null) {
            baseQuery.lt(field.getName(), fieldFilter.getLt());
        } else if (fieldFilter.getLte() != null) {
            baseQuery.lte(field.getName(), fieldFilter.getLte());
        } else if (fieldFilter.getNe() != null) {
            baseQuery.ne(field.getName(), fieldFilter.getNe());
        } else if (fieldFilter.getRegex() != null) {
            baseQuery.regex(field.getName(), fieldFilter.getRegex());
        } else if (fieldFilter.getIn() != null) {
            baseQuery.in(field.getName(), fieldFilter.getIn());
        } else if (fieldFilter.getNin() != null) {
            baseQuery.nin(field.getName(), fieldFilter.getNin());
        } else if (fieldFilter.getOr() != null) {
            setOrConditions(orOperatorList, field, fieldFilter);
        }
    }


}
