package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.scope.ServiceDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ScopeSenderService {
    private static final Logger logger = LoggerFactory.getLog(ScopeSenderService.class);

    private final ScopeScannerService scopeScannerService;
    private final BaseAsyncClient asyncClient;
    private final JimaProperties jimaProperties;
    private final String applicationName;


    public void send() {
        logger.info("Starting ScopeSenderService...");
        ChannelMessage<?, ServiceDto> channelMessage = prepareChannelMessage();
        asyncClient.send(channelMessage, jimaProperties.getScope().getChannel(), Optional.empty());
        logger.info("ScopeSenderService started successfully.");
    }

    private ChannelMessage<?, ServiceDto> prepareChannelMessage() {
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceName(applicationName)
                .resources(scopeScannerService.getResources())
                .build();

        ChannelMessage<?, ServiceDto> channelMessage = new ChannelMessage<>();
        channelMessage.setData(serviceDto);
        return channelMessage;
    }
}