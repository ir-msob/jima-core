package ir.msob.jima.core.commons.profile;

public class ProfileConstants {
    public static final String TEST = "test";
    public static final String DEV = "dev";
    public static final String PROD = "prod";
    public static final String NATIVE = "native";

    public static final String NOT_TEST = String.format("!%s", TEST);
    public static final String NOT_DEV = String.format("!%s", DEV);
    public static final String NOT_PROD = String.format("!%s", PROD);
    public static final String NOT_NATIVE = String.format("!%s", NATIVE);

    private ProfileConstants() {
    }
}
