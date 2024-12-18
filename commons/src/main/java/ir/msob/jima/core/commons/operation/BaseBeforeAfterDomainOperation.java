package ir.msob.jima.core.commons.operation;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.exception.domainnotfound.DomainNotFoundException;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;
import java.util.Collection;

/**
 * This interface provides a set of methods that are called before and after each CRUD operation.
 * It is designed to be implemented by domain services that need to perform additional processing or objectvalidation.
 *
 * @param <ID>   the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
 * @param <USER> the type of the user, which extends {@code BaseUser}
 * @param <DTO>  the type of the DTO, which extends {@code BaseDto<ID>}
 * @param <C>    the type of the criteria, which extends {@code BaseCriteria<ID>}
 */
public interface BaseBeforeAfterDomainOperation<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> {

    /**
     * This method is called before the count operation.
     *
     * @param criteria the criteria used for counting
     * @param user     the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void beforeCount(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the count operation.
     *
     * @param criteria the criteria used for counting
     * @param user     the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void afterCount(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called before the get operation.
     *
     * @param criteria the criteria used for getting
     * @param user     the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void beforeGet(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the get operation.
     *
     * @param ids      the IDs of the DTOs that were gotten
     * @param dtos     the DTOs that were gotten
     * @param criteria the criteria used for getting
     * @param user     the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void afterGet(Collection<ID> ids, Collection<DTO> dtos, C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called before the save operation.
     *
     * @param dto  the DTO to be saved
     * @param user the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void beforeSave(DTO dto, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the save operation.
     *
     * @param dto      the DTO that was saved
     * @param savedDto the DTO that was actually saved
     * @param user     the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void afterSave(DTO dto, DTO savedDto, USER user)
            throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called before the update operation.
     *
     * @param previousDto the previous state of the DTO to be updated
     * @param dto         the new state of the DTO to be updated
     * @param user        the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void beforeUpdate(DTO previousDto, DTO dto, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the update operation.
     *
     * @param previousDto the previous state of the DTO that was updated
     * @param updatedDto  the updated DTO
     * @param user        the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void afterUpdate(DTO previousDto, DTO updatedDto, USER user)
            throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called before the delete operation.
     *
     * @param criteria the criteria used for deleting
     * @param user     the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void beforeDelete(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the delete operation.
     *
     * @param dto      the DTO of the DTO that was deleted
     * @param criteria the criteria used for deleting
     * @param user     the current user
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default void afterDelete(DTO dto, C criteria, USER user)
            throws DomainNotFoundException, BadRequestException {
    }
}