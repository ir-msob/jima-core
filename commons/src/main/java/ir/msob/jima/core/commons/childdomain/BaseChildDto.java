package ir.msob.jima.core.commons.childdomain;

import ir.msob.jima.core.commons.domain.BaseDto;

import java.io.Serializable;

/**
 * The 'BaseChildDto' interface represents a basic class for data transfer objects (DTOs).
 * It extends the 'BaseDomain' interface with a generic type 'ID' that extends 'Comparable' and 'Serializable'.
 * This means that the ID of the DTO can be of any type that is comparable and serializable.
 * The interface is annotated with '@JsonTypeInfo', which includes type information in the JSON output.
 * The type information is included as a property with the name specified in 'Constants.TYPE_PROPERTY_NAME'.
 * The interface includes a 'getClassType' method that returns the simple name of the class of the DTO.
 *
 * @param <ID> The type of the ID of the DTO.
 */
public interface BaseChildDto<ID extends Comparable<ID> & Serializable> extends BaseChildDomain<ID>, BaseDto<ID> {

}