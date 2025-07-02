package ir.msob.jima.core.commons.childdomain.contactmedium;

import java.io.Serializable;
import java.util.SortedSet;

public interface BaseContactMediumContainer<ID extends Comparable<ID> & Serializable, CM extends ContactMediumAbstract<ID>> {
    SortedSet<CM> getContactMediums();

    void setContactMediums(SortedSet<CM> contactMediums);
}
