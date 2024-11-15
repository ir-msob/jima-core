package ir.msob.jima.core.commons.exception.domainnotfound;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The 'DomainNotFoundResponse' class extends the 'AbstractExceptionResponse' class and represents a specific type of response that is returned when a requested domain is not found.
 * It includes additional fields for the ID of the domain that was not found and the class of the domain.
 * The class also provides several constructors for creating an instance of the response with different sets of parameters.
 * Additionally, it overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 404.
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DomainNotFoundResponse extends AbstractExceptionResponse {
    /**
     * The ID of the domain that was not found.
     */
    private final String domainId;
    /**
     * The class of the domain that was not found.
     */
    private final Class<?> domainClass;

    /**
     * Default constructor for the 'DomainNotFoundResponse' class.
     */
    public DomainNotFoundResponse() {
        super();
        this.domainId = null;
        this.domainClass = null;
    }

    /**
     * Constructor for the 'DomainNotFoundResponse' class that takes a domain ID as a parameter.
     *
     * @param domainId The ID of the domain that was not found.
     */
    public DomainNotFoundResponse(String domainId) {
        this.domainId = domainId;
        this.domainClass = null;
    }

    /**
     * Constructor for the 'DomainNotFoundResponse' class that takes a domain class as a parameter.
     *
     * @param domainClass The class of the domain that was not found.
     */
    public DomainNotFoundResponse(Class<?> domainClass) {
        this.domainId = null;
        this.domainClass = domainClass;
    }

    /**
     * Constructor for the 'DomainNotFoundResponse' class that takes a domain ID and a domain class as parameters.
     *
     * @param domainId    The ID of the domain that was not found.
     * @param domainClass The class of the domain that was not found.
     */
    public DomainNotFoundResponse(String domainId, Class<?> domainClass) {
        this.domainId = domainId;
        this.domainClass = domainClass;
    }

    /**
     * Overrides the 'getStatus' method from the 'AbstractExceptionResponse' class to return a status code of 404.
     *
     * @return The status code for a domain not found error.
     */
    @Override
    public int getStatus() {
        return 404;
    }
}