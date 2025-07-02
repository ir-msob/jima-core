package ir.msob.jima.core.commons.childdomain.relatedobject.relatedintegration;


import java.io.Serializable;
import java.util.SortedSet;

public interface BaseRelatedIntegrationContainer<ID extends Comparable<ID> & Serializable, RI extends RelatedIntegrationAbstract<ID>> {
    SortedSet<RI> getRelatedIntegrations();

    void setRelatedIntegrations(SortedSet<RI> relatedIntegrations);
}
