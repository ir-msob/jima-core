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
 * Abstract base class for building MongoDB queries.
 * <p>
 * This class wraps a {@link Query} and provides fluent methods
 * for adding pagination, sorting, and various criteria.
 * You can chain calls to build a complex query incrementally
 * and then pass the resulting {@link Query} to a {@code MongoTemplate}
 * or repository implementation.
 *
 * @see org.springframework.data.mongodb.core.query.Query
 * @see Criteria
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
     * Adds pagination to the underlying MongoDB query.
     *
     * @param pageable the pagination information (page number, page size, sort)
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery with(Pageable pageable) {
        this.query.with(pageable);
        return this;
    }

    /**
     * Stores the given pageable locally without applying to query immediately.
     *
     * @param pageable the pagination metadata to add
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery add(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    /**
     * Adds sorting by a single field and direction.
     *
     * @param field     the field name for sorting
     * @param direction the sorting direction
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery withSort(String field, Sort.Direction direction) {
        this.query.with(Sort.by(direction, field));
        return this;
    }

    /**
     * Adds an explicit sort instance to the query.
     *
     * @param sort the {@link Sort} object defining ordering
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery withSort(Sort sort) {
        this.query.with(sort);
        return this;
    }

    /**
     * Sets a maximum number of documents to return.
     *
     * @param limit maximum number of results
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery limit(Integer limit) {
        query.limit(limit);
        return this;
    }

    /**
     * Adds a regular expression condition on a field.
     *
     * @param field the field name in the document
     * @param value the regex pattern string to match
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery regex(String field, Object value) {
        query.addCriteria(Criteria.where(field).regex(value.toString()));
        return this;
    }

    /**
     * Adds an equality condition on a field.
     *
     * @param field the field name
     * @param value the value to match exactly
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery is(String field, Object value) {
        query.addCriteria(Criteria.where(field).is(value));
        return this;
    }

    /**
     * Adds a greater-than-or-equal condition on a field.
     *
     * @param field the field name
     * @param value the boundary value
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery gte(String field, Object value) {
        query.addCriteria(Criteria.where(field).gte(value));
        return this;
    }

    /**
     * Adds a greater-than condition on a field.
     *
     * @param field the field name
     * @param value the lower bound
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery gt(String field, Object value) {
        query.addCriteria(Criteria.where(field).gt(value));
        return this;
    }

    /**
     * Adds a less-than condition on a field.
     *
     * @param field the field name
     * @param value the upper bound
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery lt(String field, Object value) {
        query.addCriteria(Criteria.where(field).lt(value));
        return this;
    }

    /**
     * Adds a less-than-or-equal condition on a field.
     *
     * @param field the field name
     * @param value the boundary value
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery lte(String field, Object value) {
        query.addCriteria(Criteria.where(field).lte(value));
        return this;
    }

    /**
     * Adds an existence check for a field.
     *
     * @param field the field name
     * @param value true to require the field exist, false otherwise
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery exists(String field, Boolean value) {
        query.addCriteria(Criteria.where(field).exists(value));
        return this;
    }

    /**
     * Adds a not-equal condition.
     *
     * @param field the field name
     * @param value the value that must not match
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery ne(String field, Object value) {
        query.addCriteria(Criteria.where(field).ne(value));
        return this;
    }

    /**
     * Adds an inclusion condition with a collection of values.
     *
     * @param field the field name
     * @param value a collection of allowable values
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery in(String field, Collection<?> value) {
        query.addCriteria(Criteria.where(field).in(value));
        return this;
    }

    /**
     * Adds an exclusion condition with a collection.
     *
     * @param field the field name
     * @param value a collection of values to exclude
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery nin(String field, Collection<?> value) {
        query.addCriteria(Criteria.where(field).nin(value));
        return this;
    }

    /**
     * Includes multiple fields in the returned result set.
     *
     * @param fieldList the list of field names to include
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery include(Collection<String> fieldList) {
        fieldList.forEach(field -> query.fields().include(field));
        return this;
    }

    /**
     * Includes a single field in the results.
     *
     * @param field the field name to include
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery include(String field) {
        query.fields().include(field);
        return this;
    }

    /**
     * Excludes one or more fields from result documents.
     *
     * @param fieldList field names to exclude
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery exclude(String... fieldList) {
        Optional.of(Arrays.asList(fieldList))
                .ifPresent(l -> l.forEach(field -> query.fields().exclude(field)));
        return this;
    }

    /**
     * Adds an `$or` condition combining given criteria.
     *
     * @param criteriaList the array of {@link Criteria} to combine
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery orOperator(Criteria... criteriaList) {
        if (criteriaList == null || criteriaList.length <= 0)
            return this;
        Criteria criteria = new Criteria().orOperator(criteriaList);
        query.addCriteria(criteria);
        return this;
    }

    /**
     * Adds an `$or` condition from a collection of criteria.
     *
     * @param criteriaList the collection of criteria
     * @return this {@code MongoQuery} instance
     */
    public MongoQuery orOperator(Collection<Criteria> criteriaList) {
        if (criteriaList == null || criteriaList.isEmpty())
            return this;
        Criteria criteria = new Criteria()
                .orOperator(criteriaList.toArray(new Criteria[0]));
        query.addCriteria(criteria);
        return this;
    }
}
