package ir.msob.jima.core.commons.relatedobject.relatedintegration;

import java.util.SortedSet;

/**
 * The 'BaseRelatedIntegration' interface defines the basic structure for a related integration in the application.
 * It is a generic interface where 'RI' is a type parameter that extends 'RelatedIntegration'.
 * The interface includes a method to get and set a sorted set of related integrations.
 *
 * @param <RI> the type of the related integration, which must extend RelatedIntegration.
 */
public interface BaseRelatedIntegration<RI extends RelatedIntegration> {

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