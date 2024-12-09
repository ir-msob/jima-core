package ir.msob.jima.core.commons.child.relatedobject;

import ir.msob.jima.core.commons.child.BaseChildDto;

import java.io.Serializable;

public interface BaseRelatedObjectDto<ID extends Comparable<ID> & Serializable> extends BaseChildDto<ID> {

}