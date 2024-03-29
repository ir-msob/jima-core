package ir.msob.jima.core.commons.service;

import ir.msob.jima.core.commons.ConcreteBaseService;
import ir.msob.jima.core.commons.ConcreteBaseUser;
import ir.msob.jima.core.commons.data.SampleRepository;
import ir.msob.jima.core.commons.model.domain.SampleDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseServiceTest {

    private ConcreteBaseService service;

    @BeforeEach
    public void setUp() {
        service = new ConcreteBaseService();
    }

    @Test
    void testGetIdClass() {
        assertEquals(String.class, service.getIdClass());
    }

    @Test
    void testGetUserClass() {
        assertEquals(ConcreteBaseUser.class, service.getUserClass());
    }

    @Test
    void testGetDomainClass() {
        assertEquals(SampleDomain.class, service.getDomainClass());
    }

    @Test
    void testGetRepositoryClass() {
        assertEquals(SampleRepository.class, service.getRepositoryClass());
    }

}
