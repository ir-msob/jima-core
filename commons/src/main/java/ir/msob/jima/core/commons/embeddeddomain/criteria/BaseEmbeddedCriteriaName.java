package ir.msob.jima.core.commons.embeddeddomain.criteria;

import ir.msob.jima.core.commons.embeddeddomain.BaseEmbeddedDomain;
import ir.msob.jima.core.commons.embeddeddomain.BaseEmbeddedDomainName;
import ir.msob.jima.core.commons.filter.Filter;

import java.io.Serializable;

public interface BaseEmbeddedCriteriaName<ID extends Comparable<ID> & Serializable, CD extends BaseEmbeddedDomainName<ID>> extends BaseEmbeddedCriteria<ID, CD> {

    Filter<String> getName();

    void setName(Filter<String> name);
}