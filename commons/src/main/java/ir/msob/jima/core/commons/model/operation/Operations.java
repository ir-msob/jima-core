package ir.msob.jima.core.commons.model.operation;

import java.util.List;

/**
 * The 'Operations' class defines constants for various operations that can be performed on a resource.
 * The class includes constants for read operations, write operations, delete operations, save operations, and update operations.
 * The class also includes lists of operations that are considered read operations, write operations, delete operations, save operations, and update operations.
 * The class includes a private constructor to prevent instantiation.
 */
public class Operations {
    /* READ */
    public static final String READ = "read";
    public static final String COUNT = "count";
    public static final String COUNT_ALL = "count-all";
    public static final String GET_ONE = "get-one";
    public static final String GET_MANY = "get-many";
    public static final String GET_STREAM = "get-stream";
    public static final String GET_PAGE = "get-page";
    /* WRITE */
    public static final String WRITE = "write";
    public static final String SAVE = "save";
    public static final String SAVE_MANY = "save-many";
    public static final String UPDATE = "update";
    public static final String UPDATE_MANY = "update-many";
    public static final String EDIT = "edit";
    public static final String EDIT_MANY = "edit-many";
    public static final String DELETE = "delete";
    public static final String DELETE_MANY = "delete-many";
    public static final String DELETE_ALL = "delete-all";
    public static final String RESUME = "resume";
    public static final String SUSPEND = "suspend";
    public static final String COMPLETE = "complete";
    public static final String START = "start";
    public static final List<String> READS = List.of(COUNT, COUNT_ALL, GET_ONE, GET_MANY, GET_STREAM, GET_PAGE);
    public static final List<String> WRITES = List.of(SAVE, SAVE_MANY, UPDATE, UPDATE_MANY, EDIT, EDIT_MANY, DELETE, DELETE_MANY, DELETE_ALL, RESUME, SUSPEND, COMPLETE, START);
    public static final List<String> DELETES = List.of(DELETE, DELETE_MANY, DELETE_ALL);
    public static final List<String> SAVES = List.of(SAVE, SAVE_MANY);
    public static final List<String> UPDATES = List.of(UPDATE, UPDATE_MANY, EDIT, EDIT_MANY, RESUME, SUSPEND, COMPLETE, START);

    private Operations() {
    }

}
