package ir.msob.jima.core.commons.model.keyvalue;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyValueFilters<K extends Comparable<K> & Serializable, V extends Serializable> implements BaseFilters {
    private Filter<K> key;
    private Filter<V> value;
}
