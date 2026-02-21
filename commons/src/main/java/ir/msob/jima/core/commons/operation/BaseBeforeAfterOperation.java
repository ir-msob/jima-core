package ir.msob.jima.core.commons.operation;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.exception.domainnotfound.DomainNotFoundException;
import ir.msob.jima.core.commons.security.BaseUser;
import org.jspecify.annotations.NonNull;
import reactor.core.publisher.Mono;

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
            C extends BaseCriteria<ID>> Mono<@NonNull Void> beforeCount(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
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
            C extends BaseCriteria<ID>> Mono<@NonNull Void> afterCount(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
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
            C extends BaseCriteria<ID>> Mono<@NonNull Void> beforeGet(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
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
     * @param <DTO>    the type of the DTO, which extends {@code BaseChildDto<ID>}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>,
            C extends BaseCriteria<ID>> Mono<@NonNull Void> afterGet(Collection<ID> ids, Collection<DTO> dtos, C criteria, USER user) throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
    }


    /**
     * This method is called before the save operation.
     *
     * @param dto    the DTO to be saved
     * @param user   the current user
     * @param <ID>   the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER> the type of the user, which extends {@code BaseUser}
     * @param <DTO>  the type of the DTO, which extends {@code BaseChildDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> Mono<@NonNull Void> beforeSave(DTO dto, USER user) throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
    }

    /**
     * This method is called after the save operation.
     *
     * @param dto      the DTO that was saved
     * @param savedDto the saved DTO
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <DTO>    the type of the DTO, which extends {@code BaseChildDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> Mono<@NonNull Void> afterSave(DTO dto, DTO savedDto, USER user)
            throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
    }

    /**
     * This method is called before the update operation.
     *
     * @param previousDto the DTO before the update
     * @param dto         the DTO to be updated
     * @param user        the current user
     * @param <ID>        the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>      the type of the user, which extends {@code BaseUser}
     * @param <DTO>       the type of the DTO, which extends {@code BaseChildDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> Mono<@NonNull Void> beforeUpdate(DTO previousDto, DTO dto, USER user) throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
    }

    /**
     * This method is called after the update operation.
     *
     * @param previousDto the DTO before the update
     * @param updatedDto  the DTO after the update
     * @param user        the current user
     * @param <ID>        the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>      the type of the user, which extends {@code BaseUser}
     * @param <DTO>       the type of the DTO, which extends {@code BaseChildDto<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>> Mono<@NonNull Void> afterUpdate(DTO previousDto, DTO updatedDto, USER user)
            throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
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
            C extends BaseCriteria<ID>> Mono<@NonNull Void> beforeDelete(C criteria, USER user) throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
    }

    /**
     * This method is called after the delete operation.
     *
     * @param dto      the DTO of deleted entity
     * @param criteria the criteria used for deleting
     * @param user     the current user
     * @param <ID>     the type of the ID of the DTO, which extends {@code Comparable} and {@code Serializable}
     * @param <USER>   the type of the user, which extends {@code BaseUser}
     * @param <DTO>    the type of the DTO, which extends {@code BaseChildDto<ID>}
     * @param <C>      the type of the criteria, which extends {@code BaseCriteria<ID>}
     * @throws DomainNotFoundException if the domain is not found
     * @throws BadRequestException     if the request is bad
     */
    default <ID extends Comparable<ID> & Serializable,
            USER extends BaseUser,
            DTO extends BaseDto<ID>,
            C extends BaseCriteria<ID>> Mono<@NonNull Void> afterDelete(DTO dto, C criteria, USER user)
            throws DomainNotFoundException, BadRequestException {
        return Mono.empty();
    }
}