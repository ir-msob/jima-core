package ir.msob.jima.core.commons.child.characteristic;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedActionContainerAbstract' class represents an abstract base characteristic with a set of characteristics.
 * It implements the 'BaseRelatedActionContainer' interface.
 * The class includes a field for the characteristics and getter and setter methods for this field.
 * The characteristics are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the characteristics are sorted in their natural order.
 * The class also includes a no-argument constructor.
 * The 'characteristics' field is annotated with '@Valid' to enable characteristic of the characteristics.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseCharacteristicContainerAbstract<ID extends Comparable<ID> & Serializable, CH extends Characteristic<ID>> implements BaseCharacteristicContainer<ID, CH> {
    /**
     * The characteristics of the base characteristic.
     */
    private SortedSet<@Valid CH> characteristics = new TreeSet<>();
}