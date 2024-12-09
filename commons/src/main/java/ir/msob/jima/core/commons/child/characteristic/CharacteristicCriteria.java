package ir.msob.jima.core.commons.child.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.child.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
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
public class CharacteristicCriteria<ID extends Comparable<ID> & Serializable, RM extends Characteristic<ID>> extends BaseChildCriteriaAbstract<ID, RM> implements BaseFilters {
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
    public boolean isMatching(RM relatedModel) {
        if (!super.isMatching(relatedModel)) {
            return false;
        }

        if (Filter.isMatching(this.getKey(), relatedModel.getKey())) {
            return false;
        }
        if (this.getValue() != null) {
            if (!this.getValue().isMatchingSerializable(relatedModel.getValue())) {
                return false;
            }
        }
        return !Filter.isMatching(this.getDataType(), relatedModel.getDataType());
    }
}