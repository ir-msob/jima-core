package ir.msob.jima.core.commons.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
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

        ChannelMessage<BaseUser, ModelType> channelMessageReq = new ChannelMessage<>();
        ModelType data = new ModelType();
        Integer status = 200;

        // Act
        ChannelMessage<BaseUser, ModelType> preparedChannelMessage = baseKafkaParentListener.prepareChannelMessage(channelMessageReq, data, status, new BaseUser());

        // Assert
        assertEquals(data, preparedChannelMessage.getData());
        assertNull(preparedChannelMessage.getCallback());
        assertEquals(channelMessageReq.getMetadata(), preparedChannelMessage.getMetadata());
        assertEquals(status, preparedChannelMessage.getStatus());
    }
}

