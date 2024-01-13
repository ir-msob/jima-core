package ir.msob.jima.core.commons.model.relateddomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedDomainFilters<ID extends Comparable<ID> & Serializable> implements BaseFilters {
    private Filter<String> relatedDomainType;
    private Filter<ID> relatedDomainId;
    private Filter<String> role;
    private Filter<String> referredType;
}
