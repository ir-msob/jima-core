package ir.msob.jima.core.commons.data;

import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;

/**
 * The 'SampleRepository' class is an implementation of the 'BaseRepository' interface.
 * It provides a concrete implementation for a repository that works with domain entities and is typically used for data access in Spring-based applications.
 * The class is parameterized with the type of the identifier (usually an entity's primary key), the type representing a user, and the type representing a domain entity.
 *
 * @param <ID>   The type of the identifier (usually an entity's primary key) which is both comparable and serializable.
 * @param <USER> The type representing a user, typically derived from 'BaseUser'.
 * @param <D>    The type representing a domain entity, typically derived from 'BaseDomain'.
 */
public class SampleRepository<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>, D extends BaseDomain<ID>> implements BaseRepository<ID, USER, D> {
}