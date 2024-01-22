package ir.msob.jima.core.commons.model.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * The 'BaseChildCriteriaAbstract' class is an abstract implementation of the 'BaseChildCriteria' interface.
 * It extends the 'BaseCriteriaAbstract' class with a generic type 'BaseChildCriteriaAbstract<ID>'.
 * This means that the child criteria models can be compared based on their IDs.
 * The class is a generic class, with the generic type 'ID' extending 'Comparable' and 'Serializable'.
 * This means that the ID of the child criteria model can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the parent criteria ID.
 * It also includes a static final long 'serialVersionUID' which represents the version control for the class.
 * The class is annotated with 'JsonInclude' to control the serialization of null values.
 *
 * @param <ID> the type of the identifier for the criteria model. It must be comparable and serializable.
 */
@Setter
@Getter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseChildCriteriaAbstract<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> {

    /**
     * The version control for the class.
     */
    @Serial
    private static final long serialVersionUID = -2881143746650108596L;

    /**
     * The parent criteria ID of the model.
     */
    private Filter<ID> parentId;

}