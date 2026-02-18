package ir.msob.jima.core.commons.embeddeddomain;

import java.io.Serializable;


public interface BaseEmbeddedDomainKey<ID extends Comparable<ID> & Serializable> extends BaseEmbeddedDomain<ID> {

    String getKey();

    void setKey(String key);
}