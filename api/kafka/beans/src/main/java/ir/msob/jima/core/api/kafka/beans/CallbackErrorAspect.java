package ir.msob.jima.core.api.kafka.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.callback.CallbackError;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.exception.BaseExceptionMapper;
import ir.msob.jima.core.commons.exception.BaseRuntimeException;
import ir.msob.jima.core.commons.exception.ExceptionResponseAbstract;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeResponse;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.ModelType;
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
    @AfterThrowing(value = "@annotation(ir.msob.jima.core.commons.callback.CallbackError)", throwing = "throwing")
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

        List<? extends ChannelMessage<USER, ? extends ExceptionResponseAbstract>> errorChannelMessages = prepareErrorChannelMessage(message, e);
        if (!message.getErrorCallbacks().isEmpty()) {
            for (ChannelMessage<USER, ? extends ExceptionResponseAbstract> errorChannelMessage : errorChannelMessages) {

                asyncClient.send(errorChannelMessage.getChannel(), errorChannelMessage, user);
            }
        }
    }

    /**
     * Prepares a ChannelMessage object from the method parameters.
     *
     * @param point The join point of the method.
     * @return A ChannelMessage object.
     * @throws JsonProcessingException If there's an issue with JSON processing.
     */
    private <USER extends BaseUser, DATA extends ModelType> ChannelMessage<USER, DATA> prepareChannelMessage(JoinPoint point) throws JsonProcessingException {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        CallbackError callbackError = methodSignature.getMethod().getAnnotation(CallbackError.class);
        Object paramValue = getParamValue(parameters, callbackError, methodSignature, point);
        return convertToChannelMessage(paramValue);
    }

    /**
     * Retrieves the parameter value based on the CallbackError annotation.
     *
     * @param parameters      The method parameters.
     * @param callbackError   The CallbackError annotation.
     * @param methodSignature The method signature.
     * @param point           The join point of the method.
     * @return The parameter value.
     * @throws CommonRuntimeException If the ChannelMessage argument cannot be detected.
     */
    private Object getParamValue(Parameter[] parameters, CallbackError callbackError, MethodSignature methodSignature, JoinPoint point) {
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getName().equals(callbackError.value())) {
                return point.getArgs()[i];
            }
        }
        throw new CommonRuntimeException("Cannot detect ChannelMessage arg. Class {}, Method {}",
                point.getSignature().getDeclaringTypeName(),
                methodSignature.getMethod().getName());
    }

    /**
     * Converts the parameter value to a ChannelMessage object.
     *
     * @param paramValue The parameter value.
     * @return A ChannelMessage object.
     * @throws JsonProcessingException If there's an issue with JSON processing.
     * @throws CommonRuntimeException  If the value cannot be converted to a ChannelMessage.
     */
    private <USER extends BaseUser, DATA extends ModelType> ChannelMessage<USER, DATA> convertToChannelMessage(Object paramValue) throws JsonProcessingException {
        if (paramValue instanceof String dto) {
            TypeReference<ChannelMessage<USER, DATA>> typeRef = new TypeReference<>() {
            };
            return objectMapper.readValue(dto, typeRef);
        } else if (paramValue instanceof ChannelMessage<?, ?> channelMessage) {
            @SuppressWarnings("unchecked")
            ChannelMessage<USER, DATA> result = (ChannelMessage<USER, DATA>) channelMessage;
            return result;
        }
        throw new CommonRuntimeException("Cannot convert to ChannelMessage. Value: {}", paramValue);
    }

    /**
     * This method prepares an error ChannelMessage based on an exception.
     *
     * @param channelMessageReq The original ChannelMessage
     * @param throwable         The thrown exception
     * @return An error ChannelMessage
     */
    <USER extends BaseUser, DATA_REQ extends ModelType, ER extends ExceptionResponseAbstract> List<ChannelMessage<USER, ER>> prepareErrorChannelMessage(ChannelMessage<USER, DATA_REQ> channelMessageReq, Throwable throwable) {
        ER er;
        if (throwable instanceof BaseRuntimeException e) {
            er = exceptionMapper.getExceptionResponse(e);
        } else {
            er = (ER) new CommonRuntimeResponse(throwable.getMessage());
        }

        return channelMessageReq.getErrorCallbacks()
                .stream()
                .map(channelMessage ->
                        // Clone the channelMessage to avoid modifying the original
                        ChannelMessage.
                                <USER, ER>builder()
                                .key(channelMessage.getKey())
                                .data(er)
                                .status(er.getStatus())
                                .channel(channelMessage.getChannel())
                                .callbacks(channelMessage.getCallbacks())
                                .metadata(channelMessage.getMetadata())
                                .build())
                .toList();
    }
}