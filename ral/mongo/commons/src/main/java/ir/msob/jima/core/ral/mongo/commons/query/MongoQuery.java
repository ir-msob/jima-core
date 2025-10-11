package ir.msob.jima.core.ral.mongo.commons.query;

import ir.msob.jima.core.commons.repository.BaseQuery;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * An abstract base class for building MongoDB queries.
 * This class provides methods to construct MongoDB queries and updates.
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MongoQuery implements BaseQuery {
    private Query query = new Query();
    private Pageable pageable;


    /**
     * Add pagination into query.
     *
     * @param pageable
     * @return
     */
    public MongoQuery with(Pageable pageable) {
        this.query.with(pageable);
        return this;
    }

    /**
     * Add pagination into MongoQuery.
     *
     * @param pageable
     * @return
     */
    public MongoQuery add(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    /**
     * @param field:     field name to sorting.
     * @param direction: direction of sorting.
     * @return
     */
    public MongoQuery withSort(String field, Sort.Direction direction) {
        this.query.with(Sort.by(direction, field));
        return this;
    }

    /**
     * Add sorting into query.
     *
     * @param sort
     * @return
     */
    public MongoQuery withSort(Sort sort) {
        this.query.with(sort);
        return this;
    }

    /**
     * Limit the number of entities to return
     *
     * @param limit
     * @return
     */
    public MongoQuery limit(Integer limit) {
        query.limit(limit);
        return this;
    }

    /**
     * Regular expression query.
     *
     * @param field: field name to query.
     * @param value: value of query.
     * @return
     */
    public MongoQuery regex(String field, Object value) {
        query.addCriteria(Criteria.where(field).regex(value.toString()));
        return this;
    }

    /**
     * Is query.
     *
     * @param field: field name to query.
     * @param value: value of query.
     * @return
     */
    public MongoQuery is(String field, Object value) {
        query.addCriteria(Criteria.where(field).is(value));
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoQuery gte(String field, Object value) {
        query.addCriteria(Criteria.where(field).gte(value));
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoQuery gt(String field, Object value) {
        query.addCriteria(Criteria.where(field).gt(value));
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoQuery lt(String field, Object value) {
        query.addCriteria(Criteria.where(field).lt(value));
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoQuery lte(String field, Object value) {
        query.addCriteria(Criteria.where(field).lte(value));
        return this;
    }

    /**
     * Add exist query of field.
     *
     * @param field
     * @param value
     * @return
     */
    public MongoQuery exists(String field, Boolean value) {
        query.addCriteria(Criteria.where(field).exists(value));
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoQuery ne(String field, Object value) {
        query.addCriteria(Criteria.where(field).ne(value));
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoQuery in(String field, Collection<?> value) {
        query.addCriteria(Criteria.where(field).in(value));
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoQuery nin(String field, Collection<?> value) {
        query.addCriteria(Criteria.where(field).nin(value));
        return this;
    }


    /**
     * @param fieldList
     * @return
     */
    public MongoQuery include(Collection<String> fieldList) {
        fieldList.forEach(field -> query.fields().include(field));
        return this;
    }

    /**
     * @param field
     * @return
     */
    public MongoQuery include(String field) {
        query.fields().include(field);
        return this;
    }

    /**
     * @param fieldList
     * @return
     */
    public MongoQuery exclude(String... fieldList) {
        Optional.of(Arrays.asList(fieldList)).ifPresent(l -> l.forEach(field -> query.fields().exclude(field)));
        return this;
    }

    /**
     * @param criteriaList
     * @return
     */
    public MongoQuery orOperator(Criteria... criteriaList) {
        if (criteriaList == null || criteriaList.length <= 0)
            return this;
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaList);
        query.addCriteria(criteria);
        return this;
    }

    /**
     * @param criteriaList
     * @return
     */
    public MongoQuery orOperator(Collection<Criteria> criteriaList) {
        if (criteriaList == null || criteriaList.isEmpty())
            return this;
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
        query.addCriteria(criteria);
        return this;
    }

}
