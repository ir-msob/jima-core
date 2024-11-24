package ir.msob.jima.core.commons.criteria.filter;

import ir.msob.jima.core.commons.shared.BaseModel;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * The 'BaseFilterQuery' class represents a base filter query with a set of filter criteria.
 * It implements the 'BaseModel' interface.
 * The class includes fields for 'eq', 'ne', 'regex', 'gte', 'gt', 'lte', 'lt', 'exists', 'in', and 'nin' criteria, and getter and setter methods for these fields.
 * Each filter criterion is represented as a 'TYPE' object, which extends 'Serializable'.
 * The 'eq' criterion is used to filter items that are equal to a specified value.
 * The 'ne' criterion is used to filter items that are not equal to a specified value.
 * The 'regex' criterion is used to filter items that match a specified regular expression.
 * The 'gte' criterion is used to filter items that are greater than or equal to a specified value.
 * The 'gt' criterion is used to filter items that are greater than a specified value.
 * The 'lte' criterion is used to filter items that are less than or equal to a specified value.
 * The 'lt' criterion is used to filter items that are less than a specified value.
 * The 'exists' criterion is used to filter items that exist.
 * The 'in' criterion is used to filter items that are in a specified set of values.
 * The 'nin' criterion is used to filter items that are not in a specified set of values.
 * The class also includes a constructor that takes all the filter criteria as parameters.
 *
 * @param <TYPE> The type of the filter criteria.
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseFilterQuery<TYPE extends Serializable> implements BaseModel {
    /**
     * The 'eq' criterion of the base filter query.
     */
    private TYPE eq;

    /**
     * The 'ne' criterion of the base filter query.
     */
    private TYPE ne;

    /**
     * The 'regex' criterion of the base filter query.
     */
    private TYPE regex;

    /**
     * The 'gte' criterion of the base filter query.
     */
    private TYPE gte;

    /**
     * The 'gt' criterion of the base filter query.
     */
    private TYPE gt;

    /**
     * The 'lte' criterion of the base filter query.
     */
    private TYPE lte;

    /**
     * The 'lt' criterion of the base filter query.
     */
    private TYPE lt;

    /**
     * The 'exists' criterion of the base filter query.
     */
    private Boolean exists;

    /**
     * The 'in' criterion of the base filter query.
     */
    private Set<TYPE> in;

    /**
     * The 'nin' criterion of the base filter query.
     */
    private Set<TYPE> nin;
}