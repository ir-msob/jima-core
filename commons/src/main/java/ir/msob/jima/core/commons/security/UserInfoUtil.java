package ir.msob.jima.core.commons.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;

import java.util.Base64;

/**
 * The 'UserInfoUtil' class is a utility class for encoding and decoding user information to/from Base64-encoded strings. It is typically used in the context of handling user-related data and security in a Spring-based application.
 * <p>
 * This class consists of static methods and is not meant to be instantiated.
 */
public class UserInfoUtil {

    private UserInfoUtil() {
    }

    /**
     * Encodes a user object to a Base64-encoded string.
     *
     * @param objectMapper An instance of ObjectMapper for JSON serialization.
     * @param user         A user object to encode.
     * @param <USER>       The type of user object.
     * @return The Base64-encoded user information as a string, or an empty string if the user is not present.
     * @throws JsonProcessingException if there is an issue with JSON serialization.
     */
    public static <USER extends BaseUser> String encodeUser(ObjectMapper objectMapper, USER user) throws JsonProcessingException {
        if (user != null) {
            String userInfo = objectMapper.writeValueAsString(user);
            return Base64.getEncoder().encodeToString(userInfo.getBytes());
        }
        return "";
    }

    /**
     * Decodes a Base64-encoded user information string to a user object.
     *
     * @param objectMapper An instance of ObjectMapper for JSON deserialization.
     * @param userInfo     The Base64-encoded user information as a string.
     * @param userClass    The class of the user object to deserialize.
     * @param <USER>       The type of user object.
     * @return An Optional containing the deserialized user object if userInfo is not empty; otherwise, it's empty.
     * @throws JsonProcessingException if there is an issue with JSON deserialization.
     */
    public static <USER extends BaseUser> USER decodeUser(ObjectMapper objectMapper, String userInfo, Class<USER> userClass) throws JsonProcessingException {
        if (Strings.isNotBlank(userInfo)) {
            String userJson = new String(Base64.getDecoder().decode(userInfo));
            return objectMapper.readValue(userJson, userClass);
        }
        throw new IllegalArgumentException("Authentication cannot be null. Please provide a valid authentication object.");
    }
}
