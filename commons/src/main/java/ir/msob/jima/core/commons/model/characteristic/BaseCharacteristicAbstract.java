package ir.msob.jima.core.commons.model.characteristic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseCharacteristicAbstract' class represents an abstract base characteristic with a set of characteristics.
 * It implements the 'BaseCharacteristic' interface.
 * The class includes a field for the characteristics and getter and setter methods for this field.
 * The characteristics are represented as a 'SortedSet' of 'Characteristic' objects.
 * The 'SortedSet' ensures that the characteristics are sorted in their natural order.
 * The class also includes a no-argument constructor.
 * The 'characteristics' field is annotated with '@Valid' to enable validation of the characteristics.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseCharacteristicAbstract implements BaseCharacteristic {
    /**
     * The characteristics of the base characteristic.
     */
    @Valid
    private SortedSet<Characteristic> characteristics = new TreeSet<>();
}