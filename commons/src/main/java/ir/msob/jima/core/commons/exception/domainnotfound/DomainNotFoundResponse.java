package ir.msob.jima.core.commons.exception.domainnotfound;

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
public class DomainNotFoundResponse extends AbstractExceptionResponse {
    private final String domainId;
    private final Class<?> domainClass;

    public DomainNotFoundResponse() {
        super();
        this.domainId = null;
        this.domainClass = null;
    }

    public DomainNotFoundResponse(String domainId) {
        this.domainId = domainId;
        this.domainClass = null;
    }

    public DomainNotFoundResponse(Class<?> domainClass) {
        this.domainId = null;
        this.domainClass = domainClass;
    }

    public DomainNotFoundResponse(String domainId, Class<?> domainClass) {
        this.domainId = domainId;
        this.domainClass = domainClass;
    }

    @Override
    public Integer getStatus() {
        return 404;
    }
}
