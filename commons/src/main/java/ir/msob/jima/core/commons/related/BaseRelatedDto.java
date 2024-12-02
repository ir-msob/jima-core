package ir.msob.jima.core.commons.related;

import ir.msob.jima.core.commons.dto.BaseDto;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;


public interface BaseRelatedDto<ID extends Comparable<ID> & Serializable> extends BaseDto<ID> {

    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRelatedDto.class, 0);
    }
}
