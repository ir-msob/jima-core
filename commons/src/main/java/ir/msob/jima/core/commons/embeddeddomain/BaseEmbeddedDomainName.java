package ir.msob.jima.core.commons.embeddeddomain;

import java.io.Serializable;


public interface BaseEmbeddedDomainName<ID extends Comparable<ID> & Serializable> extends BaseEmbeddedDomain<ID> {

    String getName();

    void setName(String name);
}