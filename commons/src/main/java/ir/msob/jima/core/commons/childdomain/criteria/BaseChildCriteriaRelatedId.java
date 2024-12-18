package ir.msob.jima.core.commons.childdomain.criteria;

import ir.msob.jima.core.commons.childdomain.BaseChildDomain;
import ir.msob.jima.core.commons.filter.Filter;

import java.io.Serializable;

public interface BaseChildCriteriaRelatedId<ID extends Comparable<ID> & Serializable, RID extends Comparable<RID> & Serializable, CD extends BaseChildDomain<ID>> extends BaseChildCriteria<ID, CD> {

    Filter<RID> getRelatedId();

    void setRelatedId(Filter<RID> relatedId);
}