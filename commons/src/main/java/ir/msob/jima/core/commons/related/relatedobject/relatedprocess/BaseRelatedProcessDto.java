package ir.msob.jima.core.commons.related.relatedobject.relatedprocess;

import ir.msob.jima.core.commons.related.relatedobject.BaseRelatedObjectDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedProcessDto' interface defines the basic structure for a related process in the application.
 * It is a generic interface where 'RP' is a type parameter that extends 'RelatedProcessAbstract'.
 * The interface includes methods to get and set a sorted set of related processes.
 *
 * @param <RP> the type of the related process, which must extend RelatedProcessAbstract.
 */
public interface BaseRelatedProcessDto<ID extends Comparable<ID> & Serializable, RP extends RelatedProcessAbstract<ID>> extends BaseRelatedObjectDto<ID> {

    /**
     * Gets the sorted set of related processes.
     *
     * @return the sorted set of related processes.
     */
    SortedSet<RP> getRelatedProcesses();

    /**
     * Sets the sorted set of related processes.
     *
     * @param relatedProcesses the sorted set of related processes to set.
     */
    void setRelatedProcesses(SortedSet<RP> relatedProcesses);

}