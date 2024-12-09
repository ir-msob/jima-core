package ir.msob.jima.core.api.rsocket.test;

import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.dto.BaseDto;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.test.BaseCoreResourceTest;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.io.Serializable;


/**
 * This is a base interface for testing RSocket-based resources in a Java application.
 * It extends the {@link BaseCoreResourceTest} interface and adds the ability to obtain
 * an RSocketRequester for testing RSocket interactions.
 *
 * @param <ID>   The type of identifier for the entities being tested.
 * @param <USER> The type of user or user-child data in the application.
 * @param <D>    The type of domain entities being tested.
 * @param <DTO>  The type of Data Transfer Object (DTO) for the domain entities.
 * @param <C>    The type of criteria used for filtering domain entities.
 */
public interface BaseCoreRsocketResourceTest<ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> extends BaseCoreResourceTest<ID, USER, D, DTO, C> {

    /**
     * Get the RSocketRequester for testing RSocket interactions.
     *
     * @return An RSocketRequester instance for testing.
     */
    RSocketRequester getRSocketRequester();
}
