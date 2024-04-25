package ir.msob.jima.core.commons.security;

import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * The base interface for the user service in the security framework.
 * <p>
 * This interface provides methods to retrieve user information based on different criteria.
 *
 *
 */
public interface BaseUserService {

    /**
     * Retrieves a user based on the provided authentication.
     *
     * @param authentication An authentication.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser, A extends Authentication> Optional<USER> getUser(A authentication);

    /**
     * Retrieves a user based on the provided authentication token.
     *
     * @param token An optional authentication token.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser> Optional<USER> getUser(Optional<String> token);

    /**
     * Retrieves a user based on the user information and user class.
     *
     * @param userInfo  The user information.
     * @param userClass The class of the user.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser> Optional<USER> getUser(String userInfo, Class<USER> userClass);

    /**
     * Retrieves a user based on the provided principal.
     *
     * @param principal The principal representing the user.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser, P extends Principal> Optional<USER> getUser(P principal);

    /**
     * Retrieves a user based on the provided claims.
     *
     * @param claims A map of claims representing user information.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser> Optional<USER> getUser(Map<String, Object> claims);

    /**
     * Retrieves a user based on the user information and the provided claims.
     *
     * @param userInfo  The user information.
     * @param claims    A map of claims representing user information.
     * @param userClass The class of the user.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser> Optional<USER> getUser(String userInfo, Map<String, Object> claims, Class<USER> userClass);

    /**
     * Retrieves a user based on the user information, principal, and user class.
     *
     * @param userInfo  The user information.
     * @param principal The principal representing the user.
     * @param userClass The class of the user.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser, P extends Principal> Optional<USER> getUser(String userInfo, P principal, Class<USER> userClass);

    /**
     * Retrieves a user based on the user information, principal, and user class.
     *
     * @param user   The user information.
     * @param claims A map of claims representing user information.
     * @return An optional containing the user if found, otherwise empty.
     */
    <USER extends BaseUser> Optional<USER> getUser(USER user, Map<String, Object> claims);

    /**
     * Retrieves the system user, if applicable.
     *
     * @return An optional containing the system user if applicable, otherwise empty.
     */
    <USER extends BaseUser> Optional<USER> getSystemUser();
}