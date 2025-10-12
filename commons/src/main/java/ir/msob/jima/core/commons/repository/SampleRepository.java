package ir.msob.jima.core.commons.repository;

import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * The 'SampleRepository' class is an implementation of the 'BaseRepository' interface.
 * It provides a concrete implementation for a repository that works with domain entities and is typically used for data access in Spring-based applications.
 * The class is parameterized with the type of the identifier (usually an entity's primary key), the type representing a user, and the type representing a domain entity.
 *
 * @param <ID> The type of the identifier (usually an entity's primary key) which is both comparable and serializable.
 * @param <D>  The type representing a domain entity, typically derived from 'BaseDomain'.
 */
public class SampleRepository<ID extends Comparable<ID> & Serializable, D extends BaseDomain<ID>> implements BaseRepository<ID, D> {
    @Override
    public BaseQueryBuilder getQueryBuilder() {
        return new BaseQueryBuilder() {
            @Override
            public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria) {
                return null;
            }

            @Override
            public <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>, Q extends BaseQuery> Q build(C criteria, Pageable pageable) {
                return null;
            }
        };
    }
}