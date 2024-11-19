package ir.msob.jima.core.commons.repository;

import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;

/**
 * The 'BaseRepository' interface defines a set of default methods for retrieving class types related to data access and domain objects in the context of a Spring Data repository.
 * It serves as a generic interface for repositories that work with domain entities and are typically used for data access in Spring-based applications.
 *
 * @param <ID>   The type of the identifier (usually an entity's primary key) which is both comparable and serializable.
 * @param <USER> The type representing a user, typically derived from 'BaseUser'.
 * @param <D>    The type representing a domain entity, typically derived from 'BaseDomain'.
 */
public interface BaseRepository<ID extends Comparable<ID> & Serializable, USER extends BaseUser, D extends BaseDomain<ID>> {

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
     * Get the class type representing a user, typically derived from 'BaseUser'.
     * This method uses 'GenericTypeUtil' to resolve the actual type argument for the user.
     *
     * @return The class type for the user.
     */
    default Class<USER> getUserClass() {
        @SuppressWarnings("unchecked")
        Class<USER> userClass = (Class<USER>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRepository.class, 1);
        return userClass;
    }

    /**
     * Get the class type representing a domain entity, typically derived from 'BaseDomain'.
     * This method uses 'GenericTypeUtil' to resolve the actual type argument for the domain entity.
     *
     * @return The class type for the domain entity.
     */
    default Class<D> getDomainClass() {
        @SuppressWarnings("unchecked")
        Class<D> domainClass = (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRepository.class, 2);
        return domainClass;
    }
}