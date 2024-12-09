package ir.msob.jima.core.commons.security;

import ir.msob.jima.core.commons.child.relatedobject.relatedparty.RelatedPartyAbstract;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

/**
 * The base interface for the user service in the security framework.
 * <p>
 * This interface provides methods to retrieve user information based on different criteria.
 */
public interface BaseUserService {

    /**
     * Retrieves a user based on the provided authentication.
     *
     * @param authentication An authentication.
     * @return the user
     */
    <USER extends BaseUser, A extends Authentication> @NotNull USER getUser(@NotNull A authentication);

    /**
     * Retrieves a user based on the provided authentication token.
     *
     * @param token An authentication token.
     * @return the user
     */
    <USER extends BaseUser> @NotNull USER getUser(@NotBlank String token);

    /**
     * Retrieves a user based on the user information and user class.
     *
     * @param userInfo  The user information.
     * @param userClass The class of the user.
     * @return the user
     */
    <USER extends BaseUser> @NotNull USER getUser(@NotBlank String userInfo, Class<USER> userClass);

    /**
     * Retrieves a user based on the provided principal.
     *
     * @param principal The principal representing the user.
     * @return the user
     */
    <USER extends BaseUser, P extends Principal> @NotNull USER getUser(@NotNull P principal);

    /**
     * Retrieves a user based on the provided claims.
     *
     * @param claims A map of claims representing user information.
     * @return the user
     */
    <USER extends BaseUser> @NotNull USER getUser(@NotEmpty Map<String, Object> claims);

    /**
     * Retrieves a user based on the user information and the provided claims.
     *
     * @param userInfo  The user information.
     * @param claims    A map of claims representing user information.
     * @param userClass The class of the user.
     * @return the user
     */
    <USER extends BaseUser> @NotNull USER getUser(@NotBlank String userInfo, @NotEmpty Map<String, Object> claims, Class<USER> userClass);

    /**
     * Retrieves a user based on the user information, principal, and user class.
     *
     * @param userInfo  The user information.
     * @param principal The principal representing the user.
     * @param userClass The class of the user.
     * @return the user
     */
    <USER extends BaseUser, P extends Principal> @NotNull USER getUser(@NotBlank String userInfo, @NotNull P principal, Class<USER> userClass);

    /**
     * Retrieves a user based on the user information, principal, and user class.
     *
     * @param user   The user information.
     * @param claims A map of claims representing user information.
     * @return the user
     */
    <USER extends BaseUser> @NotNull USER getUser(USER user, @NotEmpty Map<String, Object> claims);

    /**
     * Retrieves the system user, if applicable.
     *
     * @return the system user
     */
    <USER extends BaseUser> USER getSystemUser();

    /**
     * Retrieves the child party for a user.
     *
     * @param user The user for whom the child party is to be retrieved.
     * @return the child party
     */
    <ID extends Comparable<ID> & Serializable, USER extends BaseUser, RP extends RelatedPartyAbstract<ID>> RP getRelatedParty(USER user);
}