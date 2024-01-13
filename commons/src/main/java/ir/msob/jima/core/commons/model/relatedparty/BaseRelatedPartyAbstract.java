package ir.msob.jima.core.commons.model.relatedparty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedPartyAbstract<ID extends Comparable<ID> & Serializable> implements BaseRelatedParty<ID> {
    @Valid
    private SortedSet<RelatedParty<ID>> relatedParties = new TreeSet<>();

}
