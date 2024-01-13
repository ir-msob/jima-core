package ir.msob.jima.core.commons.model.relateddomain;

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
public abstract class BaseRelatedDomainAbstract<ID extends Comparable<ID> & Serializable> implements BaseRelatedDomain<ID> {
    @Valid
    private SortedSet<RelatedDomain<ID>> relatedDomains = new TreeSet<>();

}
