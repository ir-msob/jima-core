package ir.msob.jima.core.commons.child.relatedobject.relatedintegration;

import ir.msob.jima.core.commons.child.relatedobject.BaseRelatedObjectDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedIntegrationDto' interface defines the basic structure for a child integration in the application.
 * It is a generic interface where 'RI' is a type parameter that extends 'RelatedIntegrationAbstract'.
 * The interface includes a method to get and set a sorted set of child integrations.
 *
 * @param <RI> the type of the child integration, which must extend RelatedIntegrationAbstract.
 */
public interface BaseRelatedIntegrationDto<ID extends Comparable<ID> & Serializable, RI extends RelatedIntegrationAbstract<ID>> extends BaseRelatedObjectDto<ID> {

    /**
     * Gets the sorted set of child integrations.
     *
     * @return the sorted set of child integrations.
     */
    SortedSet<RI> getRelatedIntegrations();

    /**
     * Sets the sorted set of child integrations.
     *
     * @param relatedIntegrations the sorted set of child integrations to set.
     */
    void setRelatedIntegrations(SortedSet<RI> relatedIntegrations);

}