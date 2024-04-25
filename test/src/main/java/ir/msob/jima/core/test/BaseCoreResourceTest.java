package ir.msob.jima.core.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;
import java.util.Optional;

/**
 * A generic resource testing interface for working with resource tests in a standardized way.
 *
 * @param <ID>   The type of the resource's unique identifier.
 * @param <USER> The type representing a user.
 * @param <D>    The type of the resource's domain.
 * @param <DTO>  The type of the resource's data transfer object.
 * @param <C>    The type of the criteria used for resource testing.
 */
public interface BaseCoreResourceTest<ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> {

    /**
     * Get the class representing the unique identifier for resources.
     *
     * @return The class representing the unique identifier.
     */
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 0);
    }

    /**
     * Get the class representing a user.
     *
     * @return The class representing a user.
     */
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 1);
    }

    /**
     * Get the class representing the resource's domain.
     *
     * @return The class representing the domain.
     */
    default Class<D> getDomainClass() {
        return (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 2);
    }

    /**
     * Get the class representing the resource's data transfer object.
     *
     * @return The class representing the data transfer object.
     */
    default Class<DTO> getDtoClass() {
        return (Class<DTO>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 3);
    }

    /**
     * Get the class representing the criteria used for resource testing.
     *
     * @return The class representing the criteria.
     */
    default Class<C> getCriteriaClass() {
        return (Class<C>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 4);
    }

    /**
     * Get the class representing the resource under test.
     *
     * @return The class representing the resource.
     */
    Class<? extends BaseResource<ID, USER>> getResourceClass();

    /**
     * Get the object mapper for JSON serialization and deserialization.
     *
     * @return The ObjectMapper instance.
     */
    ObjectMapper getObjectMapper();

    /**
     * Get an optional sample user for resource testing.
     *
     * @return An optional user instance.
     */
    Optional<USER> getSampleUser();
}
