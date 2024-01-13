package ir.msob.jima.core.commons.model.relatedparty;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * @author Yaqub Abdi
 */
public interface BaseRelatedParty<ID extends Comparable<ID> & Serializable> {

    SortedSet<RelatedParty<ID>> getRelatedParties();

    void setRelatedParties(SortedSet<RelatedParty<ID>> relatedParties);

}
