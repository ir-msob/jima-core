package ir.msob.jima.core.commons.related;

import ir.msob.jima.core.commons.dto.BaseChildDto;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;


public interface BaseRelatedModel<ID extends Comparable<ID> & Serializable> extends BaseChildDto<ID> {

    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRelatedModel.class, 0);
    }


}
