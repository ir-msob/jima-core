package ir.msob.jima.core.commons.model.characteristic;

import java.util.SortedSet;

/**
 * The 'BaseCharacteristic' interface represents a base characteristic with a set of characteristics.
 * It includes methods to get and set the characteristics.
 * The characteristics are represented as a 'SortedSet' of 'Characteristic' objects.
 * The 'SortedSet' ensures that the characteristics are sorted in their natural order.
 * The 'getCharacteristics' method returns the characteristics, and the 'setCharacteristics' method sets the characteristics.
 *
 *
 */
public interface BaseCharacteristic {

    /**
     * Gets the characteristics.
     *
     * @return The characteristics.
     */
    SortedSet<Characteristic> getCharacteristics();

    /**
     * Sets the characteristics.
     *
     * @param characteristics The characteristics to set.
     */
    void setCharacteristics(SortedSet<Characteristic> characteristics);

}