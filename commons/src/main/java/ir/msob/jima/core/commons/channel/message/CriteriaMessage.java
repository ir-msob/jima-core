package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.dto.ModelType;
import ir.msob.jima.core.commons.criteria.BaseCriteria;
import lombok.*;

import java.io.Serializable;

/**
 * The 'CriteriaMessage' class represents a message that contains a criteria.
 * It extends the 'ModelType' class and includes a field for the criteria and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a criteria as a parameter.
 * The class is parameterized with two types 'ID' that extends 'Comparable' and 'Serializable', and 'C' that extends 'BaseCriteria'.
 *
 * @param <ID> The type of ID.
 * @param <C>  The type of criteria.
 */
@Setter
@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CriteriaMessage<ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> extends ModelType {
    /**
     * The criteria of the message.
     */
    private C criteria;

}