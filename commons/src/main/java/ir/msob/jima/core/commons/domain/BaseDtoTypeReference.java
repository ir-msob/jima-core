package ir.msob.jima.core.commons.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.msob.jima.core.commons.shared.BaseTypeReference;
import ir.msob.jima.core.commons.shared.PageResponse;

import java.io.Serializable;
import java.util.Collection;

/**
 * The {@code BaseDtoTypeReference} interface extends {@link BaseTypeReference} and provides
 * type references specific to DTO (Data Transfer Object) operations.
 * <p>
 * This interface is parameterized with:
 * </p>
 * <ul>
 *   <li>{@code ID} - the type of identifier, must be Comparable and Serializable</li>
 *   <li>{@code DTO} - the type of DTO, must extend BaseDto</li>
 *   <li>{@code C} - the type of criteria, must extend BaseCriteria</li>
 * </ul>
 *
 * @param <ID>  the type of identifier
 * @param <DTO> the type of data transfer object
 * @param <C>   the type of criteria
 */
public interface BaseDtoTypeReference<
        ID extends Comparable<ID> & Serializable,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>>
        extends BaseTypeReference {

    /**
     * Returns a TypeReference for a paginated response of DTOs.
     * <p>
     * Useful when deserializing JSON into a {@link PageResponse} containing DTOs.
     * </p>
     *
     * @return the TypeReference for PageResponse&lt;DTO&gt;
     */
    TypeReference<PageResponse<DTO>> getPageResponseReferenceType();

    /**
     * Returns a TypeReference for a collection of IDs.
     * <p>
     * Useful when deserializing JSON arrays into {@code Collection<ID>}.
     * </p>
     *
     * @return the TypeReference for Collection&lt;ID&gt;
     */
    TypeReference<Collection<ID>> getIdsReferenceType();

    /**
     * Returns a TypeReference for the criteria object.
     * <p>
     * Useful when deserializing JSON into criteria objects for query operations.
     * </p>
     *
     * @return the TypeReference for criteria type C
     */
    TypeReference<C> getCriteriaReferenceType();

    /**
     * Returns a TypeReference for the DTO object.
     * <p>
     * Useful when deserializing JSON into individual DTO instances.
     * </p>
     *
     * @return the TypeReference for DTO type
     */
    TypeReference<DTO> getDtoReferenceType();
}