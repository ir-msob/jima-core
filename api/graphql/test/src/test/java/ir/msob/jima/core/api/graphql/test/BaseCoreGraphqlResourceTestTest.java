package ir.msob.jima.core.api.graphql.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.domain.SampleCriteria;
import ir.msob.jima.core.commons.domain.SampleDomain;
import ir.msob.jima.core.commons.domain.SampleDto;
import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.shared.PageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class is an integration test for the BaseCoreGraphqlResourceTestTest class.
 * It tests the behavior of the methods in the BaseCoreGraphqlResourceTestTest class.
 */
class BaseCoreGraphqlResourceTestTest {

    // Instance of the class under test
    private BaseCoreGraphqlResourceTest<String, BaseUser, SampleDomain<String>, SampleDto<String>, SampleCriteria<String>> graphqlResourceTest;
    // Mocked instance of GraphQlTester
    private GraphQlTester mockGraphQlTester;

    /**
     * This test verifies that the getGraphQlTester method returns a non-null instance of GraphQlTester.
     */
    @Test
    void testGetGraphQlTester() {
        // Get a GraphQL tester instance from the resourceTest
        GraphQlTester graphQlTester = graphqlResourceTest.getGraphQlTester();

        // Assert that the returned instance is not null
        Assertions.assertNotNull(graphQlTester);
    }


    /**
     * This method sets up the test environment before each test.
     * It initializes the class under test and the mocked GraphQlTester instance.
     */
    @BeforeEach
    void setup() {
        // Mock the GraphQlTester instance
        mockGraphQlTester = Mockito.mock(GraphQlTester.class);
        // Initialize the class under test
        graphqlResourceTest = new BaseCoreGraphqlResourceTest<>() {
            @Override
            public Class<? extends BaseResource<String, BaseUser>> getResourceClass() {
                return null;
            }

            @Override
            public ObjectMapper getObjectMapper() {
                return null;
            }

            @Override
            public TypeReference<PageResponse<SampleDto<String>>> getPageResponseReferenceType() {
                return null;
            }

            @Override
            public TypeReference<Collection<String>> getIdsReferenceType() {
                return null;
            }

            @Override
            public TypeReference<SampleCriteria<String>> getCriteriaReferenceType() {
                return null;
            }

            @Override
            public TypeReference<SampleDto<String>> getDtoReferenceType() {
                return null;
            }

            @Override
            public BaseUser getSampleUser() {
                return new BaseUser();
            }

            @Override
            public JimaProperties getJimaProperties() {
                return null;
            }

            @Override
            public GraphQlTester getGraphQlTester() {
                return mockGraphQlTester;
            }
        };
    }

    /**
     * This test verifies that the getGraphQlTester method returns a non-null instance of GraphQlTester.
     */
    @Test
    void getGraphQlTesterReturnsNonNullInstance() {
        GraphQlTester graphQlTester = graphqlResourceTest.getGraphQlTester();
        assertNotNull(graphQlTester);
    }

    /**
     * This test verifies that the getResourceClass method returns null.
     */
    @Test
    void getResourceClassReturnsNull() {
        Class<? extends BaseResource<String, BaseUser>> resourceClass = graphqlResourceTest.getResourceClass();
        assertNull(resourceClass);
    }

    /**
     * This test verifies that the getObjectMapper method returns null.
     */
    @Test
    void getObjectMapperReturnsNull() {
        ObjectMapper objectMapper = graphqlResourceTest.getObjectMapper();
        assertNull(objectMapper);
    }

    /**
     * This test verifies that the getSampleUser method returns an empty Optional.
     */
    @Test
    void getSampleUserReturnsEmptyOptional() {
        BaseUser sampleUser = graphqlResourceTest.getSampleUser();
        assertNotNull(sampleUser);
    }
}