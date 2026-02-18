package ir.msob.jima.core.commons.embeddeddomain.criteria;

import ir.msob.jima.core.commons.embeddeddomain.BaseEmbeddedDomain;
import ir.msob.jima.core.commons.filter.Filter;

import java.io.Serializable;

public interface BaseEmbeddedCriteriaRelatedId<ID extends Comparable<ID> & Serializable, RID extends Comparable<RID> & Serializable, CD extends BaseEmbeddedDomain<ID>> extends BaseEmbeddedCriteria<ID, CD> {

    Filter<RID> getRelatedId();

    void setRelatedId(Filter<RID> relatedId);
}