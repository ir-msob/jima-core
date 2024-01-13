package ir.msob.jima.core.api.kafka.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class KafkaAsyncClientTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private ObjectMapper objectMapper;

    private KafkaAsyncClient kafkaAsyncClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kafkaAsyncClient = new KafkaAsyncClient(kafkaTemplate, objectMapper);
    }

    @Test
    void testSendChannelMessage() throws Exception {
        // Prepare test data
        ChannelMessage<Long, BaseUser<Long>, ModelType> channelMessage = new ChannelMessage<>();
        String channel = "testChannel";
        Optional<BaseUser<Long>> user = Optional.of(new BaseUser<>());

        // Mock behavior
        Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenReturn("serializedMessage");

        // Call the method under test
        kafkaAsyncClient.send(channelMessage, channel, user);

        // Verify that the KafkaTemplate's send method is called with the expected arguments
        Mockito.verify(kafkaTemplate).executeInTransaction(Mockito.any());
    }

    @Test
    void testSendMapMessage() throws Exception {
        // Prepare test data
        Map<String, Object> channelMessage = new HashMap<>();
        channelMessage.put("key", "value");

        String channel = "testChannel";
        Optional<BaseUser<Long>> user = Optional.of(new BaseUser<>());

        // Mock behavior
        Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenReturn("serializedMessage");

        // Call the method under test
        kafkaAsyncClient.send(channelMessage, channel, user);

        // Verify that the KafkaTemplate's send method is called with the expected arguments
        Mockito.verify(kafkaTemplate).executeInTransaction(Mockito.any());
    }
}
