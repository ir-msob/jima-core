package ir.msob.jima.core.ral.mongo.commons.query;

import ir.msob.jima.core.commons.data.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Update.Position;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * An abstract base class for building MongoDB queries.
 * This class provides methods to construct MongoDB queries and updates.
 *
 * @param <T> The concrete query class extending this base class.
 *
 */
@Getter
@Setter
public abstract class MongoQuery<T> implements BaseQuery {
    protected Query query = new Query();
    protected Update update = new Update();
    protected Pageable pageable = null;

    /**
     * Subclasses must implement this method to return an instance of the concrete query class.
     *
     * @return An instance of the concrete query class.
     */
    protected abstract T build();

    /**
     * Add pagination into query.
     *
     * @param pageable
     * @return
     */
    public T with(Pageable pageable) {
        this.query.with(pageable);
        return build();
    }

    /**
     * Add pagination into MongoQuery.
     *
     * @param pageable
     * @return
     */
    public T add(Pageable pageable) {
        this.pageable = pageable;
        return build();
    }

    /**
     * @param field:     field name to sorting.
     * @param direction: direction of sorting.
     * @return
     */
    public T withSort(Object field, Direction direction) {
        this.query.with(Sort.by(direction, field.toString()));
        return build();
    }

    /**
     * Add sorting into query.
     *
     * @param sort
     * @return
     */
    public T withSort(Sort sort) {
        this.query.with(sort);
        return build();
    }

    /**
     * Limit the number of entities to return
     *
     * @param limit
     * @return
     */
    public T limit(Integer limit) {
        query.limit(limit);
        return build();
    }

    /**
     * Regular expression query.
     *
     * @param field: field name to query.
     * @param value: value of query.
     * @return
     */
    public T regex(Object field, Object value) {
        query.addCriteria(Criteria.where(field.toString()).regex(value.toString()));
        return build();
    }

    /**
     * Is query.
     *
     * @param field: field name to query.
     * @param value: value of query.
     * @return
     */
    public T is(Object field, Object value) {
        query.addCriteria(Criteria.where(field.toString()).is(value));
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T gte(Object field, Object value) {
        query.addCriteria(Criteria.where(field.toString()).gte(value));
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T gt(Object field, Object value) {
        query.addCriteria(Criteria.where(field.toString()).gt(value));
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T lt(Object field, Object value) {
        query.addCriteria(Criteria.where(field.toString()).lt(value));
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T lte(Object field, Object value) {
        query.addCriteria(Criteria.where(field.toString()).lte(value));
        return build();
    }

    /**
     * Add exist query of field.
     *
     * @param field
     * @param value
     * @return
     */
    public T exists(Object field, Boolean value) {
        query.addCriteria(Criteria.where(field.toString()).exists(value));
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T ne(Object field, Object value) {
        query.addCriteria(Criteria.where(field.toString()).ne(value));
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T in(Object field, Collection<?> value) {
        query.addCriteria(Criteria.where(field.toString()).in(value));
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T nin(Object field, Collection<?> value) {
        query.addCriteria(Criteria.where(field.toString()).nin(value));
        return build();
    }

    public T not() {
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T set(Object field, Object value) {
        update.set(field.toString(), value);
        return build();
    }

    /**
     * @param field
     * @return
     */
    public T unset(Object field) {
        update.unset(field.toString());
        return build();
    }

    /**
     * @param field
     * @param inc
     * @return
     */
    public T inc(Object field, Number inc) {
        update.inc(field.toString(), inc);
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T pull(Object field, Object value) {
        update.pull(field.toString(), value);
        return build();
    }

    /**
     * @param field
     * @param position
     * @return
     */
    public T pop(Object field, Position position) {
        update.pop(field.toString(), position);
        return build();
    }

    /**
     * @param field
     * @param valueList
     * @return
     */
    public T pullAll(Object field, Collection<?> valueList) {
        update.pullAll(field.toString(), valueList.toArray());
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T push(Object field, Object value) {
        update.push(field.toString()).each(value);
        return build();
    }

    /**
     * @param field
     * @param value
     * @param slice
     * @return
     */
    public T push(Object field, Object value, Integer slice) {
        update.push(field.toString()).slice(slice).each(value);
        return build();
    }

    /**
     * @param field
     * @param values
     * @return
     */
    public T pushAll(Object field, Collection<?> values) {
        update.push(field.toString()).each(values);
        return build();
    }

    /**
     * @param field
     * @param values
     * @param slice
     * @return
     */
    public T pushAll(Object field, Collection<?> values, Integer slice) {
        update.push(field.toString()).slice(slice).each(values);
        return build();
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public T addToSet(Object field, Object value) {
        update.addToSet(field.toString()).each(value);
        return build();
    }

    /**
     * @param field
     * @param values
     * @return
     */
    public T addToSetAll(Object field, Collection<?> values) {
        update.addToSet(field.toString()).each(values);
        return build();
    }

    /**
     * @param fieldList
     * @return
     */
    public T include(Collection<String> fieldList) {
        fieldList.forEach(field -> query.fields().include(field));
        return build();
    }

    /**
     * @param field
     * @return
     */
    public T include(String field) {
        query.fields().include(field);
        return build();
    }

    /**
     * @param fieldList
     * @return
     */
    public T exclude(Object... fieldList) {
        Optional.ofNullable(Arrays.asList(fieldList)).ifPresent(l -> l.forEach(field -> query.fields().exclude(field.toString())));
        return build();
    }

    /**
     * @param criteriaList
     * @return
     */
    public T orOperator(Criteria... criteriaList) {
        if (criteriaList == null || criteriaList.length <= 0)
            return build();

        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaList);
        query.addCriteria(criteria);
        return build();
    }

    /**
     * @param criterias
     * @return
     */
    public T orOperator(Collection<Criteria> criterias) {
        if (criterias == null || criterias.isEmpty())
            return build();

        Criteria criteria = new Criteria();
        criteria.orOperator(criterias.toArray(new Criteria[criterias.size()]));
        query.addCriteria(criteria);
        return build();
    }
}
