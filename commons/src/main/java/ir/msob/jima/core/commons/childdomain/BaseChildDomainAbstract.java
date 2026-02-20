package ir.msob.jima.core.commons.childdomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseDomainAbstract;
import ir.msob.jima.core.commons.element.BaseElementAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Abstract base class for ChildDomain entities.
 *
 * @param <ID> the type of the identifier, which must be comparable and serializable
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseChildDomainAbstract<ID extends Comparable<ID> & Serializable> extends BaseDomainAbstract<ID> implements BaseChildDomain<ID> {

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
    protected BaseChildDomainAbstract(ID id, ID parentId) {
        super(id);
        this.parentId = parentId;
    }

    /**
     * Enum representing the field names of the `BaseChildDomainAbstract` class.
     */
    public enum FN {
        /**
         * Represents the parent domain ID field.
         */
        parentId
    }
}