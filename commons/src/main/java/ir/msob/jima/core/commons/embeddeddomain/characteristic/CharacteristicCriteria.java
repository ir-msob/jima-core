package ir.msob.jima.core.commons.embeddeddomain.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.embeddeddomain.criteria.BaseEmbeddedCriteriaAbstract;
import ir.msob.jima.core.commons.embeddeddomain.criteria.BaseEmbeddedCriteriaKey;
import ir.msob.jima.core.commons.filter.BaseFilters;
import ir.msob.jima.core.commons.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'RelatedActionCriteria' class represents a set of filters for characteristics.
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
public class CharacteristicCriteria<ID extends Comparable<ID> & Serializable, CD extends Characteristic<ID>> extends BaseEmbeddedCriteriaAbstract<ID, CD> implements BaseEmbeddedCriteriaKey<ID, CD>, BaseFilters {
    /**
     * The key filter of the characteristic filters.
     */
    private Filter<String> key;

    /**
     * The value filter of the characteristic filters.
     */
    private Filter<? extends Comparable<? extends Serializable>> value;

    /**
     * The data type filter of the characteristic filters.
     */
    private Filter<String> dataType;

    @Override
    public boolean isMatching(CD embeddeddomain) {
        if (!super.isMatching(embeddeddomain)) {
            return false;
        }

        if (Filter.isMatching(this.getKey(), embeddeddomain.getKey())) {
            return false;
        }
        if (this.getValue() != null && !this.getValue().isMatchingSerializable(embeddeddomain.getValue())) {
            return false;
        }
        return !Filter.isMatching(this.getDataType(), embeddeddomain.getDataType());
    }
}