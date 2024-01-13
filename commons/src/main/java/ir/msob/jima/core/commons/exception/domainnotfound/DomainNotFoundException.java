package ir.msob.jima.core.commons.exception.domainnotfound;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
public class DomainNotFoundException extends RuntimeException {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 7202397920293432627L;
    private final String domainId;
    private final Class<?> domainClass;

    public DomainNotFoundException() {
        this.domainId = null;
        this.domainClass = null;
    }

    public DomainNotFoundException(String domainId) {
        this.domainId = domainId;
        this.domainClass = null;
    }

    public DomainNotFoundException(Class<?> domainClass) {
        this.domainId = null;
        this.domainClass = domainClass;
    }

    public DomainNotFoundException(String domainId, Class<?> domainClass) {
        this.domainId = domainId;
        this.domainClass = domainClass;
    }
}
