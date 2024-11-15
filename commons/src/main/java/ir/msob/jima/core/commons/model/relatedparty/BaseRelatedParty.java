package ir.msob.jima.core.commons.model.relatedparty;

import java.util.SortedSet;

/**
 * This interface represents a related party with a set of related parties.
 * It provides methods to get and set the related parties.
 */
public interface BaseRelatedParty {

    /**
     * Returns a sorted set of related parties.
     *
     * @return a sorted set of related parties
     */
    SortedSet<RelatedParty> getRelatedParties();

    /**
     * Sets the related parties with the provided sorted set.
     *
     * @param relatedParties a sorted set of related parties
     */
    void setRelatedParties(SortedSet<RelatedParty> relatedParties);

}