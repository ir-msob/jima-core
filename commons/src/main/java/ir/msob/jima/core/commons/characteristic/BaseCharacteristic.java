package ir.msob.jima.core.commons.characteristic;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedAction' interface represents a base characteristic with a set of characteristics.
 * It includes methods to get and set the characteristics.
 * The characteristics are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the characteristics are sorted in their natural order.
 * The 'getContactMedia' method returns the characteristics, and the 'setContactMedia' method sets the characteristics.
 */
public interface BaseCharacteristic<ID extends Comparable<ID> & Serializable, CH extends Characteristic<ID>> {

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