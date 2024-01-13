package ir.msob.jima.core.commons.model.domain;

import java.io.Serializable;

/**
 * @param <ID>
 * @author Yaqub Abdi
 */
public interface BaseDomainId<ID extends Comparable<ID> & Serializable> {

    ID newDomainId();

}
