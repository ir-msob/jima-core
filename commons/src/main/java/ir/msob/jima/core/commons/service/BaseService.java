package ir.msob.jima.core.commons.service;

import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;

/**
 * The 'BaseService' interface defines a set of default methods for retrieving class types child to data access, domain objects, users, and repositories within a service in a Spring-based application.
 * It serves as a common foundation for various services, particularly those involved in data manipulation and interaction with repositories.
 *
 * @param <ID>   The type of the identifier (e.g., entity primary key) which should be both comparable and serializable.
 * @param <USER> The type representing a user, typically derived from 'BaseUser'.
 * @param <D>    The type representing a domain entity, typically derived from 'BaseDomain'.
 * @param <R>    The type representing a repository, typically derived from 'BaseRepository'.
 */
public interface BaseService<ID extends Comparable<ID> & Serializable, USER extends BaseUser, D extends BaseDomain<ID>, R extends BaseRepository<ID, USER, D>> {

    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 0);
    }

    /**
     * Get the class type representing a user, typically derived from 'BaseUser'.
     *
     * @return The class type for the user.
     */
    @SuppressWarnings("unchecked")
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 1);
    }

    /**
     * Get the class type representing a domain entity, typically derived from 'BaseDomain'.
     *
     * @return The class type for the domain entity.
     */
    @SuppressWarnings("unchecked")
    default Class<D> getDomainClass() {
        return (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 2);
    }

    /**
     * Get the class type representing a repository, typically derived from 'BaseRepository'.
     *
     * @return The class type for the repository.
     */
    @SuppressWarnings("unchecked")
    default Class<R> getRepositoryClass() {
        return (Class<R>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 3);
    }
}
