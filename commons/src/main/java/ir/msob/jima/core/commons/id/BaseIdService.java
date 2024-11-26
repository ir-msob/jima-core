package ir.msob.jima.core.commons.id;

import java.io.Serializable;

/**
 * The 'BaseIdService' interface defines methods for generating new IDs and converting string representations into IDs.
 * It is designed to provide common functionality for managing identifiers, typically used in various services and components.
 */
public interface BaseIdService {

    /**
     * Generate a new ID of a specified type.
     *
     * @param <ID> The type of ID to be generated, which should be both comparable and serializable.
     * @return A new ID of the specified type.
     */
    <ID extends Comparable<ID> & Serializable> ID newId();

    /**
     * Convert a string representation of an ID into the specified ID type.
     *
     * @param <ID> The type of ID to be constructed, which should be both comparable and serializable.
     * @param id   The string representation of the ID.
     * @return An ID of the specified type constructed from the provided string representation.
     */
    <ID extends Comparable<ID> & Serializable> ID of(String id);
}
