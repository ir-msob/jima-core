package ir.msob.jima.core.commons.filter;

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
public class BaseFilterQuery<TYPE extends Comparable<TYPE> & Serializable> implements BaseModel {
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
    private String regex;

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

    public boolean isMatching(TYPE value) {
        return checkEqualityConditions(value)
                && checkComparisonConditions(value)
                && checkExistenceConditions(value)
                && checkCollectionConditions(value);
    }

    private boolean checkEqualityConditions(TYPE value) {
        if (this.getEq() != null && !this.getEq().equals(value)) return false;
        if (this.getNe() != null && this.getNe().equals(value)) return false;
        return this.getRegex() == null || String.valueOf(value).matches(this.getRegex());
    }

    private boolean checkComparisonConditions(TYPE value) {
        if (this.getGte() != null && this.getGte().compareTo(value) >= 0) return false;
        if (this.getGt() != null && this.getGt().compareTo(value) > 0) return false;
        if (this.getLte() != null && this.getLte().compareTo(value) <= 0) return false;
        return this.getLt() == null || this.getLt().compareTo(value) >= 0;
    }

    private boolean checkExistenceConditions(TYPE value) {
        if (this.getExists() != null) {
            boolean valueExists = value != null;
            return (!this.getExists() || valueExists) && (this.getExists() || !valueExists);
        }
        return true;
    }

    private boolean checkCollectionConditions(TYPE value) {
        if (this.getIn() != null && !this.getIn().contains(value)) return false;
        return this.getNin() == null || !this.getNin().contains(value);
    }

    @SuppressWarnings("unchecked")
    public boolean isMatchingSerializable(Serializable value) {
        return isMatching((TYPE) value);
    }

}