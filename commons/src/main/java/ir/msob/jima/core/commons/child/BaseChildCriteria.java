package ir.msob.jima.core.commons.child;

import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;


public interface BaseChildCriteria<ID extends Comparable<ID> & Serializable, RM extends BaseChild<ID>> extends ir.msob.jima.core.commons.criteria.BaseChildCriteria<ID>, BaseFilters {

    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseChildCriteria.class, 0);
    }

    boolean isMatching(RM relatedModel);

}
