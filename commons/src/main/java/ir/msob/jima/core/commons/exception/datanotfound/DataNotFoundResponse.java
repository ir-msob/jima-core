package ir.msob.jima.core.commons.exception.datanotfound;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataNotFoundResponse extends AbstractExceptionResponse {
    private final String entity;
    private final String entityId;
    private final Class<?> entityClass;

    public DataNotFoundResponse() {
        super();
        this.entity = null;
        this.entityId = null;
        this.entityClass = null;
    }

    public DataNotFoundResponse(String entity, String entityId, Class<?> entityClass) {
        this.entity = entity;
        this.entityId = entityId;
        this.entityClass = entityClass;
    }

    @Override
    public Integer getStatus() {
        return 404;
    }
}
