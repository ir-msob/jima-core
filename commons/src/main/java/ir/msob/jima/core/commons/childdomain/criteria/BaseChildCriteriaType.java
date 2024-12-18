package ir.msob.jima.core.commons.childdomain.criteria;

import ir.msob.jima.core.commons.childdomain.BaseChildDomain;
import ir.msob.jima.core.commons.filter.Filter;

import java.io.Serializable;

public interface BaseChildCriteriaType<ID extends Comparable<ID> & Serializable, CD extends BaseChildDomain<ID>> extends BaseChildCriteria<ID, CD> {

    Filter<String> getType();

    void setType(Filter<String> type);
}