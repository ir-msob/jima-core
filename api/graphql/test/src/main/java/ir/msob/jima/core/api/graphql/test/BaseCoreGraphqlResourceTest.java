package ir.msob.jima.core.api.graphql.test;

import ir.msob.jima.core.commons.domain.BaseDomain;
import ir.msob.jima.core.commons.dto.BaseDto;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.test.BaseCoreResourceTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.io.Serializable;

/**
 * This interface represents a base class for GraphQL resource testing in a Java application. It extends the {@link BaseCoreResourceTest}
 * interface and provides a method to obtain a GraphQL tester.
 *
 * @param <ID>   The type representing unique identifiers for entities, typically extending Comparable and Serializable.
 * @param <USER> The type representing a user entity that is often used for testing.
 * @param <D>    The type representing the domain or entity that is being tested.
 * @param <DTO>  The type representing a Data Transfer Object (DTO) related to the domain being tested.
 * @param <C>    The type representing criteria for queries, often associated with user and entity filtering.
 */
public interface BaseCoreGraphqlResourceTest<ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseDomain<ID>,
        DTO extends BaseDto<ID>,
        C extends BaseCriteria<ID>> extends BaseCoreResourceTest<ID, USER, D, DTO, C> {

    /**
     * Get a GraphQL tester instance, which can be used to send GraphQL queries and mutations for testing purposes.
     *
     * @return A GraphQlTester instance for testing GraphQL resources.
     */
    GraphQlTester getGraphQlTester();
}
