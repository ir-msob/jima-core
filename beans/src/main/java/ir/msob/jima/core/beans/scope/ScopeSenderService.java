package ir.msob.jima.core.beans.scope;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import ir.msob.jima.core.commons.scope.ServiceDto;
import ir.msob.jima.core.commons.security.BaseUser;
import lombok.RequiredArgsConstructor;

/**
 * The ScopeSenderService class is responsible for sending scope-embeddeddomain messages
 * using an asynchronous client. It prepares a channel message containing service
 * details and sends it to a specified channel.
 */
@RequiredArgsConstructor
public class ScopeSenderService {
    private static final Logger logger = LoggerFactory.getLogger(ScopeSenderService.class);

    private final ScopeScannerService scopeScannerService;
    private final BaseAsyncClient asyncClient;
    private final JimaProperties jimaProperties;
    private final String applicationName;

    /**
     * Initiates the sending process of the scope message. It logs the start and
     * successful completion of the service.
     */
    public void send() throws JsonProcessingException {
        logger.info("Starting ScopeSenderService...");
        ChannelMessage<?, ServiceDto> channelMessage = prepareChannelMessage();
        asyncClient.send(jimaProperties.getScope().getChannel(), channelMessage);
        logger.info("ScopeSenderService started successfully.");
    }

    /**
     * Prepares a channel message containing the service details.
     *
     * @return a ChannelMessage object containing the service details.
     */
    private ChannelMessage<?, ServiceDto> prepareChannelMessage() {
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceName(applicationName)
                .resources(scopeScannerService.getResources())
                .build();

        ChannelMessage<BaseUser, ServiceDto> channelMessage = ChannelMessage.<BaseUser, ServiceDto>builder().build();
        channelMessage.setData(serviceDto);
        return channelMessage;
    }
}