package ir.msob.jima.core.api.rsocket.commons;

import org.springframework.messaging.rsocket.RSocketRequester;

public interface BaseRSocketRequesterMetadata {
    void metadata(RSocketRequester.MetadataSpec<?> metadataSpec);

}
