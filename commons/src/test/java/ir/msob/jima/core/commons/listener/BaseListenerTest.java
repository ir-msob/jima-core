package ir.msob.jima.core.commons.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.channel.BaseChannelMessage;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.resource.listener.BaseListener;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.core.commons.shared.ModelType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BaseListenerTest {

    @Test
    void testPrepareChannelMessage() {
        // Arrange
        BaseListener<String, BaseUser> baseKafkaParentListener = new BaseListener<>() {
            @Override
            public ObjectMapper getObjectMapper() {
                return null;
            }

            @Override
            public BaseUserService getUserService() {
                return null;
            }

            @Override
            public BaseAsyncClient getAsyncClient() {
                return null;
            }
        };

        BaseChannelMessage<BaseUser, ModelType> channelMessageReq = BaseChannelMessage.builder().build();
        ModelType data = new ModelType();
        Integer status = 200;

        // Act
        BaseChannelMessage<BaseUser, ModelType> preparedChannelMessage = baseKafkaParentListener.prepareChannelMessage(channelMessageReq, data, status, new BaseUser());

        // Assert
        assertEquals(data, preparedChannelMessage.getData());
        assertNull(preparedChannelMessage.getChannel());
        assertEquals(channelMessageReq.getMetadata(), preparedChannelMessage.getMetadata());
        assertEquals(status, preparedChannelMessage.getStatus());
    }
}

