package ir.msob.jima.core.api.kafka.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.callback.CallbackError;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.dto.ModelType;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import ir.msob.jima.core.commons.exception.BaseExceptionMapper;
import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeResponse;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import ir.msob.jima.core.commons.security.BaseUser;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.List;

/**
 * The 'CallbackErrorAspect' class is an aspect that handles errors and executes callbacks for methods annotated with @CallbackError.
 * It is annotated with '@Aspect' and '@Component', indicating that it is a Spring-managed aspect bean.
 * It uses the '@RequiredArgsConstructor' annotation from Lombok to automatically generate a constructor with required arguments.
 * The class provides methods to handle errors and execute callbacks for methods annotated with @CallbackError.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class CallbackErrorAspect {
    private static final Logger log = LoggerFactory.getLog(CallbackErrorAspect.class);
    private final BaseAsyncClient asyncClient;
    private final ObjectMapper objectMapper;
    private final BaseExceptionMapper exceptionMapper;

    /**
     * This method is executed after a method annotated with @CallbackError throws an exception.
     * It handles the error and executes the callback.
     *
     * @param point    The join point of the method
     * @param throwing The thrown exception
     */
    @AfterThrowing(value = "@annotation(ir.msob.jima.core.commons.annotation.async.CallbackError)", throwing = "throwing")
    public void afterThrowing(JoinPoint point, Throwable throwing) throws JsonProcessingException {
        callback(point, throwing);
        log.error(throwing);
    }

    /**
     * This method executes callbacks and handles errors based on the @CallbackError annotation.
     *
     * @param point The join point of the method
     * @param e     The thrown exception
     */
    private <USER extends BaseUser, DATA extends ModelType> void callback(JoinPoint point, Throwable e) throws JsonProcessingException {
        ChannelMessage<USER, DATA> message = prepareChannelMessage(point);
        USER user = message.getUser();

        List<? extends ChannelMessage<USER, ? extends AbstractExceptionResponse>> errorChannelMessages = prepareErrorChannelMessage(message, e);
        if (!message.getErrorCallbacks().isEmpty()) {
            for (ChannelMessage<USER, ? extends AbstractExceptionResponse> errorChannelMessage : errorChannelMessages) {
                asyncClient.send(errorChannelMessage, errorChannelMessage.getChannel(), user);
            }
        }
    }

    /**
     * This method prepares a ChannelMessage object from the method parameters.
     *
     * @param point The join point of the method
     * @return A ChannelMessage object
     * @throws JsonProcessingException If there's an issue with JSON processing
     */
    private <USER extends BaseUser, DATA extends ModelType> ChannelMessage<USER, DATA> prepareChannelMessage(JoinPoint point) throws JsonProcessingException {
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
        throw new CommonRuntimeException("Cannot detect ChannelMessage arg. Class {}, Method {}",
                point.getSignature().getDeclaringTypeName(),
                methodSignature.getMethod().getName());
    }

    /**
     * This method prepares an error ChannelMessage based on an exception.
     *
     * @param channelMessageReq The original ChannelMessage
     * @param throwable         The thrown exception
     * @return An error ChannelMessage
     */
    <USER extends BaseUser, DATA_REQ extends ModelType, ER extends AbstractExceptionResponse> List<ChannelMessage<USER, ER>> prepareErrorChannelMessage(ChannelMessage<USER, DATA_REQ> channelMessageReq, Throwable throwable) {
        ER er;
        if (throwable instanceof BaseRuntimeException e) {
            er = exceptionMapper.getExceptionResponse(e);
        } else {
            er = (ER) new CommonRuntimeResponse(throwable.getMessage());
        }

        return channelMessageReq.getErrorCallbacks()
                .stream()
                .map(channelMessage -> {
                    // Clone the channelMessage to avoid modifying the original
                    ChannelMessage<USER, ER> clonedMessage = new ChannelMessage<>();
                    clonedMessage.setData(er);
                    clonedMessage.setStatus(er.getStatus());
                    clonedMessage.setChannel(channelMessage.getChannel());
                    clonedMessage.setMetadata(channelMessage.getMetadata());
                    return clonedMessage;
                })
                .toList();
    }
}