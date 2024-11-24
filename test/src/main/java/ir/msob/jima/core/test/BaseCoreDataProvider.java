package ir.msob.jima.core.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.dto.BaseDto;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.service.BaseService;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;

/**
 * A generic data provider interface for working with various types of data in a standardized way.
 *
 * @param <ID>   The type of the data entity's unique identifier.
 * @param <USER> The type representing a user.
 * @param <D>    The type of the data entity.
 * @param <DTO>  The type of the data transfer object.
 * @param <C>    The type of the criteria used for data retrieval.
 * @param <R>    The type of the data repository.
 * @param <S>    The type of the data service.
 */
public interface BaseCoreDataProvider<ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>,
        R extends BaseRepository<ID, USER, D>,
        S extends BaseService<ID, USER, D, R>> {

    /**
     * Get the class representing the unique identifier for data entities.
     *
     * @return The class representing the unique identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreDataProvider.class, 0);
    }

    /**
     * Get the class representing a user.
     *
     * @return The class representing a user.
     */
    @SuppressWarnings("unchecked")
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreDataProvider.class, 1);
    }

    /**
     * Get the class representing the data entity.
     *
     * @return The class representing the data entity.
     */
    @SuppressWarnings("unchecked")
    default Class<D> getDomainClass() {
        return (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreDataProvider.class, 2);
    }

    /**
     * Get the class representing the data transfer object.
     *
     * @return The class representing the data transfer object.
     */
    @SuppressWarnings("unchecked")
    default Class<DTO> getDtoClass() {
        return (Class<DTO>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreDataProvider.class, 3);
    }

    /**
     * Get the class representing the criteria used for data retrieval.
     *
     * @return The class representing the criteria.
     */
    @SuppressWarnings("unchecked")
    default Class<C> getCriteriaClass() {
        return (Class<C>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseCoreDataProvider.class, 4);
    }

    /**
     * Get the object mapper for JSON serialization and deserialization.
     *
     * @return The ObjectMapper instance.
     */
    ObjectMapper getObjectMapper();

    /**
     * Get a JSON patch for applying changes to data entities.
     *
     * @return The JsonPatch instance.
     */
    JsonPatch getJsonPatch();

    /**
     * Get a mandatory JSON patch for applying changes to data entities.
     *
     * @return The mandatory JsonPatch instance.
     */
    JsonPatch getMandatoryJsonPatch();

    /**
     * Create a new instance of the data transfer object.
     *
     * @return A new instance of the DTO.
     */
    DTO getNewDto();

    /**
     * Update the data transfer object with new values.
     *
     * @param dto The DTO to be updated.
     */
    void getUpdateDto(DTO dto);

    /**
     * Get a mandatory new instance of the data transfer object.
     *
     * @return A new instance of the DTO (mandatory).
     */
    DTO getMandatoryNewDto();

    /**
     * Get a mandatory update of the data transfer object with new values.
     *
     * @param dto The DTO to be updated (mandatory).
     */
    void getMandatoryUpdateDto(DTO dto);

    /**
     * Get the data service for working with data entities.
     *
     * @return The data service instance.
     */
    S getService();

    /**
     * Get a sample user for data operations.
     *
     * @return An optional user instance.
     */
    USER getSampleUser();
}

