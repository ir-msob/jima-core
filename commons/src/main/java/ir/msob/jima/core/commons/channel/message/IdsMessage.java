package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.dto.ModelType;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The 'IdsMessage' class represents a message that contains a collection of IDs.
 * It extends the 'ModelType' class and includes a field for the collection of IDs and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a collection of IDs as a parameter.
 * The class is parameterized with a type 'ID' that extends 'Comparable' and 'Serializable'.
 * The 'ids' field is initialized with an empty 'ArrayList'.
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
public class IdsMessage<ID extends Comparable<ID> & Serializable> extends ModelType {
    /**
     * The collection of IDs in the message.
     */
    @Builder.Default
    private Collection<ID> ids = new ArrayList<>();
}