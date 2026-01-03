package ir.msob.jima.core.commons.service;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.util.GenericTypeUtil;
import lombok.SneakyThrows;
import org.jspecify.annotations.NonNull;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Collection;

/**
 * The 'BaseService' interface defines a set of default methods for retrieving class types childdomain to data access, domain objects, users, and repositories within a service in a Spring-based application.
 * It serves as a common foundation for various services, particularly those involved in data manipulation and interaction with repositories.
 *
 * @param <ID>   The type of the identifier (e.g., entity primary key) which should be both comparable and serializable.
 * @param <USER> The type representing a user, typically derived from 'BaseUser'.
 * @param <D>    The type representing a domain entity, typically derived from 'BaseDomain'.
 * @param <R>    The type representing a repository, typically derived from 'BaseRepository'.
 */
public interface BaseService<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>,
        R extends BaseRepository<ID, D>> {

    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 0);
    }

    /**
     * Get the class type representing a user, typically derived from 'BaseUser'.
     *
     * @return The class type for the user.
     */
    @SuppressWarnings("unchecked")
    default Class<USER> getUserClass() {
        return (Class<USER>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 1);
    }

    /**
     * Get the class type representing a domain entity, typically derived from 'BaseDomain'.
     *
     * @return The class type for the domain entity.
     */
    @SuppressWarnings("unchecked")
    default Class<D> getDomainClass() {
        return (Class<D>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 2);
    }

    /**
     * Get the class of DTO associated with the service.
     *
     * @return The class representing the DTO.
     */
    default Class<DTO> getDtoClass() {
        return (Class<DTO>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 3);
    }

    /**
     * Get the class of criteria associated with the service.
     *
     * @return The class representing the criteria.
     */
    default Class<C> getCriteriaClass() {
        return (Class<C>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 4);
    }

    /**
     * Get the class type representing a repository, typically derived from 'BaseRepository'.
     *
     * @return The class type for the repository.
     */
    @SuppressWarnings("unchecked")
    default Class<R> getRepositoryClass() {
        return (Class<R>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseService.class, 5);
    }

    /**
     * Get the repository instance associated with the service.
     *
     * @return The repository instance.
     */
    R getRepository();

    /**
     * Create a new instance of the criteria class.
     *
     * @return A new instance of the criteria class.
     */
    @SneakyThrows
    default C newCriteriaClass() {
        return getCriteriaClass().getDeclaredConstructor().newInstance();
    }

    /**
     * Convert a domain entity to a DTO entity.
     *
     * @param domain The domain entity to convert.
     * @param user   A user context.
     * @return The converted DTO entity.
     */
    DTO toDto(D domain, USER user);

    /**
     * Convert a DTO entity to a domain entity.
     *
     * @param dto  The DTO entity to convert.
     * @param user A user context.
     * @return The converted domain entity.
     */
    D toDomain(DTO dto, USER user);

    /**
     * This method is executed before the get service. It can be overridden to provide custom behavior.
     * By default, it does nothing and returns an empty Mono.
     *
     * @param criteria The criteria used for filtering entities.
     * @param user     A user associated with the operation.
     * @return A Mono indicating the completion of the pre-get operation.
     */
    default Mono<@NonNull Void> preGet(C criteria, USER user) {
        return Mono.empty();
    }

    /**
     * This method is executed after the get service. It can be overridden to provide custom behavior.
     * By default, it does nothing and returns an empty Mono.
     *
     * @param ids      A collection of entity IDs that were retrieved.
     * @param dtos     A collection of DTO entities corresponding to the retrieved domains.
     * @param criteria The criteria used for filtering entities.
     * @param user     A user associated with the operation.
     * @return A Mono indicating the completion of the post-get operation.
     */
    default Mono<@NonNull Void> postGet(Collection<ID> ids, Collection<DTO> dtos, C criteria, USER user) {
        return Mono.empty();
    }

    /**
     * Performs actions before saving entities.
     *
     * @param dto  The DTO to be saved.
     * @param user A user object.
     * @return A Mono that emits void.
     */
    default Mono<@NonNull Void> preSave(DTO dto, USER user) {
        return Mono.empty();
    }

    /**
     * Performs actions after saving entities.
     *
     * @param dto         The DTO that was saved.
     * @param savedDomain The saved domain.
     * @param user        A user object.
     * @return A Mono that emits void.
     */
    default Mono<@NonNull Void> postSave(DTO dto, D savedDomain, USER user) {
        return Mono.empty();
    }

    /**
     * Performs actions before updating entities.
     *
     * @param dto  The DTO to be updated.
     * @param user A user object.
     * @return A Mono that emits void.
     */
    default Mono<@NonNull Void> preUpdate(DTO dto, USER user) {
        return Mono.empty();
    }

    /**
     * Performs actions after updating entities.
     *
     * @param dto           The DTO that was updated.
     * @param updatedDomain The updated domain.
     * @param user          A user object.
     * @return A Mono that emits void.
     */
    default Mono<@NonNull Void> postUpdate(DTO dto, D updatedDomain, USER user) {
        return Mono.empty();
    }

    /**
     * Performs actions before deleting entities based on criteria.
     *
     * @param criteria The criteria used for deleting entities.
     * @param user     A user object.
     * @return A Mono that emits void.
     */
    default Mono<@NonNull Void> preDelete(C criteria, USER user) {
        return Mono.empty();
    }

    /**
     * Performs actions after deleting entities based on criteria.
     *
     * @param dto      The DTO of the entity that was deleted.
     * @param criteria The criteria used for deleting entities.
     * @param user     A user object.
     * @return A Mono that emits void.
     */
    default Mono<@NonNull Void> postDelete(DTO dto, C criteria, USER user) {
        return Mono.empty();
    }
}
