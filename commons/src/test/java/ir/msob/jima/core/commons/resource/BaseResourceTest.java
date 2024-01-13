package ir.msob.jima.core.commons.resource;

import ir.msob.jima.core.commons.ConcreteBaseResource;
import ir.msob.jima.core.commons.ConcreteBaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseResourceTest {

    private ConcreteBaseResource resource;

    @BeforeEach
    public void setUp() {
        resource = new ConcreteBaseResource();
    }

    @Test
    void testGetIdClass() {
        assertEquals(String.class, resource.getIdClass());
    }

    @Test
    void testGetUserClass() {
        assertEquals(ConcreteBaseUser.class, resource.getUserClass());
    }


}
