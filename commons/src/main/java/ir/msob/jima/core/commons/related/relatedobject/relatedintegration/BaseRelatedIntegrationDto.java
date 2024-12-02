package ir.msob.jima.core.commons.related.relatedobject.relatedintegration;

import ir.msob.jima.core.commons.related.relatedobject.BaseRelatedObjectDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedIntegrationDto' interface defines the basic structure for a related integration in the application.
 * It is a generic interface where 'RI' is a type parameter that extends 'RelatedIntegrationAbstract'.
 * The interface includes a method to get and set a sorted set of related integrations.
 *
 * @param <RI> the type of the related integration, which must extend RelatedIntegrationAbstract.
 */
public interface BaseRelatedIntegrationDto<ID extends Comparable<ID> & Serializable, RI extends RelatedIntegrationAbstract<ID>> extends BaseRelatedObjectDto<ID> {

    /**
     * Gets the sorted set of related integrations.
     *
     * @return the sorted set of related integrations.
     */
    SortedSet<RI> getRelatedIntegrations();

    /**
     * Sets the sorted set of related integrations.
     *
     * @param relatedIntegrations the sorted set of related integrations to set.
     */
    void setRelatedIntegrations(SortedSet<RI> relatedIntegrations);

}