package ir.msob.jima.core.commons.embeddeddomain.criteria;

import ir.msob.jima.core.commons.embeddeddomain.BaseEmbeddedDomain;
import ir.msob.jima.core.commons.embeddeddomain.BaseEmbeddedDomainKey;
import ir.msob.jima.core.commons.filter.Filter;

import java.io.Serializable;

public interface BaseEmbeddedCriteriaKey<ID extends Comparable<ID> & Serializable, CD extends BaseEmbeddedDomainKey<ID>> extends BaseEmbeddedCriteria<ID, CD> {

    Filter<String> getKey();

    void setKey(Filter<String> key);
}