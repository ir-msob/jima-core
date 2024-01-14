package ir.msob.jima.core.commons.model.relatedparty;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * This interface represents a related party with a set of related parties.
 * It provides methods to get and set the related parties.
 *
 * @param <ID> the type of the related party ID, which must be comparable and serializable
 *
 */
public interface BaseRelatedParty<ID extends Comparable<ID> & Serializable> {

    /**
     * Returns a sorted set of related parties.
     *
     * @return a sorted set of related parties
     */
    SortedSet<RelatedParty<ID>> getRelatedParties();

    /**
     * Sets the related parties with the provided sorted set.
     *
     * @param relatedParties a sorted set of related parties
     */
    void setRelatedParties(SortedSet<RelatedParty<ID>> relatedParties);

}