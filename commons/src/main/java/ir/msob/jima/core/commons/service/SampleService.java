package ir.msob.jima.core.commons.service;

import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.repository.BaseRepository;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;

/**
 * This class represents a sample service that implements the BaseService interface.
 * It is a generic class that takes four type parameters: ID, USER, D, and R.
 * ID is the type of the ID and must be comparable and serializable.
 * USER is the type of the user and must extend BaseUser.
 * D is the type of the domain and must extend BaseDomain.
 * R is the type of the repository and must extend BaseRepository.
 *
 * @param <ID>   the type of the ID, which must be comparable and serializable
 * @param <USER> the type of the user, which must extend BaseUser
 * @param <D>    the type of the domain, which must extend BaseDomain
 * @param <R>    the type of the repository, which must extend BaseRepository
 */
public class SampleService<ID extends Comparable<ID> & Serializable, USER extends BaseUser, D extends BaseDomain<ID>, R extends BaseRepository<ID, USER, D>> implements BaseService<ID, USER, D, R> {
}