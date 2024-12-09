package ir.msob.jima.core.commons.child.relatedobject.relatedprocess;

import ir.msob.jima.core.commons.child.relatedobject.BaseRelatedObjectDto;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * The 'BaseRelatedProcessDto' interface defines the basic structure for a child process in the application.
 * It is a generic interface where 'RP' is a type parameter that extends 'RelatedProcessAbstract'.
 * The interface includes methods to get and set a sorted set of child processes.
 *
 * @param <RP> the type of the child process, which must extend RelatedProcessAbstract.
 */
public interface BaseRelatedProcessDto<ID extends Comparable<ID> & Serializable, RP extends RelatedProcessAbstract<ID>> extends BaseRelatedObjectDto<ID> {

    /**
     * Gets the sorted set of child processes.
     *
     * @return the sorted set of child processes.
     */
    SortedSet<RP> getRelatedProcesses();

    /**
     * Sets the sorted set of child processes.
     *
     * @param relatedProcesses the sorted set of child processes to set.
     */
    void setRelatedProcesses(SortedSet<RP> relatedProcesses);

}