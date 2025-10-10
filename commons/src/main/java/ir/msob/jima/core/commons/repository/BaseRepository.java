package ir.msob.jima.core.commons.repository;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;

/**
 * The 'BaseRepository' interface defines a set of default methods for retrieving class types childdomain to data access and domain objects in the context of a Spring Data repository.
 * It serves as a generic interface for repositories that work with domain entities and are typically used for data access in Spring-based applications.
 *
 * @param <ID> The type of the identifier (usually an entity's primary key) which is both comparable and serializable.
 * @param <D>  The type representing a domain entity, typically derived from 'BaseDomain'.
 */
public interface BaseRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>> {

    /**
     * Get the class type for the identifier (usually the primary key) used in domain entities.
     * This method uses 'GenericTypeUtil' to resolve the actual type argument for the identifier.
     *
     * @return The class type for the identifier.
     */
    default Class<ID> getIdClass() {
        @SuppressWarnings("unchecked")
        Class<ID> idClass = (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRepository.class, 0);
        return idClass;
    }

    /**
     * Get the class type representing a domain entity, typically derived from 'BaseDomain'.
     * This method uses 'GenericTypeUtil' to resolve the actual type argument for the domain entity.
     *
     * @return The class type for the domain entity.
     */
    default Class<D> getDomainClass() {
        @SuppressWarnings("unchecked")
        Class<D> domainClass = (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRepository.class, 1);
        return domainClass;
    }

    BaseQueryBuilder getQueryBuilder();

    /**
     * Provide default criteria for a query.
     *
     * @param criteria The criteria object used for extending the query.
     * @return The extended query with criteria.
     */
    default <C extends BaseCriteria<ID>> BaseQuery criteria(C criteria) {
        return getQueryBuilder().build(criteria);
    }
}