package ir.msob.jima.core.commons.exception.datanotfound;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The 'DataNotFoundResponse' class extends the 'AbstractExceptionResponse' class and represents a specific type of response that is returned when a requested data is not found.
 * It includes additional fields for the name of the entity that was not found, the ID of the entity, and the class of the entity.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 404.
 *
 *
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataNotFoundResponse extends AbstractExceptionResponse {
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
     * Default constructor for the 'DataNotFoundResponse' class.
     */
    public DataNotFoundResponse() {
        super();
        this.entity = null;
        this.entityId = null;
        this.entityClass = null;
    }

    /**
     * Constructor for the 'DataNotFoundResponse' class that takes an entity name, an entity ID, and an entity class as parameters.
     *
     * @param entity      The name of the entity that was not found.
     * @param entityId    The ID of the entity that was not found.
     * @param entityClass The class of the entity that was not found.
     */
    public DataNotFoundResponse(String entity, String entityId, Class<?> entityClass) {
        this.entity = entity;
        this.entityId = entityId;
        this.entityClass = entityClass;
    }

    /**
     * Overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 404.
     *
     * @return The status code for a data not found error.
     */
    @Override
    public Integer getStatus() {
        return 404;
    }
}