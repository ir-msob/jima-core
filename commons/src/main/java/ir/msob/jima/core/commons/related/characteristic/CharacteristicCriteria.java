package ir.msob.jima.core.commons.related.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.commons.criteria.BaseCriteriaAbstract;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import ir.msob.jima.core.commons.domain.BaseIdModel;
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
public class CharacteristicCriteria<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> implements BaseFilters {
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

    public <C extends CharacteristicCriteria<ID>, DTO extends Characteristic<ID>> boolean isMatching(C criteria, DTO dto) {
        if (!super.isMatching((BaseCriteria<ID>) criteria, (BaseIdModel<ID>) dto)) {
            return false;
        }
        if (Filter.isMatching(criteria.getKey(), dto.getKey())) {
            return false;
        }
        if (criteria.getValue() != null) {
            if (!criteria.getValue().isMatchingSerializable(dto.getValue())) {
                return false;
            }
        }
        return !Filter.isMatching(criteria.getDataType(), dto.getDataType());
    }
}