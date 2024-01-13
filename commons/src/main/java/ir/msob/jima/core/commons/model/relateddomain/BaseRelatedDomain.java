package ir.msob.jima.core.commons.model.relateddomain;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * @author Yaqub Abdi
 */
public interface BaseRelatedDomain<ID extends Comparable<ID> & Serializable> {

    SortedSet<RelatedDomain<ID>> getRelatedDomains();

    void setRelatedDomains(SortedSet<RelatedDomain<ID>> relatedDomains);

}
