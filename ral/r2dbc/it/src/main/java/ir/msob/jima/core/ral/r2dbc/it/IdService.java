package ir.msob.jima.core.ral.r2dbc.it;

import ir.msob.jima.core.commons.id.BaseIdService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * A service for generating and handling UUID instances.
 * This service provides methods to create new UUIDs and convert
 * string representations of UUIDs back to UUID instances.
 */
@Component
public class IdService implements BaseIdService {

    /**
     * Generates a new UUID.
     *
     * @return a new UUID instance.
     */
    @Override
    @SuppressWarnings("unchecked")
    public String newId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Converts a string representation of an ObjectId to an ObjectId instance.
     *
     * @param id the string representation of the ObjectId.
     * @return an ObjectId instance corresponding to the provided string.
     * @throws IllegalArgumentException if the string is not a valid ObjectId.
     */
    @Override
    @SuppressWarnings("unchecked")
    public String of(String id) {
        return id;
    }
}
