package ir.msob.jima.core.commons.embeddeddomain.criteria;

import ir.msob.jima.core.commons.embeddeddomain.BaseEmbeddedDomain;
import ir.msob.jima.core.commons.filter.Filter;

import java.io.Serializable;

public interface BaseEmbeddedCriteriaType<ID extends Comparable<ID> & Serializable, CD extends BaseEmbeddedDomain<ID>> extends BaseEmbeddedCriteria<ID, CD> {

    Filter<String> getType();

    void setType(Filter<String> type);
}