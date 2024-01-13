package ir.msob.jima.core.commons.service;

import ir.msob.jima.core.commons.data.BaseRepository;
import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;

public class SampleService<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>, D extends BaseDomain<ID>, R extends BaseRepository<ID, USER, D>> implements BaseService<ID, USER, D, R> {
}
