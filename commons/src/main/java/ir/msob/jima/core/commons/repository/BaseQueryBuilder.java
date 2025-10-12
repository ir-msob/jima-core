package ir.msob.jima.core.commons.repository;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Defines the contract for building query objects based on given criteria.
 * <p>
 * Implementations of this interface are responsible for transforming a set of criteria
 * (represented by {@link BaseCriteria}) into a query object (implementing {@link BaseQuery})
 * that can be executed against a data source or query engine.
 * <p>
 * This abstraction allows flexible query creation for different persistence or process engines.
 *
 * @see BaseCriteria
 * @see BaseQuery
 */
public interface BaseQueryBuilder {

    /**
     * Builds a query object based on the provided criteria.
     *
     * @param criteria the filtering criteria used to construct the query
     * @param <ID>     the type of the entity identifier
     * @param <C>      the type of the criteria extending {@link BaseCriteria}
     * @param <Q>      the type of the query extending {@link BaseQuery}
     * @return a query object built according to the provided criteria
     */
    <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria);

    /**
     * Builds a query object based on the provided criteria and pagination information.
     *
     * @param criteria the filtering criteria used to construct the query
     * @param pageable pagination and sorting information
     * @param <ID>     the type of the entity identifier
     * @param <C>      the type of the criteria extending {@link BaseCriteria}
     * @param <Q>      the type of the query extending {@link BaseQuery}
     * @return a query object built according to the provided criteria and pageable configuration
     */
    <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria, Pageable pageable);
}