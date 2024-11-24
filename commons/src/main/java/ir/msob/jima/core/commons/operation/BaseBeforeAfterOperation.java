package ir.msob.jima.core.commons.operation;

import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.commons.dto.BaseDto;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.exception.domainnotfound.DomainNotFoundException;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;
import java.util.Collection;

/**
 * This interface provides a set of methods that are called before and after each CRUD operation.
 * It is designed to be implemented by domain services that need to perform additional processing or objectvalidation.
 */
public interface BaseBeforeAfterOperation {

    /**
     * This method is called before the count operation.
     *
     * @param criteria the criteria used for counting
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            C extends BaseCriteria<ID>> void beforeCount(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the count operation.
     *
     * @param criteria the criteria used for counting
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            C extends BaseCriteria<ID>> void afterCount(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called before the get operation.
     *
     * @param criteria the criteria used for getting
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            C extends BaseCriteria<ID>> void beforeGet(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the get operation.
     *
     * @param ids      the IDs of retrieved entities
     * @param dtos     the retrieved DTOs
     * @param criteria the criteria used for getting
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <DTO>    the type of the DTO, which extends {@code BaseDto<ID>}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>,
            C extends BaseCriteria<ID>> void afterGet(Collection<ID> ids, Collection<DTO> dtos, C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }


    /**
     * This method is called before the save operation.
     *
     * @param dto    the DTO to be saved
     * @param user   the current user
     * @param <ID>   the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER> the type of the user, which extends {@code BaseUser}
     * @param <DTO>  the type of the DTO, which extends {@code BaseDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> void beforeSave(DTO dto, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the save operation.
     *
     * @param dto      the DTO that was saved
     * @param savedDto the saved DTO
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <DTO>    the type of the DTO, which extends {@code BaseDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> void afterSave(DTO dto, DTO savedDto, USER user)
            throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called before the update operation.
     *
     * @param previousDto the DTO before the update
     * @param dto         the DTO to be updated
     * @param user        the current user
     * @param <ID>        the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>      the type of the user, which extends {@code BaseUser}
     * @param <DTO>       the type of the DTO, which extends {@code BaseDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> void beforeUpdate(DTO previousDto, DTO dto, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the update operation.
     *
     * @param previousDto the DTO before the update
     * @param updatedDto  the DTO after the update
     * @param user        the current user
     * @param <ID>        the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>      the type of the user, which extends {@code BaseUser}
     * @param <DTO>       the type of the DTO, which extends {@code BaseDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> void afterUpdate(DTO previousDto, DTO updatedDto, USER user)
            throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called before the delete operation.
     *
     * @param criteria the criteria used for deleting
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            C extends BaseCriteria<ID>> void beforeDelete(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
    }

    /**
     * This method is called after the delete operation.
     *
     * @param dto      the DTO of deleted entity
     * @param criteria the criteria used for deleting
     * @param dtoClass the class of the DTO
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <DTO>    the type of the DTO, which extends {@code BaseDto<ID>}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>,
            C extends BaseCriteria<ID>> void afterDelete(DTO dto, C criteria, Class<DTO> dtoClass, USER user)
            throws DomainNotFoundException, BadRequestException {
    }
}