package ir.msob.jima.core.commons.domain;

import java.io.Serializable;

/**
 * The 'BaseDomainId' interface represents a basic interface for domain models that need to generate new domain IDs.
 * It is a generic interface, with the generic type 'ID' extending 'Comparable' and 'Serializable'.
 * This means that the ID of the domain model can be of any type that is comparable and serializable.
 * The interface includes a 'newDomainId' method for generating new domain IDs.
 *
 * @param <ID> The type of the ID of the domain model.
 */
public interface BaseDomainId<ID extends Comparable<ID> & Serializable> {

    /**
     * Generates a new domain ID.
     *
     * @return The new domain ID.
     */
    ID newDomainId();

}