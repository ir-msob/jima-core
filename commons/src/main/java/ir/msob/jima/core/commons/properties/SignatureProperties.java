package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class represents a collection of signature algorithms with their configurations.
 * <p>
 * It provides a `SortedSet` of `Algorithm` objects to manage and access different
 * signature algorithms supported by the application. The `TreeSet` implementation ensures
 * the algorithms are ordered based on their internal comparison logic (typically by ID).
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SignatureProperties {

    /**
     * A sorted set of `Algorithm` objects representing the supported signature algorithms.
     * The algorithms are ordered based on their ID using a `TreeSet` implementation.
     */
    private SortedSet<Algorithm> algorithms = new TreeSet<>();

    /**
     * This inner class represents a configuration for a specific signature algorithm.
     * <p>
     * It holds information about the algorithm's identifier (ID), its name (algorithm),
     * the associated private and public keys, and an enabled flag indicating if the algorithm
     * is currently usable.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Algorithm implements Comparable<Algorithm> {

        /**
         * The unique identifier of the signature algorithm.
         */
        private String id;

        /**
         * The full name or description of the signature algorithm.
         */
        private String algorithm;

        /**
         * The private key associated with this signature algorithm.
         */
        private PrivateKey privateKey;

        /**
         * The public key associated with this signature algorithm.
         */
        private PublicKey publicKey;

        /**
         * A flag indicating if this signature algorithm is currently enabled for use.
         */
        private boolean enabled = false;

        /**
         * Compares this `Algorithm` object with another based on their IDs.
         *
         * @param o The other `Algorithm` object to compare with.
         * @return 0 if the IDs are equal, a negative value if this object's ID is less,
         * and a positive value otherwise.
         */
        @Override
        public int compareTo(Algorithm o) {
            return Objects.compare(this.getId(), o.getId(), String::compareTo);
        }

        /**
         * Checks if this `Algorithm` object is equal to another object.
         * <p>
         * Two `Algorithm` objects are considered equal only if their IDs are the same.
         *
         * @param o The object to compare with.
         * @return true if the objects are equal (same ID), false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null)
                return false;

            if (o instanceof Algorithm that) {
                return Objects.equals(this.getId(), that.getId());
            }
            return false;
        }

        /**
         * Generates a hash code for this `Algorithm` object based on its ID.
         *
         * @return The hash code of the algorithm's ID.
         */
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
