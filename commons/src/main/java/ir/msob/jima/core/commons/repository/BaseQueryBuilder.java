package ir.msob.jima.core.commons.repository;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface BaseQueryBuilder {

    <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> BaseQuery build(C criteria);

    <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> BaseQuery build(C criteria, Pageable pageable);
}
