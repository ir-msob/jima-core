package ir.msob.jima.core.commons.related;

import ir.msob.jima.core.commons.criteria.BaseChildCriteria;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.util.GenericTypeUtil;

import java.io.Serializable;


public interface BaseRelatedModelCriteria<ID extends Comparable<ID> & Serializable, RM extends BaseRelatedModel<ID>> extends BaseChildCriteria<ID>, BaseFilters {

    /**
     * Get the class type for the identifier (e.g., entity primary key) used in domain entities.
     *
     * @return The class type for the identifier.
     */
    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass() {
        return (Class<ID>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseRelatedModelCriteria.class, 0);
    }

    boolean isMatching(RM relatedModel);

}
