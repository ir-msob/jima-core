package ir.msob.jima.core.commons.model.keyvalue;

import ir.msob.jima.core.commons.model.BaseModel;
import lombok.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K extends Comparable<K> & Serializable, V extends Serializable> implements Comparable<KeyValue<K, V>>, BaseModel {
    private K key;
    private V value;

    public static <K extends Comparable<K> & Serializable, V extends Serializable> KeyValue<K, V> of(K k, V v) {
        KeyValue<K, V> keyValue = new KeyValue<>();
        keyValue.setKey(k);
        keyValue.setValue(v);
        return keyValue;
    }

    @Override
    public int compareTo(KeyValue<K, V> o) {
        if (this == o) {
            return 0;
        }

        if (o != null && (this.getKey() != null && o.getKey() != null)) {
            return this.getKey().compareTo(o.getKey());

        }

        return Comparator
                .comparing(System::identityHashCode)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof KeyValue<?, ?> keyValue) {
            KeyValue<K, V> that = (KeyValue<K, V>) keyValue;
            return Objects.equals(this.getKey(), that.getKey());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }

}
