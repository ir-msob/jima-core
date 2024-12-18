package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.ModelType;
import lombok.*;

/**
 * The 'LongMessage' class represents a message that contains a Long result.
 * It extends the 'ModelType' class and includes a field for the result and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a Long result as a parameter.
 */
@Setter
@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LongMessage extends ModelType {
    /**
     * The Long result of the message.
     */
    private Long result;
}