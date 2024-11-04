package ir.msob.jima.core.api.rsocket.commons;

import org.springframework.messaging.rsocket.RSocketRequester;

/**
 * Interface for setting metadata on RSocketRequester instances.
 * Provides a method to configure metadata using a {@link RSocketRequester.MetadataSpec}.
 */
public interface BaseRSocketRequesterMetadata {

    /**
     * Configures metadata for an RSocketRequester using the provided {@link RSocketRequester.MetadataSpec}.
     *
     * @param metadataSpec the metadata specification to be applied
     */
    void metadata(RSocketRequester.MetadataSpec<?> metadataSpec);
}
