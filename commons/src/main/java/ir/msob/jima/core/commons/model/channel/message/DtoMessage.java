package ir.msob.jima.core.commons.model.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.core.commons.model.dto.ModelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The 'DtoMessage' class represents a message that contains a DTO (Data Transfer Object).
 * It extends the 'ModelType' class and includes a field for the DTO and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a DTO as a parameter.
 * The class is parameterized with two types 'ID' that extends 'Comparable' and 'Serializable', and 'DTO' that extends 'BaseDto'.
 *
 * @param <ID>  The type of ID.
 * @param <DTO> The type of DTO.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoMessage<ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>> extends ModelType {
    /**
     * The DTO of the message.
     */
    private DTO dto;
}