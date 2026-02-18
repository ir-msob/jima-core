package ir.msob.jima.core.commons.embeddeddomain.relatedobject.relatedparty;


import java.io.Serializable;
import java.util.SortedSet;

public interface BaseRelatedPartyContainer<ID extends Comparable<ID> & Serializable, RD extends RelatedPartyAbstract<ID>> {
    SortedSet<RD> getRelatedParties();

    void setRelatedParties(SortedSet<RD> relatedParties);
}
