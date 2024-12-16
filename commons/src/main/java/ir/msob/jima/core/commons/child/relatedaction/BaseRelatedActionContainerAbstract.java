package ir.msob.jima.core.commons.child.relatedaction;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedActionContainerAbstract' class represents an abstract base child action with a set of relatedAction.
 * It implements the 'BaseRelatedActionContainer' interface.
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
public abstract class BaseRelatedActionContainerAbstract<ID extends Comparable<ID> & Serializable, CM extends RelatedActionAbstract<ID>> implements BaseRelatedActionContainer<ID, CM> {
    /**
     * The relatedAction of the base child action.
     */
    private SortedSet<CM> relatedActions = new TreeSet<>();
}