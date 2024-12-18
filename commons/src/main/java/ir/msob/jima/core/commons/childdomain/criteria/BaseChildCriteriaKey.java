package ir.msob.jima.core.commons.childdomain.criteria;

import ir.msob.jima.core.commons.childdomain.BaseChildDomain;
import ir.msob.jima.core.commons.filter.Filter;

import java.io.Serializable;

public interface BaseChildCriteriaKey<ID extends Comparable<ID> & Serializable, CD extends BaseChildDomain<ID>> extends BaseChildCriteria<ID, CD> {

    Filter<String> getKey();

    void setKey(Filter<String> key);
}