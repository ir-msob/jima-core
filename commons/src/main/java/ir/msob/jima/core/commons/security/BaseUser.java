package ir.msob.jima.core.commons.security;

import ir.msob.jima.core.commons.model.dto.ModelType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class represents a base user with an ID, session ID, username, roles, and audience.
 * It extends the ModelType class and implements Serializable interface for the object to be converted into a byte stream.
 * It provides methods to get and set the ID, session ID, username, roles, and audience.
 *
 * @param <ID> the type of the user ID and session ID, which must be comparable and serializable
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseUser<ID extends Comparable<ID> & Serializable> extends ModelType {
    /**
     * The serial version UID for the serialization.
     */
    @Serial
    private static final long serialVersionUID = 4945340068393543863L;

    /**
     * The ID of the user.
     */
    private ID id;

    /**
     * The session ID of the user.
     */
    private ID sessionId;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The roles of the user.
     */
    private SortedSet<String> roles = new TreeSet<>();

    /**
     * The audience of the user.
     */
    private String audience;
}