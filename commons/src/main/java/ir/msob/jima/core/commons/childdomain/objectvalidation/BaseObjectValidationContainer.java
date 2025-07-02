package ir.msob.jima.core.commons.childdomain.objectvalidation;

import java.io.Serializable;
import java.util.SortedSet;

public interface BaseObjectValidationContainer<ID extends Comparable<ID> & Serializable, OV extends ObjectValidationAbstract<ID>> {
    SortedSet<OV> getObjectValidations();

    void setObjectValidations(SortedSet<OV> objectValidations);
}
