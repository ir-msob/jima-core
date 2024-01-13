package ir.msob.jima.core.api.restful.test;

import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.UserInfoUtil;
import ir.msob.jima.core.test.BaseCoreResourceTest;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.Serializable;

/**
 * The `BaseCoreRestResourceTest` interface serves as a foundation for testing RESTful resources in a Java application.
 * It extends the {@link BaseCoreResourceTest} interface and provides methods and utilities for setting up test headers,
 * including content and user information headers.
 *
 * @param <ID>   The type representing unique identifiers for entities, typically extending Comparable and Serializable.
 * @param <USER> The type representing a user entity often used for testing purposes.
 * @param <D>    The type representing the domain or entity that is being tested.
 * @param <DTO>  The type representing a Data Transfer Object (DTO) related to the domain being tested.
 * @param <C>    The type representing criteria for queries, often associated with user and entity filtering.
 */
public interface BaseCoreRestResourceTest<ID extends Comparable<ID> & Serializable,
        USER extends BaseUser<ID>,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> extends BaseCoreResourceTest<ID, USER, D, DTO, C> {

    /**
     * Get a WebTestClient instance for testing RESTful resources.
     *
     * @return A WebTestClient instance for making test requests to RESTful resources.
     */
    WebTestClient getWebTestClient();

    /**
     * Prepare HTTP headers for a test request, including content and user information headers.
     *
     * @param httpHeaders The HttpHeaders object to prepare for the test request.
     */
    default void prepareHeader(org.springframework.http.HttpHeaders httpHeaders) {
        prepareContentHeader(httpHeaders);
        prepareUserInfoHeader(httpHeaders);
    }

    /**
     * Prepare the content header with JSON content type for a test request.
     *
     * @param httpHeaders The HttpHeaders object to prepare for the test request.
     */
    default void prepareContentHeader(org.springframework.http.HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * Prepare the user information header with encoded user data for a test request.
     *
     * @param httpHeaders The HttpHeaders object to prepare for the test request.
     */
    @SneakyThrows
    default void prepareUserInfoHeader(org.springframework.http.HttpHeaders httpHeaders) {
        httpHeaders.add(ir.msob.jima.core.commons.Constants.USER_INFO_HEADER_NAME, UserInfoUtil.encodeUser(getObjectMapper(), this.<ID, USER>getSampleUser()));
    }
}

