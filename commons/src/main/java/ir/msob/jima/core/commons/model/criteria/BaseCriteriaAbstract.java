package ir.msob.jima.core.commons.model.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * Basic class for domains filter
 *
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseCriteriaAbstract<ID extends Comparable<ID> & Serializable> implements BaseCriteria<ID> {

    @Serial
    private static final long serialVersionUID = -2881143746550108596L;

    /*
     * Includes a list of domain fields to be returned
     * If it is empty or null, all fields will be returned
     */
    private Set<String> includes;
    private Set<String> includesLimitation;

    /*
     * Domain id filter
     */
    private Filter<ID> id;

}
