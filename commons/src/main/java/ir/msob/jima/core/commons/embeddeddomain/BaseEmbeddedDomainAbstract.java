package ir.msob.jima.core.commons.embeddeddomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.element.BaseElementAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Abstract base class for embedded domain entities.
 *
 * @param <ID> the type of the identifier, which must be comparable and serializable
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEmbeddedDomainAbstract<ID extends Comparable<ID> & Serializable> extends BaseElementAbstract<ID> implements BaseEmbeddedDomain<ID> {

    /**
     * The parent domain ID of the model.
     */
    private ID parentId;

    /**
     * Constructs a new instance with the specified ID and parent ID.
     *
     * @param id       the ID of the entity
     * @param parentId the parent domain ID of the entity
     */
    protected BaseEmbeddedDomainAbstract(ID id, ID parentId) {
        super(id);
        this.parentId = parentId;
    }

    /**
     * Enum representing the field names of the `BaseembeddeddomainAbstract` class.
     */
    public enum FN {
        /**
         * Represents the parent domain ID field.
         */
        parentId
    }
}