package ir.msob.jima.core.commons.profile;

/**
 * Constants for application profiles.
 * This class provides constants for different environment profiles used in the application.
 */
public class ProfileConstants {

    /**
     * Profile constant for the test environment.
     */
    public static final String TEST = "test";

    /**
     * Profile constant for the development environment.
     */
    public static final String DEV = "dev";

    /**
     * Profile constant for the production environment.
     */
    public static final String PROD = "prod";

    /**
     * Profile constant for the native environment.
     */
    public static final String NATIVE = "native";

    /**
     * Profile constant for environments that are not test.
     */
    public static final String NOT_TEST = String.format("!%s", TEST);

    /**
     * Profile constant for environments that are not development.
     */
    public static final String NOT_DEV = String.format("!%s", DEV);

    /**
     * Profile constant for environments that are not production.
     */
    public static final String NOT_PROD = String.format("!%s", PROD);

    /**
     * Profile constant for environments that are not native.
     */
    public static final String NOT_NATIVE = String.format("!%s", NATIVE);

    /**
     * Private constructor to prevent instantiation.
     */
    private ProfileConstants() {
    }
}
