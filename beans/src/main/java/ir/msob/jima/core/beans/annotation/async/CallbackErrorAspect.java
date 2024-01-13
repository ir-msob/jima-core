package ir.msob.jima.core.beans.annotation.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.annotation.async.CallbackError;
import ir.msob.jima.core.commons.annotation.methodstats.MethodStatsLogger;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Parameter;
import java.util.Optional;

/**
 * An aspect for handling errors and executing callbacks using annotations.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class CallbackErrorAspect {
    private static final Logger log = LoggerFactory.getLog(MethodStatsLogger.class);
    private final BaseAsyncClient asyncClient;
    private final ObjectMapper objectMapper;

    /**
     * Handle errors and execute callbacks for methods annotated with @CallbackError.
     *
     * @param point    The join point of the method
     * @param throwing The thrown exception
     * @throws Throwable If an error occurs during handling
     */
    @AfterThrowing(value = "@annotation(ir.msob.jima.core.commons.annotation.async.CallbackError)", throwing = "throwing")
    public void afterThrowing(JoinPoint point, Throwable throwing) throws Throwable {
        callback(point, throwing);
        log.error(throwing);
    }

    /**
     * Execute callbacks and handle errors based on the @CallbackError annotation.
     *
     * @param point The join point of the method
     * @param e     The thrown exception
     * @throws Throwable If an error occurs during handling
     */
    private <ID extends Comparable<ID> & Serializable
            , USER extends BaseUser<ID>
            , DATA extends ModelType> void callback(JoinPoint point, Throwable e) throws Throwable {
        ChannelMessage<ID, USER, DATA> message = prepareChannelMessage(point);
        Optional<USER> user = Optional.ofNullable(message.getUser());

        ChannelMessage<ID, USER, DATA> errorChannelMessage = prepareErrorChannelMessage(message, e);
        if (Strings.isNotBlank(message.getErrorCallback()))
            asyncClient.send(errorChannelMessage, message.getErrorCallback(), user);
    }

    /**
     * Prepare a ChannelMessage object from the method parameters.
     *
     * @param point The join point of the method
     * @return A ChannelMessage object
     * @throws JsonProcessingException If there's an issue with JSON processing
     */
    private <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>, DATA extends ModelType> ChannelMessage<ID, USER, DATA> prepareChannelMessage(JoinPoint point) throws JsonProcessingException {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        CallbackError callbackError = methodSignature.getMethod().getAnnotation(CallbackError.class);

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getName().equals(callbackError.value())) {
                Object paramValue = point.getArgs()[i];
                if (paramValue instanceof String dto) {
                    return objectMapper.readValue(dto, ChannelMessage.class);
                } else if (paramValue instanceof ChannelMessage channelMessage) {
                    return channelMessage;
                }
            }
        }
        throw new CommonRuntimeException("Can not detect ChannelMessage arg. Class {}, Method {}"
                , point.getSignature().getDeclaringTypeName()
                , ((MethodSignature) point.getSignature()).getMethod().getName());
    }

    /**
     * Prepare an error ChannelMessage based on an exception.
     *
     * @param channelMessageReq The original ChannelMessage
     * @param throwable         The thrown exception
     * @return An error ChannelMessage
     * @throws Throwable If there's an issue with error handling
     */
    private <ID extends Comparable<ID> & Serializable
            , USER extends BaseUser<ID>
            , DATA_REQ extends ModelType
            , DATA extends ModelType
            , ER extends AbstractExceptionResponse> ChannelMessage<ID, USER, DATA> prepareErrorChannelMessage(ChannelMessage<ID, USER, DATA_REQ> channelMessageReq, Throwable throwable) throws Throwable {
        ER er = ExceptionUtil.cast(throwable);
        if (er == null)
            throw throwable;

        ChannelMessage<ID, USER, DATA> channelMessage = new ChannelMessage<>();
        channelMessage.setData((DATA) er);
        channelMessage.setCallback(null);
        channelMessage.setMetadata(channelMessageReq.getMetadata());
        channelMessage.setStatus(er.getStatus());
        return channelMessage;
    }
}

