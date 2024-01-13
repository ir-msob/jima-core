package ir.msob.jima.core.commons.model.characteristic;

import java.util.SortedSet;

/**
 * @author Yaqub Abdi
 */
public interface BaseCharacteristic {

    SortedSet<Characteristic> getCharacteristics();

    void setCharacteristics(SortedSet<Characteristic> characteristics);

}
