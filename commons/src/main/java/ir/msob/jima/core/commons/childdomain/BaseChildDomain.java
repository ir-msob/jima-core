package ir.msob.jima.core.commons.childdomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.element.BaseElement;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;

/**
 * Interface representing a base child domain entity.
 *
 * @param <ID> the type of the identifier, which must be comparable and serializable
 */
public interface BaseChildDomain<ID extends Comparable<ID> & Serializable> extends BaseDomain<ID> {

    /**
     * Returns the parent domain ID of the model.
     *
     * @return The parent domain ID.
     */
    ID getParentId();

    /**
     * Sets the parent domain ID of the model.
     *
     * @param id The parent domain ID to be set.
     */
    void setParentId(ID id);

    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    @JsonIgnore
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseChildDomain.class, 0);
    }
}