package ir.msob.jima.core.commons.operation;

/**
 * The 'Operations' class defines constants for various operations that can be performed on a resource.
 * The class includes constants for read operations, write operations, delete operations, save operations, and update operations.
 * The class also includes lists of operations that are considered read operations, write operations, delete operations, save operations, and update operations.
 * The class includes a private constructor to prevent instantiation.
 */
public class OperationsStatus {
    /* READ */
    public static final Integer COUNT = 200;
    public static final Integer COUNT_ALL = 200;
    public static final Integer GET_BY_ID = 200;
    public static final Integer GET_ONE = 200;
    public static final Integer GET_MANY = 200;
    public static final Integer GET_STREAM = 200;
    public static final Integer GET_PAGE = 200;
    public static final Integer REDIRECT = 200;
    /* WRITE */
    public static final Integer SAVE = 201;
    public static final Integer SAVE_MANY = 201;
    public static final Integer UPDATE_BY_ID = 200;
    public static final Integer UPDATE = 200;
    public static final Integer UPDATE_MANY = 200;
    public static final Integer EDIT_BY_ID = 200;
    public static final Integer EDIT = 200;
    public static final Integer EDIT_MANY = 200;
    public static final Integer DELETE_BY_ID = 200;
    public static final Integer DELETE = 200;
    public static final Integer DELETE_MANY = 200;
    public static final Integer DELETE_ALL = 200;
    public static final Integer RESUME = 200;
    public static final Integer SUSPEND = 200;
    public static final Integer COMPLETE = 200;
    public static final Integer START = 200;
    public static final Integer REPORT = 200;

    private OperationsStatus() {
    }
}
