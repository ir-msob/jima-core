package ir.msob.jima.core.api.kafka.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for {@link KafkaAsyncClient}.
 * This class contains unit tests for testing the functionality of KafkaAsyncClient.
 */
class KafkaAsyncClientTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private ObjectMapper objectMapper;

    private KafkaAsyncClient kafkaAsyncClient;

    /**
     * Sets up the test environment before each test method.
     * Initializes mocks and the KafkaAsyncClient instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kafkaAsyncClient = new KafkaAsyncClient(kafkaTemplate, objectMapper);
    }

    /**
     * Tests the send method of KafkaAsyncClient with a ChannelMessage.
     *
     * @throws Exception if an error occurs during the test execution.
     */
    @Test
    void testSendChannelMessage() throws Exception {
        // Prepare test data
        ChannelMessage<BaseUser, ModelType> channelMessage = ChannelMessage.builder().build();
        String channel = "testChannel";

        // Mock behavior
        Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenReturn("serializedMessage");

        // Call the method under test
        kafkaAsyncClient.send(channelMessage, channel, new BaseUser());

        // Verify that the KafkaTemplate's send method is called with the expected arguments
        Mockito.verify(kafkaTemplate).send(Mockito.any(), Mockito.any());
    }

    /**
     * Tests the send method of KafkaAsyncClient with a Map message.
     *
     * @throws Exception if an error occurs during the test execution.
     */
    @Test
    void testSendMapMessage() throws Exception {
        // Prepare test data
        Map<String, Object> channelMessage = new HashMap<>();
        channelMessage.put("key", "value");

        String channel = "testChannel";

        // Mock behavior
        Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenReturn("serializedMessage");

        // Call the method under test
        kafkaAsyncClient.send(channelMessage, channel, new BaseUser());

        // Verify that the KafkaTemplate's send method is called with the expected arguments
        Mockito.verify(kafkaTemplate).send(Mockito.any(), Mockito.any());
    }
}
