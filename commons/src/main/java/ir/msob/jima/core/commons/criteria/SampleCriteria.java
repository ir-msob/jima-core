package ir.msob.jima.core.commons.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'SampleCriteria' class is a concrete implementation of the 'BaseCriteriaAbstract' class.
 * It extends 'BaseCriteriaAbstract' with a generic type 'ID' that extends 'Comparable' and 'Serializable'.
 * This means that the ID of the criteria can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the ID filter and for include and include limitation sets.
 * The class also includes a no-argument constructor.
 * The class is annotated with '@JsonInclude(JsonInclude.Include.NON_NULL)', which means that null fields will not be included in the JSON output.
 *
 * @param <ID> The type of the ID of the criteria.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SampleCriteria<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> {
}