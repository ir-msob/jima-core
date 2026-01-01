package ir.msob.jima.core.ral.mongo.commons.query;

import ir.msob.jima.core.commons.repository.BaseUpdate;
import lombok.*;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;

/**
 * Fluent builder for MongoDB update operations.
 * <p>
 * Wraps a {@link Update} instance and provides methods
 * to build a chain of update operations that can be
 * applied to a document in a MongoDB collection.
 * <p>
 * Example usage:
 * <pre>
 *     MongoUpdate update = new MongoUpdate()
 *         .set("name", "John")
 *         .inc("age", 1)
 *         .push("tags", "active");
 * </pre>
 * The resulting {@code update} can be passed to a
 * {@code MongoTemplate} update operation.
 *
 * @see org.springframework.data.mongodb.core.query.Update
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MongoUpdate implements BaseUpdate {

    private Update update;

    /**
     * Sets a field to the given value using the MongoDB {@code $set} operator.
     *
     * @param field the field name in the document
     * @param value the value to set
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate set(String field, Object value) {
        update.set(field, value);
        return this;
    }

    /**
     * Removes the specified field using the MongoDB {@code $unset} operator.
     *
     * @param field the field name to remove
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate unset(String field) {
        update.unset(field);
        return this;
    }

    /**
     * Increments the value of a field using the MongoDB {@code $inc} operator.
     *
     * @param field the field name
     * @param inc   the amount to increment
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate inc(String field, Number inc) {
        update.inc(field, inc);
        return this;
    }

    /**
     * Removes a value from an array field using the MongoDB {@code $pull} operator.
     *
     * @param field the array field name
     * @param value the value to remove
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate pull(String field, Object value) {
        update.pull(field, value);
        return this;
    }

    /**
     * Removes all elements matching values in a collection from an array field.
     *
     * @param field     the array field name
     * @param valueList the values to pull
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate pullAll(String field, Collection<?> valueList) {
        update.pullAll(field, valueList.toArray());
        return this;
    }

    /**
     * Removes the first or last element of an array using the MongoDB {@code $pop} operator.
     *
     * @param field    the array field name
     * @param position position to pop (FIRST or LAST)
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate pop(String field, Update.Position position) {
        update.pop(field, position);
        return this;
    }

    /**
     * Adds a value to an array using the MongoDB {@code $push} operator.
     *
     * @param field the array field name
     * @param value the value to push
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate push(String field, Object value) {
        update.push(field).each(value);
        return this;
    }

    /**
     * Adds a value to an array and applies a slice limit.
     *
     * @param field the array field name
     * @param value the value to push
     * @param slice an optional slice limit
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate push(String field, Object value, Integer slice) {
        update.push(field).slice(slice).each(value);
        return this;
    }

    /**
     * Adds multiple values to an array using the MongoDB {@code $push} with {@code $each}.
     *
     * @param field  the array field name
     * @param values the values to push
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate pushAll(String field, Collection<?> values) {
        update.push(field).each(values);
        return this;
    }

    /**
     * Adds multiple values to an array with a slice limit.
     *
     * @param field  the array field name
     * @param values the values to push
     * @param slice  an optional slice limit
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate pushAll(String field, Collection<?> values, Integer slice) {
        update.push(field).slice(slice).each(values);
        return this;
    }

    /**
     * Adds a value to an array only if it does not already exist
     * using the MongoDB {@code $addToSet} operator.
     *
     * @param field the array field name
     * @param value the value to add if not present
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate addToSet(String field, Object value) {
        update.addToSet(field).each(value);
        return this;
    }

    /**
     * Adds multiple values to an array only if they do not already exist.
     *
     * @param field  the array field name
     * @param values the values to add if not present
     * @return this {@code MongoUpdate} for chaining
     */
    public MongoUpdate addToSetAll(String field, Collection<?> values) {
        update.addToSet(field).each(values);
        return this;
    }
}
