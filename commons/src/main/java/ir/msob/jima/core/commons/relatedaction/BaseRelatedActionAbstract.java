package ir.msob.jima.core.commons.relatedaction;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedActionAbstract' class represents an abstract base related action with a set of relatedAction.
 * It implements the 'BaseRelatedAction' interface.
 * The class includes a field for the relatedAction and getter and setter methods for this field.
 * The relatedAction are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the relatedAction are sorted in their natural order.
 * The class also includes a no-argument constructor.
 * The 'relatedAction' field is annotated with '@Valid' to enable relatedaction of the relatedAction.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedActionAbstract<ID extends Comparable<ID> & Serializable, CM extends RelatedActionAbstract<ID>> implements BaseRelatedAction<ID, CM> {
    /**
     * The relatedAction of the base related action.
     */
    @Valid
    private SortedSet<CM> relatedAction = new TreeSet<>();
}