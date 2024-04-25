package ir.msob.jima.core.commons.exception.domainnotfound;

import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * The 'DomainNotFoundException' class extends the 'BaseRuntimeException' class and represents a specific type of exception that is thrown when a requested domain is not found.
 * It includes additional fields for the ID of the domain that was not found and the class of the domain.
 * The class also provides several constructors for creating an instance of the exception with different sets of parameters.
 *
 *
 */
@Setter
@Getter
public class DomainNotFoundException extends BaseRuntimeException {
    /**
     * The serial version UID for the serializable class.
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    /**
     * The ID of the domain that was not found.
     */
    private final String domainId;
    /**
     * The class of the domain that was not found.
     */
    private final Class<?> domainClass;

    /**
     * Default constructor for the 'DomainNotFoundException' class.
     */
    public DomainNotFoundException() {
        this.domainId = null;
        this.domainClass = null;
    }

    /**
     * Constructor for the 'DomainNotFoundException' class that takes a domain ID as a parameter.
     *
     * @param domainId The ID of the domain that was not found.
     */
    public DomainNotFoundException(String domainId) {
        this.domainId = domainId;
        this.domainClass = null;
    }

    /**
     * Constructor for the 'DomainNotFoundException' class that takes a domain class as a parameter.
     *
     * @param domainClass The class of the domain that was not found.
     */
    public DomainNotFoundException(Class<?> domainClass) {
        this.domainId = null;
        this.domainClass = domainClass;
    }

    /**
     * Constructor for the 'DomainNotFoundException' class that takes a domain ID and a domain class as parameters.
     *
     * @param domainId    The ID of the domain that was not found.
     * @param domainClass The class of the domain that was not found.
     */
    public DomainNotFoundException(String domainId, Class<?> domainClass) {
        this.domainId = domainId;
        this.domainClass = domainClass;
    }
}