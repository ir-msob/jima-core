package ir.msob.jima.core.commons.child.relatedobject.relateddomain;

import ir.msob.jima.core.commons.child.relatedobject.BaseRelatedObjectDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedDomainDto' interface defines the basic structure for a child domain in the application.
 * It is a generic interface where 'ID' is a type parameter that extends 'Comparable' and 'Serializable'.
 * The interface includes a 'getRelatedDomains' method that returns a sorted set of child domains.
 * The interface also includes a 'setRelatedDomains' method that sets the sorted set of child domains.
 *
 * @param <ID> the type of the identifier of the child domain. It must be comparable and serializable.
 * @param <RD> the type of the child domain, which must extend RelatedDomainAbstract<ID>.
 */
public interface BaseRelatedDomainDto<ID extends Comparable<ID> & Serializable, RD extends RelatedDomainAbstract<ID>> extends BaseRelatedObjectDto<ID> {

    /**
     * Gets the sorted set of child domains.
     *
     * @return the sorted set of child domains.
     */
    SortedSet<RD> getRelatedDomains();

    /**
     * Sets the sorted set of child domains.
     *
     * @param relatedDomains the sorted set of child domains to set.
     */
    void setRelatedDomains(SortedSet<RD> relatedDomains);

}