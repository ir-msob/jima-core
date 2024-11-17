package ir.msob.jima.core.ral.mongo.commons;

import ir.msob.jima.core.commons.service.BaseIdService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * A service for generating and handling BSON ObjectId instances.
 * This service provides methods to create new ObjectIds and convert
 * string representations of ObjectIds back to ObjectId instances.
 */
@Component
public class IdService implements BaseIdService {

    /**
     * Generates a new BSON ObjectId.
     *
     * @return a new ObjectId instance.
     */
    @Override
    @SuppressWarnings("unchecked")
    public ObjectId newId() {
        return new ObjectId();
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
    public ObjectId of(String id) {
        return new ObjectId(id);
    }
}
