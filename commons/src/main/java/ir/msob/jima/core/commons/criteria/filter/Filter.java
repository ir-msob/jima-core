package ir.msob.jima.core.commons.criteria.filter;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The 'Filter' class represents a filter that can be applied to fields.
 * It extends the 'BaseFilter' class and implements the 'Serializable' interface.
 * The class includes static methods for creating filters with different criteria.
 * Each filter criterion is represented as a 'TYPE' object, which extends 'Serializable'.
 * The 'eq' method creates a filter for items that are equal to a specified value.
 * The 'ne' method creates a filter for items that are not equal to a specified value.
 * The 'regex' method creates a filter for items that match a specified regular expression.
 * The 'gte' method creates a filter for items that are greater than or equal to a specified value.
 * The 'gt' method creates a filter for items that are greater than a specified value.
 * The 'lte' method creates a filter for items that are less than or equal to a specified value.
 * The 'lt' method creates a filter for items that are less than a specified value.
 * The 'exists' method creates a filter for items that exist.
 * The 'in' methods create a filter for items that are in a specified set or collection of values.
 * The 'nin' methods create a filter for items that are not in a specified set or collection of values.
 *
 * @param <TYPE> The type of the filter criteria.
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Filter<TYPE extends Serializable> extends BaseFilter<TYPE> {
    /**
     * Constructs a new 'Filter' with the specified filter criteria.
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
    @Builder
    public Filter(TYPE eq, TYPE ne, TYPE regex, TYPE gte, TYPE gt, TYPE lte, TYPE lt, Boolean exists, Set<TYPE> in, Set<TYPE> nin, BaseFilterQuery<TYPE> or) {
        super(eq, ne, regex, gte, gt, lte, lt, exists, in, nin, or);
    }

    /**
     * Creates a filter for items that are equal to a specified value.
     *
     * @param eq The specified value.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> eq(TYPE eq) {
        return Filter.<TYPE>builder().eq(eq).build();
    }

    /**
     * Creates a filter for items that are not equal to a specified value.
     *
     * @param ne The specified value.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> ne(TYPE ne) {
        return Filter.<TYPE>builder().ne(ne).build();
    }

    /**
     * Creates a filter for items that match a specified regular expression.
     *
     * @param regex The specified regular expression.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> regex(TYPE regex) {
        return Filter.<TYPE>builder().regex(regex).build();
    }

    /**
     * Creates a filter for items that are greater than or equal to a specified value.
     *
     * @param gte The specified value.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> gte(TYPE gte) {
        return Filter.<TYPE>builder().gte(gte).build();
    }

    /**
     * Creates a filter for items that are greater than a specified value.
     *
     * @param gt The specified value.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> gt(TYPE gt) {
        return Filter.<TYPE>builder().gt(gt).build();
    }

    /**
     * Creates a filter for items that are less than or equal to a specified value.
     *
     * @param lte The specified value.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> lte(TYPE lte) {
        return Filter.<TYPE>builder().lte(lte).build();
    }

    /**
     * Creates a filter for items that are less than a specified value.
     *
     * @param lt The specified value.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> lt(TYPE lt) {
        return Filter.<TYPE>builder().lt(lt).build();
    }

    /**
     * Creates a filter for items that exist.
     *
     * @param exists The existence status.
     * @return The created filter.
     */
    public static Filter<Boolean> exists(boolean exists) {
        return Filter.<Boolean>builder().exists(exists).build();
    }

    /**
     * Creates a filter for items that are in a specified set of values.
     *
     * @param in The specified set of values.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> in(Set<TYPE> in) {
        return Filter.<TYPE>builder().in(in).build();
    }

    /**
     * Creates a filter for items that are in a specified collection of values.
     *
     * @param in The specified collection of values.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> in(Collection<TYPE> in) {
        return Filter.<TYPE>builder().in(new HashSet<>(in)).build();
    }

    /**
     * Creates a filter for items that are not in a specified set of values.
     *
     * @param nin The specified set of values.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> nin(Set<TYPE> nin) {
        return Filter.<TYPE>builder().nin(nin).build();
    }

    /**
     * Creates a filter for items that are not in a specified collection of values.
     *
     * @param nin The specified collection of values.
     * @return The created filter.
     */
    public static <TYPE extends Serializable> Filter<TYPE> nin(Collection<TYPE> nin) {
        return Filter.<TYPE>builder().nin(new HashSet<>(nin)).build();
    }
}