package ir.msob.jima.core.commons.resource;

import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;

/**
 * The 'BaseResource' interface defines a set of default methods for managing resources, particularly for handling access control and user-related operations in a Spring-based application.
 * This interface serves as a common foundation for various resource-related operations.
 *
 * @param <ID>   The type of the identifier, which should be both comparable and serializable (e.g., entity primary key).
 * @param <USER> The type representing a user, typically derived from 'BaseUser'.
 */
public interface BaseResource<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> {

    /**
     * Get the class type for the identifier (e.g., entity primary key).
     *
     * @return The class type for the identifier.
     */
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(this.getClass(), BaseResource.class, 0);
    }

    /**
     * Get the class type representing a user, typically derived from 'BaseUser'.
     *
     * @return The class type for the user.
     */
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(this.getClass(), BaseResource.class, 1);
    }

    /**
     * Get the user service associated with the resource. This service is responsible for managing user-related operations and access control for the resource.
     *
     * @return The user service associated with the resource.
     */
    BaseUserService getUserService();
}
