package ir.msob.jima.core.commons.model.criteria.filter;

import ir.msob.jima.core.commons.model.BaseModel;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Queries that can be applied to fields
 *
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseFilterQuery<TYPE extends Serializable> implements BaseModel {
    private TYPE eq;
    private TYPE ne;
    private TYPE regex;
    private TYPE gte;
    private TYPE gt;
    private TYPE lte;
    private TYPE lt;
    private Boolean exists;
    private Set<TYPE> in;
    private Set<TYPE> nin;
}
