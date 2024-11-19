package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The 'DtoMessage' class is a specialized form of 'IdMessage' that includes a DTO (Data Transfer Object).
 * This class is used when a message needs to carry a DTO along with the ID.
 * It is parameterized with two types 'ID' that extends 'Comparable' and 'Serializable', and 'DTO' that extends 'BaseDto'.
 *
 * @param <ID>  The type of ID.
 * @param <DTO> The type of DTO.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoMessage<ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>> extends IdMessage<ID> {
    /**
     * The 'dto' field holds the DTO that is part of the message.
     * This is used when the message needs to carry a DTO.
     */
    private DTO dto;
}