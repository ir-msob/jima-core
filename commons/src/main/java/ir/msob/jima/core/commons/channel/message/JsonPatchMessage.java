package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.fge.jsonpatch.JsonPatch;
import ir.msob.jima.core.commons.dto.ModelType;
import ir.msob.jima.core.commons.shared.criteria.BaseCriteria;
import lombok.*;

import java.io.Serializable;

/**
 * The 'JsonPatchMessage' class represents a message that contains a JSON Patch and a criteria.
 * It extends the 'CriteriaMessage' class and includes a field for the JSON Patch and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a JSON Patch and a criteria as parameters.
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
public class JsonPatchMessage<ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> extends ModelType {
    /**
     * The criteria of the message.
     */
    private C criteria;
    /**
     * The JSON Patch of the message.
     */
    private JsonPatch jsonPatch;
}