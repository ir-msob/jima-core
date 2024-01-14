package ir.msob.jima.core.commons.model.keyvalue;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'KeyValueFilters' class is a concrete implementation of the 'BaseFilters' interface.
 * It includes a key filter of type 'Filter<K>' and a value filter of type 'Filter<V>', where 'K' extends 'Comparable' and 'Serializable', and 'V' extends 'Serializable'.
 * This means that the key filter and the value filter can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the key filter and the value filter.
 * The class also includes a no-argument constructor.
 * The class is annotated with '@JsonInclude(JsonInclude.Include.NON_NULL)', which means that null fields will not be included in the JSON output.
 * The class is also annotated with '@ToString(callSuper = true)', which means that the 'toString' method will include information from the superclass.
 *
 * @param <K> The type of the key filter.
 * @param <V> The type of the value filter.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyValueFilters<K extends Comparable<K> & Serializable, V extends Serializable> implements BaseFilters {
    /**
     * The key filter of the 'KeyValueFilters'.
     */
    private Filter<K> key;

    /**
     * The value filter of the 'KeyValueFilters'.
     */
    private Filter<V> value;
}