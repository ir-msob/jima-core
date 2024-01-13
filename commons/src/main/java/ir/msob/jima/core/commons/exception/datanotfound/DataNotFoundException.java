package ir.msob.jima.core.commons.exception.datanotfound;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
public class DataNotFoundException extends RuntimeException {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    private final String entity;
    private final String entityId;
    private final Class<?> entityClass;

    public DataNotFoundException(String message, String entity, String entityId, Class<?> entityClass) {
        super(message);
        this.entity = entity;
        this.entityId = entityId;
        this.entityClass = entityClass;
    }

    public DataNotFoundException(String message, String entity, String entityId) {
        super(message);
        this.entity = entity;
        this.entityId = entityId;
        this.entityClass = null;
    }

    public DataNotFoundException(String message, String entity) {
        super(message);
        this.entity = entity;
        this.entityId = null;
        this.entityClass = null;
    }

    public DataNotFoundException(String message) {
        super(message);
        this.entity = null;
        this.entityId = null;
        this.entityClass = null;
    }
}
