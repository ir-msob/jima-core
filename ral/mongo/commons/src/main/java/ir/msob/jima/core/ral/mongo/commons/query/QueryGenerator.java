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
 * @param <C> The type of the criteria.
 * @author Yasqub Abdi
 */
public class QueryGenerator<C extends BaseCriteria<ObjectId>> {

    private static void prepareOrOperation(QueryBuilder queryBuilder, Collection<Criteria> orOperatorList) {
        if (orOperatorList != null && !orOperatorList.isEmpty()) {
            queryBuilder.orOperator(orOperatorList);
        }
    }

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
            orOperatorList.add(MongoCriteria.lt(field.getName(), fieldFilter.getOr().getLte()));
        } else if (fieldFilter.getOr().getNe() != null) {
            orOperatorList.add(MongoCriteria.ne(field.getName(), fieldFilter.getOr().getNe()));
        } else if (fieldFilter.getOr().getRegex() != null) {
            orOperatorList.add(MongoCriteria.regex(field.getName(), fieldFilter.getOr().getRegex()));
        } else if (fieldFilter.getOr().getIn() != null) {
            orOperatorList.add(MongoCriteria.in(field.getName(), (Collection<Object>) fieldFilter.getOr().getIn()));
        } else if (fieldFilter.getOr().getNin() != null) {
            orOperatorList.add(MongoCriteria.nin(field.getName(), (Collection<Object>) fieldFilter.getOr().getNin()));
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

    private void setFieldsCondition(C criteria, QueryBuilder queryBuilder, Collection<Criteria> orOperatorList, Collection<Field> fields) {
        for (Field field : fields) {
            /**
             * Ability to access private fields
             */
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
        }
        /**
         * Or operator
         */
        else if (fieldFilter.getOr() != null) {
            setOrConditions(orOperatorList, field, fieldFilter);
        }
    }

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

    private void preparePagination(Pageable pageable, QueryBuilder queryBuilder) {
        if (pageable != null) {
            queryBuilder.add(pageable);
        }
    }

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
