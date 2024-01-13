package ir.msob.jima.core.api.rsocket.commons;

import org.springframework.messaging.rsocket.RSocketRequester;

public interface BaseRSocketRequesterBuilder {
    RSocketRequester.Builder getBuilder(Object... candidateHandlers);

}
