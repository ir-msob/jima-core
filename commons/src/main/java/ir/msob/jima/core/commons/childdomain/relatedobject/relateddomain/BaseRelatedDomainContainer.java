package ir.msob.jima.core.commons.childdomain.relatedobject.relateddomain;

import java.io.Serializable;
import java.util.SortedSet;

public interface BaseRelatedDomainContainer<ID extends Comparable<ID> & Serializable, RD extends RelatedDomainAbstract<ID>> {
    SortedSet<RD> getRelatedDomains();

    void setRelatedDomains(SortedSet<RD> relatedDomains);
}
