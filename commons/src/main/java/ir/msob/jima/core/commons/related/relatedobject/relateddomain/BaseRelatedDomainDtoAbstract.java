package ir.msob.jima.core.commons.related.relatedobject.relateddomain;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedDomainDtoAbstract' class provides a basic implementation of the 'BaseRelatedDomainDto' interface.
 * It is an abstract class where 'ID' is a type parameter that extends 'Comparable' and 'Serializable'.
 * The class includes a 'relatedDomains' field that holds a sorted set of related domains.
 * The class uses the Lombok library to automatically generate getter and setter methods for the 'relatedDomains' field.
 * The class also uses the Lombok library to automatically generate a 'toString' method and a no-argument constructor.
 * The 'toString' method includes a call to the superclass's 'toString' method.
 * The 'relatedDomains' field is annotated with '@Valid' to enable objectvalidation of the related domains.
 *
 * @param <ID> the type of the identifier of the related domain. It must be comparable and serializable.
 * @param <RD> the type of the related domain, which must extend RelatedDomainAbstract<ID>.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedDomainDtoAbstract<ID extends Comparable<ID> & Serializable, RD extends RelatedDomainAbstract<ID>> implements BaseRelatedDomainDto<ID, RD> {
    /**
     * A sorted set of related domains.
     * The set is initialized to an empty 'TreeSet'.
     */
    @Valid
    private SortedSet<RD> relatedDomains = new TreeSet<>();
}