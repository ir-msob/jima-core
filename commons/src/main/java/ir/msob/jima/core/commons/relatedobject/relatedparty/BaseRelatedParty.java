package ir.msob.jima.core.commons.relatedobject.relatedparty;

import java.util.SortedSet;

/**
 * The 'BaseRelatedParty' interface represents a related party with a set of related parties.
 * It provides methods to get and set the related parties.
 *
 * @param <RP> the type of the related party, which must extend RelatedParty.
 */
public interface BaseRelatedParty<RP extends RelatedParty> {

    /**
     * Returns a sorted set of related parties.
     *
     * @return a sorted set of related parties
     */
    SortedSet<RP> getRelatedParties();

    /**
     * Sets the related parties with the provided sorted set.
     *
     * @param relatedParties a sorted set of related parties
     */
    void setRelatedParties(SortedSet<RP> relatedParties);

}