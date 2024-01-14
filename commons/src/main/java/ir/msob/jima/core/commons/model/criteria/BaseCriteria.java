package ir.msob.jima.core.commons.model.criteria;

import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.model.BaseModel;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import ir.msob.jima.core.commons.model.dto.BaseType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The 'BaseCriteria' interface represents the basic class for domain filters.
 * It extends the 'BaseModel' and 'BaseType' interfaces.
 * The interface is a generic interface, with the generic type 'ID' extending 'Comparable' and 'Serializable'.
 * This means that the ID of the criteria can be of any type that is comparable and serializable.
 * The interface includes getter and setter methods for the ID filter and for include and include limitation sets.
 * The interface also includes 'init' and 'validation' methods for initializing and validating the criteria.
 *
 * @param <ID> The type of the ID of the criteria.
 *
 */
public interface BaseCriteria<ID extends Comparable<ID> & Serializable> extends BaseModel, BaseType {

    /**
     * Returns a set of includes for the criteria.
     *
     * @return The set of includes.
     */
    default Set<String> getIncludes() {
        return new HashSet<>();
    }

    /**
     * Sets a set of includes for the criteria.
     *
     * @param includes The set of includes.
     */
    default void setIncludes(Set<String> includes) {
    }

    /**
     * Returns a set of include limitations for the criteria.
     *
     * @return The set of include limitations.
     */
    default Set<String> getIncludesLimitation() {
        return new HashSet<>();
    }

    /**
     * Sets a set of include limitations for the criteria.
     *
     * @param includes The set of include limitations.
     */
    default void setIncludesLimitation(Set<String> includes) {
    }

    /**
     * Returns the ID filter of the criteria.
     *
     * @return The ID filter.
     */
    Filter<ID> getId();

    /**
     * Sets the ID filter of the criteria.
     *
     * @param id The ID filter.
     */
    void setId(Filter<ID> id);

    /**
     * Initializes and sets some conditions to the criteria.
     */
    default void init() {
    }

    /**
     * Validates the criteria data after initialization.
     *
     * @throws BadRequestException If the validation fails.
     */
    default void validation() throws BadRequestException {
    }
}