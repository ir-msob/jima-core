package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.ModelType;
import lombok.*;

import java.io.Serializable;

/**
 * The 'IdMessage' class represents a message that contains an ID.
 * It extends the 'ModelType' class and includes a field for the ID and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts an ID as a parameter.
 * The class is parameterized with a type 'ID' that extends 'Comparable' and 'Serializable'.
 *
 * @param <ID> The type of ID.
 */
@Setter
@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdMessage<ID extends Comparable<ID> & Serializable> extends ModelType {
    /**
     * The ID of the message.
     */
    private ID id;
}