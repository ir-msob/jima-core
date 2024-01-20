package ir.msob.jima.core.api.restful.beans.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.exception.AbstractExceptionResponse;
import ir.msob.jima.core.commons.exception.BaseExceptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * The `RestWebExceptionHandler` component is responsible for handling exceptions in a web application and converting them into JSON responses.
 * It implements the `WebExceptionHandler` interface and is ordered to run before other error handling components.
 * <p>
 * Author: Yaqub Abdi
 */
@Component
@Order(-2)
@CommonsLog
@RequiredArgsConstructor
public class RestWebExceptionHandler extends DefaultErrorAttributes implements WebExceptionHandler {

    // ObjectMapper is used for converting Java objects into their JSON representation
    private final ObjectMapper objectMapper;
    // BaseExceptionMapper is used for mapping exceptions to AbstractExceptionResponse objects
    private final BaseExceptionMapper exceptionMapper;

    /**
     * Handle the exception and convert it into a JSON response.
     *
     * @param exchange The ServerWebExchange object representing the current web request and response.
     * @param ex       The thrown exception to be handled.
     * @return A Mono representing the handling of the exception.
     */
    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // Set the content type of the response to be JSON
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Try to cast the exception to an AbstractExceptionResponse.
        AbstractExceptionResponse response = exceptionMapper.getExceptionResponse(ex);

        if (response == null) {
            // If the exception cannot be cast, propagate it.
            throw ex;
        }

        // Resolve the HTTP status based on the exception response.
        HttpStatus httpStatus = HttpStatus.resolve(response.getStatus());
        exchange.getResponse().setStatusCode(httpStatus);

        try {
            // Serialize the exception response to JSON.
            DataBuffer db = new DefaultDataBufferFactory().wrap(objectMapper.writeValueAsBytes(response));
            // Write the serialized JSON to the response body
            return exchange.getResponse().writeWith(Mono.just(db));
        } catch (JsonProcessingException e) {
            // Log the error if the exception response cannot be serialized to JSON
            log.error(e);
        }

        // Return an empty Mono if the exception was handled successfully
        return Mono.empty();
    }
}