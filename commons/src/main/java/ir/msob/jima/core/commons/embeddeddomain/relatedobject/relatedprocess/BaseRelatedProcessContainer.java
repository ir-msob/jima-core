package ir.msob.jima.core.commons.embeddeddomain.relatedobject.relatedprocess;


import java.io.Serializable;
import java.util.SortedSet;

public interface BaseRelatedProcessContainer<ID extends Comparable<ID> & Serializable, RD extends RelatedProcessAbstract<ID>> {
    SortedSet<RD> getRelatedProcesses();

    void setRelatedProcesses(SortedSet<RD> relatedProcesses);
}
