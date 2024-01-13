package ir.msob.jima.core.commons.model.criteria.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Filters that can be applied to fields
 *
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Param<TYPE extends Serializable> extends BaseFilter<TYPE> {

}
