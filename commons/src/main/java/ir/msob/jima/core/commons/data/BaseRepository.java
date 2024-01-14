package ir.msob.jima.core.commons.data;

import ir.msob.jima.core.commons.model.domain.BaseDomain;
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
public interface BaseRepository<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>, D extends BaseDomain<ID>> {

    /**
     * Get the class type for the identifier (usually the primary key) used in domain entities.
     * This method uses 'GenericTypeUtil' to resolve the actual type argument for the identifier.
     *
     * @return The class type for the identifier.
     */
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRepository.class, 0);
    }

    /**
     * Get the class type representing a user, typically derived from 'BaseUser'.
     * This method uses 'GenericTypeUtil' to resolve the actual type argument for the user.
     *
     * @return The class type for the user.
     */
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRepository.class, 1);
    }

    /**
     * Get the class type representing a domain entity, typically derived from 'BaseDomain'.
     * This method uses 'GenericTypeUtil' to resolve the actual type argument for the domain entity.
     *
     * @return The class type for the domain entity.
     */
    default Class<D> getDomainClass() {
        return (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRepository.class, 2);
    }
}