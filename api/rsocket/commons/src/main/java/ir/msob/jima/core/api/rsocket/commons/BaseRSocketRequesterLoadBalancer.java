package ir.msob.jima.core.api.rsocket.commons;

import org.springframework.messaging.rsocket.RSocketRequester;

/**
 * Interface for load balancing RSocketRequester instances.
 * Provides a method to obtain a {@link RSocketRequester} for a given application name.
 */
public interface BaseRSocketRequesterLoadBalancer {

    /**
     * Returns a {@link RSocketRequester} for the specified application name.
     * This method may use load balancing strategies to select the appropriate requester.
     *
     * @param applicationName   the name of the application for which the requester is needed
     * @param candidateHandlers optional handlers that can be used during the requester selection process
     * @return a configured RSocketRequester for the specified application
     */
    RSocketRequester getRequester(String applicationName, Object... candidateHandlers);
}
