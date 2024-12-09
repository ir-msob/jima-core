package ir.msob.jima.core.commons.child.relatedobject.relatedparty;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This abstract class represents a base child party with a sorted set of child parties.
 * It provides methods to get and set the child parties.
 * It implements the BaseRelatedPartyDto interface.
 *
 * @param <RP> the type of the child party, which must extend RelatedPartyAbstract.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedPartyDtoAbstract<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>> implements BaseRelatedPartyDto<ID, RP> {

    /**
     * A sorted set of child parties.
     */
    @Valid
    private SortedSet<RP> relatedParties = new TreeSet<>();

}