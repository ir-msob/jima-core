package ir.msob.jima.core.commons.relatedobject.relatedparty;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This abstract class represents a base related party with a sorted set of related parties.
 * It provides methods to get and set the related parties.
 * It implements the BaseRelatedParty interface.
 *
 * @param <RP> the type of the related party, which must extend RelatedPartyAbstract.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedPartyAbstract<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>> implements BaseRelatedParty<ID, RP> {

    /**
     * A sorted set of related parties.
     */
    @Valid
    private SortedSet<RP> relatedParties = new TreeSet<>();

}