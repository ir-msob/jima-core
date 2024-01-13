package ir.msob.jima.core.api.rsocket.commons;

import org.springframework.messaging.rsocket.RSocketRequester;

public interface BaseRSocketRequesterLoadBalancer {
    RSocketRequester getRequester(String applicationName, Object... candidateHandlers);

}
