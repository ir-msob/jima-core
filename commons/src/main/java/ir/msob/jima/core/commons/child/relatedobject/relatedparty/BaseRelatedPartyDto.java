package ir.msob.jima.core.commons.child.relatedobject.relatedparty;

import ir.msob.jima.core.commons.child.relatedobject.BaseRelatedObjectDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedPartyDto' interface represents a child party with a set of child parties.
 * It provides methods to get and set the child parties.
 *
 * @param <RP> the type of the child party, which must extend RelatedPartyAbstract.
 */
public interface BaseRelatedPartyDto<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>> extends BaseRelatedObjectDto<ID> {

    /**
     * Returns a sorted set of child parties.
     *
     * @return a sorted set of child parties
     */
    SortedSet<RP> getRelatedParties();

    /**
     * Sets the child parties with the provided sorted set.
     *
     * @param relatedParties a sorted set of child parties
     */
    void setRelatedParties(SortedSet<RP> relatedParties);

}