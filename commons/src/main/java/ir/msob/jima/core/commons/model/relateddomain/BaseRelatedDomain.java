package ir.msob.jima.core.commons.model.relateddomain;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedDomain' interface defines the basic structure for a related domain in the application.
 * It is a generic interface where 'ID' is a type parameter that extends 'Comparable' and 'Serializable'.
 * The interface includes a 'getRelatedDomains' method that returns a sorted set of related domains.
 * The interface also includes a 'setRelatedDomains' method that sets the sorted set of related domains.
 *
 *
 * @param <ID> the type of the identifier of the related domain. It must be comparable and serializable.
 */
public interface BaseRelatedDomain<ID extends Comparable<ID> & Serializable> {

    /**
     * Gets the sorted set of related domains.
     *
     * @return the sorted set of related domains.
     */
    SortedSet<RelatedDomain<ID>> getRelatedDomains();

    /**
     * Sets the sorted set of related domains.
     *
     * @param relatedDomains the sorted set of related domains to set.
     */
    void setRelatedDomains(SortedSet<RelatedDomain<ID>> relatedDomains);

}