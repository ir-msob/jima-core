package ir.msob.jima.core.api.restful.commons.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.UserInfoUtil;
import org.springframework.http.HttpHeaders;

import static ir.msob.jima.core.commons.Constants.USER_INFO_HEADER_NAME;

/**
 * The `BaseWebClient` interface defines common methods and utilities for making web client requests in a Java application.
 * It provides methods for working with JSON objects, particularly for setting user information in HTTP headers.
 */
public interface BaseWebClient {

    /**
     * Retrieve the ObjectMapper used for JSON serialization and deserialization.
     *
     * @return The ObjectMapper instance for handling JSON objects.
     */
    ObjectMapper getObjectMapper();

    /**
     * Set user information in the HTTP headers of a web client request by encoding user data into a JSON string.
     *
     * @param <USER>  The type representing a user entity for working with user-embeddeddomain information.
     * @param builder The HttpHeaders object representing the HTTP headers of the request.
     * @param user    An Optional containing user-embeddeddomain information.
     * @throws JsonProcessingException if there is an issue with JSON processing.
     */
    default <USER extends BaseUser> void setUserInfo(HttpHeaders builder, USER user) throws JsonProcessingException {
        String userInfo = UserInfoUtil.encodeUser(getObjectMapper(), user);
        builder.set(USER_INFO_HEADER_NAME, userInfo);
    }
}
