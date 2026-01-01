package ir.msob.jima.core.commons.repository;

import java.util.Collection;

/**
 * Represents a fluent update operation.
 * <p>
 * Implementations of this interface build an update definition
 * by chaining multiple update modifiers such as setting a field,
 * incrementing a value, pushing a value into a collection, etc.
 * Typical usage is to prepare an update definition for a database
 * update operation.
 * <p>
 * Example usage:
 * <pre>
 *     BaseUpdate update = ...
 *         .set("name", "John")
 *         .inc("age", 1)
 *         .push("tags", "active");
 * </pre>
 */
public interface BaseUpdate {

    /**
     * Adds a set operation for the given field.
     *
     * @param field the name of the field to update
     * @param value the new value to assign to the field
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate set(String field, Object value);

    /**
     * Adds an unset operation to remove the specified field.
     *
     * @param field the name of the field to remove
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate unset(String field);

    /**
     * Adds an increment operation for the given field.
     *
     * @param field the field name whose value will be incremented
     * @param inc   the amount to increment the field by
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate inc(String field, Number inc);

    /**
     * Adds a pull operation that removes a matching value from an array field.
     *
     * @param field the name of the array field
     * @param value the value to remove from the array
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate pull(String field, Object value);

    /**
     * Adds a pullAll operation that removes multiple matching values
     * from an array field.
     *
     * @param field     the name of the array field
     * @param valueList the collection of values to remove
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate pullAll(String field, Collection<?> valueList);

    /**
     * Adds a push operation that appends a value to an array field.
     *
     * @param field the name of the array field
     * @param value the value to append to the array
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate push(String field, Object value);

    /**
     * Adds a push operation with a slice limit, which may limit array size.
     *
     * @param field the name of the array field
     * @param value the value to append to the array
     * @param slice the optional slice limit to trim the array after push
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate push(String field, Object value, Integer slice);

    /**
     * Adds a pushAll operation to append multiple values to an array field.
     *
     * @param field  the array field name
     * @param values the values to append
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate pushAll(String field, Collection<?> values);

    /**
     * Adds a pushAll operation with a slice limit for bulk insertion.
     *
     * @param field  the array field name
     * @param values the values to append
     * @param slice  the optional slice limit to trim the array
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate pushAll(String field, Collection<?> values, Integer slice);

    /**
     * Adds an addToSet operation that ensures the given value is added
     * to an array only if it is not already present.
     *
     * @param field the name of the array field
     * @param value the value to add if not present
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate addToSet(String field, Object value);

    /**
     * Adds an addToSetAll operation that ensures multiple values
     * are added only if they are not already present.
     *
     * @param field  the array field name
     * @param values the values to add if not already present
     * @return this {@code BaseUpdate} for method chaining
     */
    BaseUpdate addToSetAll(String field, Collection<?> values);
}
