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
 * The {@code BaseChildCriteriaAbstract} class is an abstract implementation of the {@code BaseChildCriteria} interface.
 * It extends the {@code BaseCriteriaAbstract} class with a generic type {@code BaseChildCriteriaAbstract<ID>},
 * enabling child criteria models to be compared based on their IDs.
 * <p>
 * This class is generic, with the generic type {@code ID} extending {@code Comparable} and {@code Serializable}.
 * It means that the ID of the child criteria model can be of any type that is comparable and serializable.
 * <p>
 * The class includes getter and setter methods for the parent criteria ID.
 * Additionally, it includes a static final long {@code serialVersionUID} which represents the version control for the class.
 * <p>
 * The class is annotated with {@code JsonInclude} to control the serialization of null values.
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