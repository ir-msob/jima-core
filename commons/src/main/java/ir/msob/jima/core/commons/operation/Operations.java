package ir.msob.jima.core.commons.operation;

import java.util.List;
import java.util.stream.Stream;

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
    public static final String GET_BY_ID = "get-by-id";
    public static final String GET_ONE = "get-one";
    public static final String GET_MANY = "get-many";
    public static final String GET_STREAM = "get-stream";
    public static final String GET_PAGE = "get-page";
    public static final String REDIRECT = "redirect";
    /* WRITE */
    public static final String WRITE = "write";
    public static final String SAVE = "save";
    public static final String SAVE_MANY = "save-many";
    public static final String UPDATE_BY_ID = "update-by-id";
    public static final String UPDATE_BY_KEY = "update-by-key";
    public static final String UPDATE_BY_NAME = "update-by-name";
    public static final String UPDATE_BY_RELATED_ID = "update-by-embeddeddomain-id";
    public static final String UPDATE = "update";
    public static final String UPDATE_MANY = "update-many";
    public static final String EDIT_BY_ID = "edit-by-id";
    public static final String EDIT = "edit";
    public static final String EDIT_MANY = "edit-many";
    public static final String DELETE_BY_ID = "delete-by-id";
    public static final String DELETE_BY_KEY = "delete-by-key";
    public static final String DELETE_BY_NAME = "delete-by-name";
    public static final String DELETE_BY_RELATED_ID = "delete-by-embeddeddomain-id";
    public static final String DELETE = "delete";
    public static final String DELETE_MANY = "delete-many";
    public static final String DELETE_ALL = "delete-all";
    public static final String RESUME = "resume";
    public static final String SUSPEND = "suspend";
    public static final String COMPLETE = "complete";
    public static final String START = "start";
    public static final String RESUME_BY_ID = "resume-by-id";
    public static final String SUSPEND_BY_ID = "suspend-by-id";
    public static final String COMPLETE_BY_ID = "complete-by-id";
    public static final String START_BY_ID = "start-by-id";
    public static final String REPORT = "report";
    public static final List<String> DELETES = List.of(DELETE_BY_ID, DELETE_BY_KEY, DELETE_BY_NAME, DELETE_BY_RELATED_ID, DELETE, DELETE_MANY, DELETE_ALL);
    public static final List<String> SAVES = List.of(SAVE, SAVE_MANY);
    public static final List<String> UPDATES = List.of(UPDATE_BY_ID, UPDATE_BY_KEY, UPDATE_BY_NAME, UPDATE_BY_RELATED_ID, UPDATE, UPDATE_MANY, EDIT_BY_ID, EDIT, EDIT_MANY, RESUME, SUSPEND, COMPLETE, START, RESUME_BY_ID, SUSPEND_BY_ID, COMPLETE_BY_ID, START_BY_ID);
    public static final List<String> WRITES = Stream.of(SAVES, UPDATES, DELETES)
            .flatMap(List::stream)
            .toList();
    public static final List<String> READS = List.of(COUNT, COUNT_ALL, GET_BY_ID, GET_ONE, GET_MANY, GET_STREAM, GET_PAGE, REPORT, REDIRECT);

    private Operations() {
    }

}
