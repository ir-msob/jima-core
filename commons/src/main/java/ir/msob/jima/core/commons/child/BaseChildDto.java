package ir.msob.jima.core.commons.child;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;


public interface BaseChildDto<ID extends Comparable<ID> & Serializable> extends BaseChild<ID> {


    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseChildDto.class, 0);
    }

    /**
     * Returns the simple name of the class of the DTO.
     *
     * @return The simple name of the class.
     */
    @JsonProperty(Constants.TYPE_PROPERTY_NAME)
    default String getClassType() {
        return this.getClass().getSimpleName();
    }
}
