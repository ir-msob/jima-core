package ir.msob.jima.core.commons.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.shared.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'RelatedActionFilters' class represents a set of filters for characteristics.
 * It implements the 'BaseFilters' interface.
 * The class includes fields for key, value, dataType, and isArray filters, and getter and setter methods for these fields.
 * Each filter is represented as a 'Filter' object.
 * The 'key' filter is used to filter characteristics based on their keys.
 * The 'value' filter is used to filter characteristics based on their values.
 * The 'dataType' filter is used to filter characteristics based on their data types.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacteristicFilters implements BaseFilters {
    /**
     * The key filter of the characteristic filters.
     */
    private Filter<String> key;

    /**
     * The value filter of the characteristic filters.
     */
    private Filter<Serializable> value;

    /**
     * The data type filter of the characteristic filters.
     */
    private Filter<String> dataType;
}