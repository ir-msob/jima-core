package ir.msob.jima.core.commons.child.contactmedium;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedActionDtoAbstract' class represents an abstract base contact medium with a set of contactMedia.
 * It implements the 'BaseRelatedActionDto' interface.
 * The class includes a field for the contactMedia and getter and setter methods for this field.
 * The contactMedia are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the contactMedia are sorted in their natural order.
 * The class also includes a no-argument constructor.
 * The 'contactMedia' field is annotated with '@Valid' to enable objectvalidation of the contactMedia.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseContactMediumDtoAbstract<ID extends Comparable<ID> & Serializable, CM extends ContactMediumAbstract<ID>> implements BaseContactMediumDto<ID, CM> {
    /**
     * The contactMedia of the base contact medium.
     */
    @Valid
    private SortedSet<CM> contactMedia = new TreeSet<>();
}