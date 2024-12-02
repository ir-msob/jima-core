package ir.msob.jima.core.commons.shared.keyvalue;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The {@code KeyValueFilters} class is a concrete implementation of the {@code BaseFilters} interface.
 * It includes a key filter of type {@code Filter<K>} and a value filter of type {@code Filter<V>},
 * where {@code K} extends {@code Comparable} and {@code Serializable}, and {@code V} extends {@code Serializable}.
 * <p>
 * This means that the key filter and the value filter can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the key filter and the value filter.
 * Additionally, it includes a no-argument constructor.
 * <p>
 * The class is annotated with {@code @JsonInclude(JsonInclude.Include.NON_NULL)},
 * indicating that null fields will not be included in the JSON output.
 * It is also annotated with {@code @ToString(callSuper = true)},
 * meaning that the {@code toString} method will include information from the superclass.
 *
 * @param <K> The type of the key filter.
 * @param <V> The type of the value filter.
 */

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyValueFilters<K extends Comparable<K> & Serializable, V extends Comparable<V> & Serializable> implements BaseFilters {
    /**
     * The key filter of the 'KeyValueFilters'.
     */
    private Filter<K> key;

    /**
     * The value filter of the 'KeyValueFilters'.
     */
    private Filter<V> value;
}