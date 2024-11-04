package ir.msob.jima.core.commons.security;

/**
 * An interface for token services that provides a method to retrieve a token.
 * Implementations of this interface should provide the logic for generating
 * or retrieving a token.
 */
public interface BaseTokenService {

    /**
     * Retrieves a token.
     *
     * @return a String representing the token.
     */
    String getToken();
}
