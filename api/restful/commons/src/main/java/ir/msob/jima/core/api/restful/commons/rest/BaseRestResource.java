package ir.msob.jima.core.api.restful.commons.rest;

import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.io.Serializable;
import java.security.Principal;

import static ir.msob.jima.core.commons.Constants.USER_INFO_HEADER_NAME;

/**
 * The `BaseRestResource` interface defines common methods and utilities for RESTful resources in a Java application. It extends the {@link BaseResource}
 * interface, which represents resources associated with a user.
 *
 * @param <ID>   The type representing unique identifiers for entities, typically extending Comparable and Serializable.
 * @param <USER> The type representing a user entity that is often used in RESTful interactions.
 */
public interface BaseRestResource<ID extends Comparable<ID> & Serializable, USER extends BaseUser> extends BaseResource<ID, USER> {

    /**
     * Retrieve the user associated with the given Principal object.
     *
     * @param principal The Principal object representing the user.
     * @return the user
     */
    default @NotNull USER getUser(Principal principal) {
        return getUserService().getUser(principal);
    }

    /**
     * Retrieve the user associated with the given user information.
     *
     * @param userInfo The user information string.
     * @return the user
     */
    default @NotNull USER getUser(String userInfo) {
        return getUserService().getUser(userInfo, getUserClass());
    }

    /**
     * Retrieve the user associated with the user information extracted from the ServerWebExchange and Principal.
     *
     * @param serverWebExchange The ServerWebExchange object representing the web request and response.
     * @param principal         The Principal object representing the user.
     * @return the user
     */
    default @NotNull USER getUser(ServerWebExchange serverWebExchange, Principal principal) {
        String userInfo = serverWebExchange.getRequest().getHeaders().getFirst(USER_INFO_HEADER_NAME);
        return getUserService().getUser(userInfo, principal, getUserClass());
    }

    /**
     * Retrieve the user associated with the user information extracted from the ServerWebExchange.
     *
     * @param serverWebExchange The ServerWebExchange object representing the web request and response.
     * @return the user
     */
    default @NotNull USER getUser(ServerWebExchange serverWebExchange) {
        String token = getToken(serverWebExchange);
        return getUserService().getUser(token);
    }

    /**
     * Retrieve the authentication token from the ServerWebExchange.
     *
     * @param serverWebExchange The ServerWebExchange object representing the web request and response.
     * @return the authentication token
     */
    default @NotNull String getToken(@NotNull ServerWebExchange serverWebExchange) {
        String token = serverWebExchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (Strings.isBlank(token))
            throw new BadRequestException("Authorization header is missing or empty. Please provide a valid token.");
        return token;
    }
}
