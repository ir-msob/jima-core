package ir.msob.jima.core.commons.related.relatedaction;

import ir.msob.jima.core.commons.related.BaseRelatedDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedActionDto' interface represents a base related actions with a set of related actions.
 * It includes methods to get and set the related actions.
 * The related actions are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the related actions are sorted in their natural order.
 * The 'getRelatedAction' method returns the related actions, and the 'setRelatedAction' method sets the related actions.
 */
public interface BaseRelatedActionDto<ID extends Comparable<ID> & Serializable, CM extends RelatedActionAbstract<ID>>
        extends BaseRelatedDto<ID> {

    /**
     * Gets the related actions.
     *
     * @return The related actions.
     */
    SortedSet<CM> getRelatedActions();

    /**
     * Sets the relatedAction.
     *
     * @param relatedAction The relatedAction to set.
     */
    void setRelatedActions(SortedSet<CM> relatedAction);

}