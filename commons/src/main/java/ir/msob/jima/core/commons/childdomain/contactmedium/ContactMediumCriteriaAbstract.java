package ir.msob.jima.core.commons.childdomain.contactmedium;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaAbstract;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaName;
import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteriaType;
import ir.msob.jima.core.commons.filter.BaseFilters;
import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriodFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'RelatedActionCriteria' class represents a set of filters for contact mediums.
 * It implements the 'BaseFilters' interface.
 * The class includes fields for key, value, dataType, and isArray filters, and getter and setter methods for these fields.
 * Each filter is represented as a 'Filter' object.
 * The 'key' filter is used to filter contact mediums based on their keys.
 * The 'value' filter is used to filter contact mediums based on their values.
 * The 'dataType' filter is used to filter contact mediums based on their data types.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactMediumCriteriaAbstract<ID extends Comparable<ID> & Serializable, CD extends ContactMediumAbstract<ID>> extends BaseChildCriteriaAbstract<ID, CD> implements BaseChildCriteriaName<ID, CD>, BaseChildCriteriaType<ID, CD>, BaseFilters {
    private Filter<String> name;
    private Filter<String> type;
    private Filter<String> value;
    private Filter<Integer> order;
    private TimePeriodFilters validFor;

    @Override
    public boolean isMatching(CD relatedModel) {
        if (!super.isMatching(relatedModel)) {
            return false;
        }
        if (Filter.isMatching(this.getName(), relatedModel.getName())) {
            return false;
        }
        if (Filter.isMatching(this.getType(), relatedModel.getType())) {
            return false;
        }
        if (Filter.isMatching(this.getValue(), relatedModel.getValue())) {
            return false;
        }
        if (Filter.isMatching(this.getOrder(), relatedModel.getOrder())) {
            return false;
        }
        return !TimePeriodFilters.isMatching(this.getValidFor(), relatedModel.getValidFor());
    }
}