package ir.msob.jima.core.commons.childdomain.characteristic;

import java.io.Serializable;
import java.util.SortedSet;

public interface BaseCharacteristicContainer<ID extends Comparable<ID> & Serializable, CH extends Characteristic<ID>> {
    SortedSet<CH> getCharacteristics();

    void setCharacteristics(SortedSet<CH> characteristics);
}
