package ir.msob.jima.core.commons.security;

/**
 * This class represents the values that can be used for the audience claim in JWT.
 * It provides constants for unknown, web, mobile, and tablet audiences.
 * It has a private constructor to prevent instantiation.
 */
public class BaseClaimKeyValue {
    /**
     * The value for an unknown audience.
     */
    public static final String AUDIENCE_UNKNOWN = "u";

    /**
     * The value for a web audience.
     */
    public static final String AUDIENCE_WEB = "w";

    /**
     * The value for a mobile audience.
     */
    public static final String AUDIENCE_MOBILE = "m";

    /**
     * The value for a tablet audience.
     */
    public static final String AUDIENCE_TABLET = "t";

    /**
     * Private constructor to prevent instantiation.
     */
    private BaseClaimKeyValue() {
    }

}