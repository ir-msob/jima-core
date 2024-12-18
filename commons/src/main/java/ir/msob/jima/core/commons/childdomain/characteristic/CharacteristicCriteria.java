package ir.msob.jima.core.commons.childdomain.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaKey;
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
public class CharacteristicCriteria<ID extends Comparable<ID> & Serializable, CD extends Characteristic<ID>> extends BaseChildCriteriaAbstract<ID, CD> implements BaseChildCriteriaKey<ID, CD>, BaseFilters {
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
    public boolean isMatching(CD childDomain) {
        if (!super.isMatching(childDomain)) {
            return false;
        }

        if (Filter.isMatching(this.getKey(), childDomain.getKey())) {
            return false;
        }
        if (this.getValue() != null) {
            if (!this.getValue().isMatchingSerializable(childDomain.getValue())) {
                return false;
            }
        }
        return !Filter.isMatching(this.getDataType(), childDomain.getDataType());
    }
}