package ir.msob.jima.core.commons.model.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.ModelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The 'BooleanMessage' class represents a message that contains a boolean result.
 * It extends the 'ModelType' class and includes a field for the result and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a boolean result as a parameter.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BooleanMessage extends ModelType {
    /**
     * The result of the boolean message.
     */
    private Boolean result;
}