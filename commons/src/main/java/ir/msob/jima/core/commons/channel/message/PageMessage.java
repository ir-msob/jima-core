package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.shared.ModelType;
import ir.msob.jima.core.commons.shared.PageDto;
import lombok.*;

import java.io.Serializable;

/**
 * The 'PageMessage' class represents a message that contains a 'Page' object.
 * It extends the 'ModelType' class and includes a field for the 'Page' object and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a 'Page' object as a parameter.
 * The class is parameterized with two types 'ID' that extends 'Comparable' and 'Serializable', and 'DTO' that extends 'BaseChildDto'.
 *
 * @param <ID>  The type of ID.
 * @param <DTO> The type of DTO.
 */
@Setter
@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageMessage<ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>> extends ModelType {
    /**
     * The 'Page' object of the message.
     */
    private PageDto<DTO> page;
}