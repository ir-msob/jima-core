package ir.msob.jima.core.commons.data;

import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.core.commons.security.BaseUser;

import java.io.Serializable;

public class SampleRepository<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>, D extends BaseDomain<ID>> implements BaseRepository<ID, USER, D> {
}
