package ir.msob.jima.core.commons.child.contactmedium;

import ir.msob.jima.core.commons.child.BaseContainer;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedActionContainer' interface represents a base contact mediums with a set of contact mediums.
 * It includes methods to get and set the contact mediums.
 * The contact mediums are represented as a 'SortedSet' of 'RelatedAction' objects.
 * The 'SortedSet' ensures that the contact mediums are sorted in their natural order.
 * The 'getContactMedia' method returns the contact mediums, and the 'setContactMedia' method sets the contact mediums.
 */
public interface BaseContactMediumContainer<ID extends Comparable<ID> & Serializable, CM extends ContactMediumAbstract<ID>> extends BaseContainer {

    /**
     * Gets the contact mediums.
     *
     * @return The contact mediums.
     */
    SortedSet<CM> getContactMediums();

    /**
     * Sets the contactMedia.
     *
     * @param contactMedia The contactMedia to set.
     */
    void setContactMediums(SortedSet<CM> contactMedia);

}