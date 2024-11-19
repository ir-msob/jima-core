package ir.msob.jima.core.commons.shared.criteria.filter;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * The 'BaseFilter' class represents a base filter with a set of filter criteria.
 * It extends the 'BaseFilterQuery' class.
 * The class includes a field for 'or' filter and getter and setter methods for this field.
 * The 'or' filter is used to apply a logical OR operation on the filter criteria.
 * The class also includes a constructor that takes all the filter criteria as parameters.
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
 *
 * @param <TYPE> The type of the filter criteria.
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseFilter<TYPE extends Serializable> extends BaseFilterQuery<TYPE> {
    /**
     * The 'or' filter of the base filter.
     */
    private BaseFilterQuery<TYPE> or;

    /**
     * Constructs a new 'BaseFilter' with the specified filter criteria.
     *
     * @param eq     The 'eq' criterion.
     * @param ne     The 'ne' criterion.
     * @param regex  The 'regex' criterion.
     * @param gte    The 'gte' criterion.
     * @param gt     The 'gt' criterion.
     * @param lte    The 'lte' criterion.
     * @param lt     The 'lt' criterion.
     * @param exists The 'exists' criterion.
     * @param in     The 'in' criterion.
     * @param nin    The 'nin' criterion.
     * @param or     The 'or' filter.
     */
    public BaseFilter(TYPE eq, TYPE ne, TYPE regex, TYPE gte, TYPE gt, TYPE lte, TYPE lt, Boolean exists, Set<TYPE> in, Set<TYPE> nin, BaseFilterQuery<TYPE> or) {
        super(eq, ne, regex, gte, gt, lte, lt, exists, in, nin);
        this.or = or;
    }
}