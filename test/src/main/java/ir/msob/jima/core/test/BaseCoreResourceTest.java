package ir.msob.jima.core.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.domain.BaseDtoTypeReference;
import ir.msob.jima.core.commons.element.Elements;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import ir.msob.jima.core.commons.operation.ConditionalOnOperationUtil;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.scope.Scope;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;
import java.lang.annotation.Annotation;

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
        C extends BaseCriteria<ID>>
        extends BaseDtoTypeReference<ID, DTO, C> {

    Logger logger = LoggerFactory.getLogger(BaseCoreResourceTest.class);

    /**
     * Get the class representing the unique identifier for resources.
     *
     * @return The class representing the unique identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 0);
    }

    /**
     * Get the class representing a user.
     *
     * @return The class representing a user.
     */
    @SuppressWarnings("unchecked")
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 1);
    }

    /**
     * Get the class representing the resource's domain.
     *
     * @return The class representing the domain.
     */
    @SuppressWarnings("unchecked")
    default Class<D> getDomainClass() {
        return (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 2);
    }

    /**
     * Get the class representing the resource's data transfer object.
     *
     * @return The class representing the data transfer object.
     */
    @SuppressWarnings("unchecked")
    default Class<DTO> getDtoClass() {
        return (Class<DTO>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreResourceTest.class, 3);
    }

    /**
     * Get the class representing the criteria used for resource testing.
     *
     * @return The class representing the criteria.
     */
    @SuppressWarnings("unchecked")
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
     * Get an sample user for resource testing.
     *
     * @return An optional user instance.
     */
    USER getSampleUser();

    /**
     * Determines whether a specific CRUD operation should be ignored for testing.
     *
     * @param scope The CRUD scope to check.
     * @return True if the operation should be ignored, false otherwise.
     */
    default boolean ignoreTest(Scope scope) {
        boolean result = !ConditionalOnOperationUtil.hasOperation(scope, getJimaProperties().getCrud(), getResourceClass());
        if (result) {
            logger.info("Perform {}/{} test for {} is ignored.", scope.element(), scope.operation(), getResourceClass().getSimpleName());
        }
        return result;
    }

    default boolean ignoreTest(String operation) {
        Scope scope = new Scope() {

            @Override
            public String element() {
                return Elements.DOMAIN;
            }

            @Override
            public String operation() {
                return operation;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Scope.class;
            }
        };
        return ignoreTest(scope);
    }

    default boolean ignoreTest(String element, String operation) {
        Scope scope = new Scope() {

            @Override
            public String element() {
                return element;
            }

            @Override
            public String operation() {
                return operation;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Scope.class;
            }
        };
        return ignoreTest(scope);
    }

    JimaProperties getJimaProperties();

}