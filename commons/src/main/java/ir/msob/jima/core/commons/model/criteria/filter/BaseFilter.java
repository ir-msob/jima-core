package ir.msob.jima.core.commons.model.criteria.filter;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseFilter<TYPE extends Serializable> extends BaseFilterQuery<TYPE> {
    private BaseFilterQuery<TYPE> or;

    public BaseFilter(TYPE eq, TYPE ne, TYPE regex, TYPE gte, TYPE gt, TYPE lte, TYPE lt, Boolean exists, Set<TYPE> in, Set<TYPE> nin, BaseFilterQuery<TYPE> or) {
        super(eq, ne, regex, gte, gt, lte, lt, exists, in, nin);
        this.or = or;
    }
}
