package ir.msob.jima.core.commons.model.relatedparty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This abstract class represents a base related party with a sorted set of related parties.
 * It provides methods to get and set the related parties.
 * It implements the BaseRelatedParty interface.
 *
 * @param <ID> the type of the related party ID, which must be comparable and serializable
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedPartyAbstract<ID extends Comparable<ID> & Serializable> implements BaseRelatedParty<ID> {

    /**
     * A sorted set of related parties.
     */
    @Valid
    private SortedSet<RelatedParty<ID>> relatedParties = new TreeSet<>();

}