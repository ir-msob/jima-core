package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.dto.BaseDto;
import ir.msob.jima.core.commons.dto.ModelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The 'DtosMessage' class represents a message that contains a collection of DTOs (Data Transfer Objects).
 * It extends the 'ModelType' class and includes a field for the collection of DTOs and getter and setter methods for this field.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and a constructor that accepts a collection of DTOs as a parameter.
 * The class is parameterized with two types 'ID' that extends 'Comparable' and 'Serializable', and 'DTO' that extends 'BaseDto'.
 * The 'dtos' field is initialized with an empty 'ArrayList'.
 *
 * @param <ID>  The type of ID.
 * @param <DTO> The type of DTO.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtosMessage<ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>> extends ModelType {
    /**
     * The collection of DTOs in the message.
     */
    private Collection<DTO> dtos = new ArrayList<>();
}