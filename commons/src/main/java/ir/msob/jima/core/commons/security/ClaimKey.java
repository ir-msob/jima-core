package ir.msob.jima.core.commons.security;

/**
 * This class represents the keys used in JWT claims.
 * It provides constants for the ID, session ID, subject, roles, and audience keys.
 * It has a protected constructor to prevent instantiation.
 */
public class ClaimKey {
    /**
     * The key for the ID claim.
     */
    public static final String ID = "id";

    /**
     * The key for the session ID claim.
     */
    public static final String SESSION_ID = "sid";

    /**
     * The key for the subject claim.
     */
    public static final String SUBJECT = "sub";

    /**
     * The key for the roles claim.
     */
    public static final String ROLES = "rls";

    /**
     * The key for the audience claim.
     */
    public static final String AUDIENCE = "au";

    /**
     * Protected constructor to prevent instantiation.
     */
    protected ClaimKey() {
    }
}