package ir.msob.jima.core.commons.model.relatedobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Base class representing the filters that can be applied when searching for related entities.
 * It implements the BaseFilters interface and provides filters for the related entity type, ID, role, and referred type.
 *
 * @param <ID> the type of the related entity ID, which must be comparable and serializable
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedObjectFilters<ID extends Comparable<ID> & Serializable> implements BaseFilters {

    /**
     * Filter for the type of the related object.
     */
    private Filter<String> objectType;

    /**
     * Filter for the ID of the related object.
     */
    private Filter<ID> objectId;

    /**
     * Filter for the role of the related object.
     */
    private Filter<String> role;

    /**
     * Filter for the type of the object that referred to this related object.
     */
    private Filter<String> referringType;

    /**
     * Filter for the status of the related object.
     */
    private Filter<String> status;
}
