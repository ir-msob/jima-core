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
 * The 'BaseRelatedActionAbstract' class represents an abstract base object validation with a set of objectValidation.
 * It implements the 'BaseRelatedAction' interface.
 * The class includes a field for the objectValidation and getter and setter methods for this field.
 * The objectValidation are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the objectValidation are sorted in their natural order.
 * The class also includes a no-argument constructor.
 * The 'objectValidation' field is annotated with '@Valid' to enable objectvalidation of the objectValidation.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedActionAbstract<ID extends Comparable<ID> & Serializable, CM extends RelatedAction<ID>> implements BaseRelatedAction<ID, CM> {
    /**
     * The objectValidation of the base object validation.
     */
    @Valid
    private SortedSet<CM> objectValidation = new TreeSet<>();
}