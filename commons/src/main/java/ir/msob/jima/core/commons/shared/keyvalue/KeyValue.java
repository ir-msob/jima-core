package ir.msob.jima.core.commons.shared.keyvalue;

import ir.msob.jima.core.commons.shared.BaseModel;
import lombok.*;
import org.jspecify.annotations.NonNull;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * The 'KeyValue' class is a concrete implementation of the 'BaseModel' interface.
 * It includes a key of type 'K' and a value of type 'V', where 'K' extends 'Comparable' and 'Serializable', and 'V' extends 'Serializable'.
 * This means that the key of the key-value pair can be of any type that is comparable and serializable, and the value can be of any type that is serializable.
 * The class includes getter and setter methods for the key and the value.
 * The class also includes a 'compareTo' method that compares this key-value pair to another key-value pair.
 * The comparison is based on the keys of the key-value pairs.
 * If the keys are null, the comparison is based on the identity hash codes of the key-value pairs.
 * The class also includes 'equals' and 'hashCode' methods that are based on the key of the key-value pair.
 * The class also includes a static 'of' method that creates a new key-value pair with the specified key and value.
 *
 * @param <K> The type of the key of the key-value pair.
 * @param <V> The type of the value of the key-value pair.
 */
@Setter
@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K extends Comparable<K> & Serializable, V extends Serializable> implements Comparable<KeyValue<K, V>>, BaseModel {
    private K key;
    private V value;

    /**
     * Creates a new key-value pair with the specified key and value.
     *
     * @param k The key of the key-value pair.
     * @param v The value of the key-value pair.
     * @return The new key-value pair.
     */
    public static <K extends Comparable<K> & Serializable, V extends Serializable> KeyValue<K, V> of(K k, V v) {
        KeyValue<K, V> keyValue = new KeyValue<>();
        keyValue.setKey(k);
        keyValue.setValue(v);
        return keyValue;
    }

    /**
     * Compares this key-value pair to another key-value pair.
     * The comparison is based on the keys of the key-value pairs.
     * If the keys are null, the comparison is based on the identity hash codes of the key-value pairs.
     *
     * @param o The other key-value pair.
     * @return A negative integer, zero, or a positive integer as this key-value pair is less than, equal to, or greater than the specified key-value pair.
     */
    @Override
    public int compareTo(@NonNull KeyValue<K, V> o) {
        if (this == o) {
            return 0;
        }

        if (this.getKey() != null && o.getKey() != null) {
            return this.getKey().compareTo(o.getKey());

        }

        return Comparator
                .comparing(System::identityHashCode)
                .compare(this, o);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * The result is true if and only if the argument is not null and is a 'KeyValue' object that has the same key as this object.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof KeyValue<?, ?> keyValue) {
            @SuppressWarnings("unchecked")
            KeyValue<K, V> that = (KeyValue<K, V>) keyValue;
            return Objects.equals(this.getKey(), that.getKey());
        }
        return false;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by 'HashMap'.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }

}