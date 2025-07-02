package ir.msob.jima.core.commons.childdomain.relatedaction;

import java.io.Serializable;
import java.util.SortedSet;

public interface BaseRelatedActionContainer<ID extends Comparable<ID> & Serializable, RA extends RelatedActionAbstract<ID>> {
    SortedSet<RA> getRelatedActions();

    void setRelatedActions(SortedSet<RA> relatedActions);
}
