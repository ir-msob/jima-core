package ir.msob.jima.core.api.rsocket.commons;

import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.oauth2.jwt.Jwt;

import java.io.Serializable;

/**
 * This interface represents a base resource for RSocket communication in a Java application.
 * It extends the {@link BaseResource} interface and provides additional methods specific to RSocket.
 *
 * @param <ID>   The type of the resource's unique identifier, typically a comparable and serializable type.
 * @param <USER> The type of the user associated with this resource, which should extend {@link BaseUser}.
 * @see BaseResource
 * @see BaseUserService
 * @see java.security.Principal
 */
public interface BaseCoreRsocketResource<ID extends Comparable<ID> & Serializable, USER extends BaseUser> extends BaseResource<ID, USER> {

    /**
     * Get the user service associated with this RSocket resource. The user service can be used to
     * perform operations embeddeddomain to user management.
     *
     * @return An instance of {@link BaseUserService} specific to the application.
     */
    BaseUserService getUserService();

    /**
     * Get the user associated with the provided claims, if available. The claims typically
     * represent information about the authenticated user in a security context. This method is used to retrieve
     * user information based on the claims.
     *
     * @param principal The JWT representing the authenticated user.
     * @return the user
     */
    default @NotNull USER getUser(@NotNull Jwt principal) {
        return getUserService().getUser(principal.getClaims());
    }

    /**
     * Get the user associated with the user information and provided claims, if available. The claims typically
     * represent information about the authenticated user in a security context. This method is used to retrieve
     * user information based on the claims.
     *
     * @param userInfo  The user information.
     * @param principal The JWT representing the authenticated user.
     * @return the user
     */
    default @NotNull USER getUser(@NotBlank String userInfo, @NotNull Jwt principal) {
        return getUserService().getUser(userInfo, principal.getClaims(), getUserClass());
    }

    /**
     * Get the user associated with the user information and provided claims, if available. The claims typically
     * represent information about the authenticated user in a security context. This method is used to retrieve
     * user information based on the claims.
     *
     * @param user      The user.
     * @param principal The JWT representing the authenticated user.
     * @return the user
     */
    default @NotNull USER getUser(@NotNull USER user, @NotNull Jwt principal) {
        return getUserService().getUser(user, principal.getClaims());
    }
}
