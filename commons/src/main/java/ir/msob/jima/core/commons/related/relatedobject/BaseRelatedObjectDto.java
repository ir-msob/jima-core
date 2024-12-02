package ir.msob.jima.core.commons.related.relatedobject;

import ir.msob.jima.core.commons.related.BaseRelatedDto;

import java.io.Serializable;

public interface BaseRelatedObjectDto<ID extends Comparable<ID> & Serializable> extends BaseRelatedDto<ID> {

}