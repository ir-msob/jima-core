package ir.msob.jima.core.commons.child.relatedaction;

import ir.msob.jima.core.commons.child.BaseContainer;
import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedActionContainer' interface represents a base child actions with a set of child actions.
 * It includes methods to get and set the child actions.
 * The child actions are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the child actions are sorted in their natural order.
 * The 'getRelatedAction' method returns the child actions, and the 'setRelatedAction' method sets the child actions.
 */
public interface BaseRelatedActionContainer<ID extends Comparable<ID> & Serializable, CM extends RelatedActionAbstract<ID>> extends BaseContainer {

    /**
     * Gets the child actions.
     *
     * @return The child actions.
     */
    SortedSet<CM> getRelatedActions();

    /**
     * Sets the relatedAction.
     *
     * @param relatedAction The relatedAction to set.
     */
    void setRelatedActions(SortedSet<CM> relatedAction);

}