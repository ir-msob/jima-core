package ir.msob.jima.core.commons.exception.datanotfound;

import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * The 'DataNotFoundException' class extends the 'BaseRuntimeException' class and represents a specific type of exception that is thrown when a requested data is not found.
 * It includes additional fields for the name of the entity that was not found, the ID of the entity, and the class of the entity.
 * The class also provides several constructors for creating an instance of the exception with different sets of parameters.
 *
 *
 */
@Setter
@Getter
public class DataNotFoundException extends BaseRuntimeException {
    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /**
     * The name of the entity that was not found.
     */
    private final String entity;
    /**
     * The ID of the entity that was not found.
     */
    private final String entityId;
    /**
     * The class of the entity that was not found.
     */
    private final Class<?> entityClass;

    /**
     * Constructor for the 'DataNotFoundException' class that takes a message, an entity name, an entity ID, and an entity class as parameters.
     *
     * @param message     The exception message.
     * @param entity      The name of the entity that was not found.
     * @param entityId    The ID of the entity that was not found.
     * @param entityClass The class of the entity that was not found.
     */
    public DataNotFoundException(String message, String entity, String entityId, Class<?> entityClass) {
        super(message);
        this.entity = entity;
        this.entityId = entityId;
        this.entityClass = entityClass;
    }

    /**
     * Constructor for the 'DataNotFoundException' class that takes a message, an entity name, and an entity ID as parameters.
     *
     * @param message  The exception message.
     * @param entity   The name of the entity that was not found.
     * @param entityId The ID of the entity that was not found.
     */
    public DataNotFoundException(String message, String entity, String entityId) {
        super(message);
        this.entity = entity;
        this.entityId = entityId;
        this.entityClass = null;
    }

    /**
     * Constructor for the 'DataNotFoundException' class that takes a message and an entity name as parameters.
     *
     * @param message The exception message.
     * @param entity  The name of the entity that was not found.
     */
    public DataNotFoundException(String message, String entity) {
        super(message);
        this.entity = entity;
        this.entityId = null;
        this.entityClass = null;
    }

    /**
     * Constructor for the 'DataNotFoundException' class that takes a message as a parameter.
     *
     * @param message The exception message.
     */
    public DataNotFoundException(String message) {
        super(message);
        this.entity = null;
        this.entityId = null;
        this.entityClass = null;
    }
}