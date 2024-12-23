package ir.msob.jima.core.api.kafka.beans;

import ir.msob.jima.core.commons.channel.BaseChannelMessage;
import ir.msob.jima.core.commons.exception.BaseExceptionMapper;
import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import ir.msob.jima.core.commons.exception.ExceptionResponseAbstract;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeResponse;
import ir.msob.jima.core.commons.security.BaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CallbackErrorAspectTest {

    @InjectMocks
    private CallbackErrorAspect callbackErrorAspect;

    @Mock
    private BaseExceptionMapper exceptionMapper;

    @Mock
    private BaseChannelMessage<BaseUser, ? extends ExceptionResponseAbstract> channelMessageReq;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrepareErrorChannelMessage_WithBaseRuntimeException() {
        BaseRuntimeException exception = new BaseRuntimeException("Test exception");
        CommonRuntimeResponse response = new CommonRuntimeResponse("Error occurred");

        when(exceptionMapper.getExceptionResponse(exception)).thenReturn(response);
        when(channelMessageReq.getErrorCallbacks()).thenReturn(Collections.singletonList(BaseChannelMessage.<BaseUser, ExceptionResponseAbstract>builder().build()));

        List<? extends BaseChannelMessage<BaseUser, ? extends ExceptionResponseAbstract>> result = callbackErrorAspect.prepareErrorChannelMessage(channelMessageReq, exception);

        assertEquals(1, result.size());
        assertEquals(response, result.getFirst().getData());
    }

    @Test
    void testPrepareErrorChannelMessage_WithOtherException() {
        CommonRuntimeException exception = new CommonRuntimeException("General exception");
        CommonRuntimeResponse response = new CommonRuntimeResponse("Error occurred");

        when(exceptionMapper.getExceptionResponse(exception)).thenReturn(response);
        when(channelMessageReq.getErrorCallbacks()).thenReturn(Collections.singletonList(BaseChannelMessage.<BaseUser, ExceptionResponseAbstract>builder().build()));

        List<BaseChannelMessage<BaseUser, ExceptionResponseAbstract>> result = callbackErrorAspect.prepareErrorChannelMessage(channelMessageReq, exception);

        assertEquals(1, result.size());
        assertEquals(response, result.get(0).getData());
    }
}