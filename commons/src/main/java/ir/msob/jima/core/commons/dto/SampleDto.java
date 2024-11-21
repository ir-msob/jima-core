package ir.msob.jima.core.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.SampleDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * The 'SampleDto' class is a concrete implementation of the 'SampleDomain' class and the 'BaseDto' interface.
 * It extends 'SampleDomain' with a generic type 'ID' that extends 'Comparable' and 'Serializable'.
 * This means that the ID of the DTO can be of any type that is comparable and serializable.
 * The class includes getter and setter methods.
 * The class also includes a no-argument constructor.
 * The class is annotated with '@JsonInclude(JsonInclude.Include.NON_NULL)', which means that null fields will not be included in the JSON output.
 * The class is also annotated with '@ToString(callSuper = true)', which means that the 'toString' method will include information from the superclass.
 *
 * @param <ID> The type of the ID of the DTO.
 */
@Setter
@Getter
@ToString(callSuper = true)
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SampleDto<ID extends Comparable<ID> & Serializable> extends SampleDomain<ID> implements BaseDto<ID> {
}