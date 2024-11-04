package ir.msob.jima.core.test;

/**
 * A functional interface representing an assertion operation on a given value.
 * This interface can be used to define custom assertions in tests.
 *
 * @param <V> the type of the value to be asserted
 */
@FunctionalInterface
public interface Assertable<V> {

    /**
     * Performs an assertion on the provided value.
     *
     * @param value the value to be asserted
     */
    void assertThan(V value);
}