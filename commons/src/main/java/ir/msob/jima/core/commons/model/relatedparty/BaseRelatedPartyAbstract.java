package ir.msob.jima.core.commons.model.relatedparty;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This abstract class represents a base related party with a sorted set of related parties.
 * It provides methods to get and set the related parties.
 * It implements the BaseRelatedParty interface.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedPartyAbstract implements BaseRelatedParty {

    /**
     * A sorted set of related parties.
     */
    @Valid
    private SortedSet<RelatedParty> relatedParties = new TreeSet<>();

}