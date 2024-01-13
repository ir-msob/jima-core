package ir.msob.jima.core.commons.model.criteria.filter;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Filters that can be applied to fields
 *
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Filter<TYPE extends Serializable> extends BaseFilter<TYPE> {

    @Builder
    public Filter(TYPE eq, TYPE ne, TYPE regex, TYPE gte, TYPE gt, TYPE lte, TYPE lt, Boolean exists, Set<TYPE> in, Set<TYPE> nin, BaseFilterQuery<TYPE> or) {
        super(eq, ne, regex, gte, gt, lte, lt, exists, in, nin, or);
    }

    public static <TYPE extends Serializable> Filter<TYPE> eq(TYPE eq) {
        return Filter.<TYPE>builder().eq(eq).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> ne(TYPE ne) {
        return Filter.<TYPE>builder().ne(ne).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> regex(TYPE regex) {
        return Filter.<TYPE>builder().regex(regex).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> gte(TYPE gte) {
        return Filter.<TYPE>builder().gte(gte).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> gt(TYPE gt) {
        return Filter.<TYPE>builder().gt(gt).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> lte(TYPE lte) {
        return Filter.<TYPE>builder().lte(lte).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> lt(TYPE lt) {
        return Filter.<TYPE>builder().lt(lt).build();
    }

    public static Filter<Boolean> exists(boolean exists) {
        return Filter.<Boolean>builder().exists(exists).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> in(Set<TYPE> in) {
        return Filter.<TYPE>builder().in(in).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> in(Collection<TYPE> in) {
        return Filter.<TYPE>builder().in(new HashSet<>(in)).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> nin(Set<TYPE> nin) {
        return Filter.<TYPE>builder().nin(nin).build();
    }

    public static <TYPE extends Serializable> Filter<TYPE> nin(Collection<TYPE> nin) {
        return Filter.<TYPE>builder().nin(new HashSet<>(nin)).build();
    }
}
