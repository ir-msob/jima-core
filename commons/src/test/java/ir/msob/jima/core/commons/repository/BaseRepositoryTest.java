package ir.msob.jima.core.commons.repository;

import ir.msob.jima.core.commons.ConcreteBaseRepository;
import ir.msob.jima.core.commons.ConcreteBaseUser;
import ir.msob.jima.core.commons.domain.SampleDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseRepositoryTest {
    private ConcreteBaseRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new ConcreteBaseRepository();
    }

    @Test
    void testGetIdClass() {
        // Get the ID class
        Class<String> idClass = repository.getIdClass();

        // Assert that idClass is not null
        assertNotNull(idClass);
    }

    @Test
    void testGetUserClass() {
        // Get the User class
        Class<ConcreteBaseUser> userClass = repository.getUserClass();

        // Assert that userClass is not null
        assertNotNull(userClass);
    }

    @Test
    void testGetDomainClass() {
        // Get the Domain class
        Class<SampleDomain<String>> domainClass = repository.getDomainClass();

        // Assert that domainClass is not null
        assertNotNull(domainClass);
    }
}
