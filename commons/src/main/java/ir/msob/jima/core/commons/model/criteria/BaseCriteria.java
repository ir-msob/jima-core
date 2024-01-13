package ir.msob.jima.core.commons.model.criteria;

import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.model.BaseModel;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import ir.msob.jima.core.commons.model.dto.BaseType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic class for domains filter
 *
 * @author Yaqub Abdi
 */
public interface BaseCriteria<ID extends Comparable<ID> & Serializable> extends BaseModel, BaseType {

    default Set<String> getIncludes() {
        return new HashSet<>();
    }

    default void setIncludes(Set<String> includes) {
    }

    default Set<String> getIncludesLimitation() {
        return new HashSet<>();
    }

    default void setIncludesLimitation(Set<String> includes) {
    }

    Filter<ID> getId();

    void setId(Filter<ID> id);

    /**
     * Init and set some conditions to criteria
     */
    default void init() {
    }

    /**
     * Validation criteria data after init
     */
    default void validation() throws BadRequestException {
    }
}
