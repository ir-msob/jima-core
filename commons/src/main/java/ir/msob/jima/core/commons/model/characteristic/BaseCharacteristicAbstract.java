package ir.msob.jima.core.commons.model.characteristic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseCharacteristicAbstract implements BaseCharacteristic {
    @Valid
    private SortedSet<Characteristic> characteristics = new TreeSet<>();
}
