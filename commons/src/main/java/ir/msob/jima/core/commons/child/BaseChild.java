package ir.msob.jima.core.commons.child;

import ir.msob.jima.core.commons.element.BaseElement;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;


public interface BaseChild<ID extends Comparable<ID> & Serializable> extends BaseElement<ID> {

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
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseChild.class, 0);
    }


}
