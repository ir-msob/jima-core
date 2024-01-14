package ir.msob.jima.core.commons.model.criteria.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'Param' class represents a parameter that can be used in filter criteria.
 * It extends the 'BaseFilter' class and implements the 'Serializable' interface.
 * The class is a generic class, with the generic type 'TYPE' extending 'Serializable'.
 * This means that the filter criteria can be of any type that implements 'Serializable'.
 * The class includes getter and setter methods for the filter criteria.
 * The class also includes a no-argument constructor.
 *
 * @param <TYPE> The type of the filter criteria.
 *
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Param<TYPE extends Serializable> extends BaseFilter<TYPE> {

}