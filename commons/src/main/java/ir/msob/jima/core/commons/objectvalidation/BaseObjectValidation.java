package ir.msob.jima.core.commons.objectvalidation;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedAction' interface represents a base object validations with a set of object validations.
 * It includes methods to get and set the object validations.
 * The object validations are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the object validations are sorted in their natural order.
 * The 'getObjectValidation' method returns the object validations, and the 'setObjectValidation' method sets the object validations.
 */
public interface BaseObjectValidation<ID extends Comparable<ID> & Serializable, OV extends ObjectValidationAbstract<ID>> {

    /**
     * Gets the object validations.
     *
     * @return The object validations.
     */
    SortedSet<OV> getObjectValidations();

    /**
     * Sets the objectValidation.
     *
     * @param objectValidation The objectValidation to set.
     */
    void setObjectValidations(SortedSet<OV> objectValidation);

}