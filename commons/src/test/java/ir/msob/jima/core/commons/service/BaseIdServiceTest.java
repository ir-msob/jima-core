package ir.msob.jima.core.commons.service;

import ir.msob.jima.core.commons.ConcreteBaseIdService;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseIdServiceTest {

    @Test
    void testNewId() {
        // Create a concrete class that implements BaseIdService for testing.
        ConcreteBaseIdService idService = new ConcreteBaseIdService();

        // Call the newId method to generate an ID.
        Serializable newId = idService.newId();

        // Assert that the generated ID is not null.
        assertNotNull(newId);
    }

    @Test
    void testOf() {
        // Create a concrete class that implements BaseIdService for testing.
        ConcreteBaseIdService idService = new ConcreteBaseIdService();

        // Call the of method to generate an ID.
        Serializable newId = idService.of("ID");

        // Assert that the generated ID is not null.
        assertNotNull(newId);
    }

}
