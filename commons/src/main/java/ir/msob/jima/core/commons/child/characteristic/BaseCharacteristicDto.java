package ir.msob.jima.core.commons.child.characteristic;

import ir.msob.jima.core.commons.child.BaseChildDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedActionDto' interface represents a base characteristic with a set of characteristics.
 * It includes methods to get and set the characteristics.
 * The characteristics are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the characteristics are sorted in their natural order.
 * The 'getContactMedia' method returns the characteristics, and the 'setContactMedia' method sets the characteristics.
 */
public interface BaseCharacteristicDto<ID extends Comparable<ID> & Serializable, CH extends Characteristic<ID>>
        extends BaseChildDto<ID> {

    /**
     * Gets the characteristics.
     *
     * @return The characteristics.
     */
    SortedSet<CH> getCharacteristics();

    /**
     * Sets the characteristics.
     *
     * @param characteristics The characteristics to set.
     */
    void setCharacteristics(SortedSet<CH> characteristics);

}