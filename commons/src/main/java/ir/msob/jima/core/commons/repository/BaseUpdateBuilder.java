package ir.msob.jima.core.commons.repository;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;

import java.io.Serializable;

public interface BaseUpdateBuilder {

    <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> BaseUpdate build(C criteria);

    <ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>> BaseUpdate build(D domain);
}
