package ir.msob.jima.core.ral.mongo.commons.criteria;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collection;

/**
 * A utility class for building MongoDB Criteria objects used for querying in Spring Data MongoDB.
 *
 *
 */
public class MongoCriteria {

    private MongoCriteria() {
    }

    /**
     * Create a Criteria object for equality check.
     *
     * @param field The field to compare.
     * @param value The value to match.
     * @return The Criteria object.
     */
    public static Criteria is(Object field, Object value) {
        return Criteria.where(field.toString()).is(value);
    }

    /**
     * Create a Criteria object for regular expression matching.
     *
     * @param field The field to compare.
     * @param value The regular expression pattern.
     * @return The Criteria object.
     */
    public static Criteria regex(Object field, Object value) {
        return Criteria.where(field.toString()).regex(value.toString());
    }

    /**
     * Create a Criteria object for checking if a field exists.
     *
     * @param field The field to check.
     * @param b     A boolean value indicating whether the field should exist.
     * @return The Criteria object.
     */
    public static Criteria exists(Object field, Boolean b) {
        return Criteria.where(field.toString()).exists(b);
    }

    /**
     * Create a Criteria object for combining multiple Criteria with logical AND.
     *
     * @param criteria The Criteria objects to combine.
     * @return The Criteria object.
     */
    public static Criteria andOperator(Criteria... criteria) {
        return new Criteria().andOperator(criteria);
    }

    /**
     * Create a Criteria object for combining multiple Criteria with logical OR.
     *
     * @param criteria The Criteria objects to combine.
     * @return The Criteria object.
     */
    public static Criteria orOperator(Criteria... criteria) {
        return new Criteria().orOperator(criteria);
    }

    /**
     * Create a Criteria object for checking if a field is greater than a value.
     *
     * @param field The field to compare.
     * @param value The value to compare against.
     * @return The Criteria object.
     */
    public static Criteria gt(Object field, Object value) {
        return Criteria.where(field.toString()).gt(value);
    }

    /**
     * Create a Criteria object for checking if a field is greater than or equal to a value.
     *
     * @param field The field to compare.
     * @param value The value to compare against.
     * @return The Criteria object.
     */
    public static Criteria gte(Object field, Object value) {
        return Criteria.where(field.toString()).gte(value);
    }

    /**
     * Create a Criteria object for checking if a field is less than a value.
     *
     * @param field The field to compare.
     * @param value The value to compare against.
     * @return The Criteria object.
     */
    public static Criteria lt(Object field, Object value) {
        return Criteria.where(field.toString()).lt(value);
    }

    /**
     * Create a Criteria object for checking if a field is less than or equal to a value.
     *
     * @param field The field to compare.
     * @param value The value to compare against.
     * @return The Criteria object.
     */
    public static Criteria lte(Object field, Object value) {
        return Criteria.where(field.toString()).lte(value);
    }

    /**
     * Create a Criteria object for checking if a field is not equal to a value.
     *
     * @param field The field to compare.
     * @param value The value to compare against.
     * @return The Criteria object.
     */
    public static Criteria ne(Object field, Object value) {
        return Criteria.where(field.toString()).ne(value);
    }

    /**
     * Create a Criteria object for checking if a field's value is in a collection of values.
     *
     * @param field  The field to compare.
     * @param values The collection of values to check for.
     * @return The Criteria object.
     */
    public static Criteria in(Object field, Collection<?> values) {
        return Criteria.where(field.toString()).in(values);
    }

    /**
     * Create a Criteria object for checking if a field's value is not in a collection of values.
     *
     * @param field  The field to compare.
     * @param values The collection of values to check for.
     * @return The Criteria object.
     */
    public static Criteria nin(Object field, Collection<?> values) {
        return Criteria.where(field.toString()).nin(values);
    }
}
