package ir.msob.jima.core.test;

@FunctionalInterface
public interface Assertable<V> {
    void assertThan(V value);
}