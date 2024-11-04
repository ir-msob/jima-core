package ir.msob.jima.core.api.rsocket.commons;

import org.springframework.messaging.rsocket.RSocketRequester;

/**
 * Interface for building RSocketRequester instances.
 * Provides a method to obtain a configured {@link RSocketRequester.Builder}.
 */
public interface BaseRSocketRequesterBuilder {

    /**
     * Returns a configured {@link RSocketRequester.Builder} instance.
     * 
     * @param candidateHandlers optional handlers that can be used during the building process
     * @return a configured RSocketRequester.Builder
     */
    RSocketRequester.Builder getBuilder(Object... candidateHandlers);
}
