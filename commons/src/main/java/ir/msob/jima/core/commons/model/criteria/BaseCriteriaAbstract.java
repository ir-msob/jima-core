package ir.msob.jima.core.commons.model.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * The 'BaseCriteriaAbstract' class is an abstract class that provides a basic implementation for domain filters.
 * It implements the 'BaseCriteria' interface with a generic type 'ID' that extends 'Comparable' and 'Serializable'.
 * This means that the ID of the criteria can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the ID filter and for include and include limitation sets.
 * The class also includes a no-argument constructor.
 * The class is annotated with '@JsonInclude(JsonInclude.Include.NON_NULL)', which means that null fields will not be included in the JSON output.
 *
 * @param <ID> The type of the ID of the criteria.
 */
@Setter
@Getter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseCriteriaAbstract<ID extends Comparable<ID> & Serializable> implements BaseCriteria<ID> {

    @Serial
    private static final long serialVersionUID = -2881143746550108596L;

    /**
     * A set of domain fields to be returned.
     * If it is empty or null, all fields will be returned.
     */
    private Set<String> includes;

    /**
     * A set of limitations for the includes.
     */
    private Set<String> includesLimitation;

    /**
     * The ID filter of the criteria.
     */
    private Filter<ID> id;

}