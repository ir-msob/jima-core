package ir.msob.jima.core.commons.model.relateddomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This class represents the filters that can be applied when searching for related domains.
 * It implements the BaseFilters interface and provides filters for the related domain type, ID, role, and referred type.
 *
 * @param <ID> the type of the related domain ID, which must be comparable and serializable
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedDomainFilters<ID extends Comparable<ID> & Serializable> implements BaseFilters {
    /**
     * Filter for the type of the related domain.
     */
    private Filter<String> relatedDomainType;

    /**
     * Filter for the ID of the related domain.
     */
    private Filter<ID> relatedDomainId;

    /**
     * Filter for the role of the related domain.
     */
    private Filter<String> role;

    /**
     * Filter for the type of the entity that referred to this related domain.
     */
    private Filter<String> referredType;
}