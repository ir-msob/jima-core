package ir.msob.jima.core.commons.child.relatedobject.relatedprocess;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedProcessContainerAbstract' class provides a basic implementation of the 'BaseRelatedProcessContainer' interface.
 * It is an abstract class where 'RP' is a type parameter that extends 'RelatedProcessAbstract'.
 * This class includes a 'relatedProcesses' field that holds a sorted set of child processes.
 * The class uses the Lombok library to automatically generate getter and setter methods for the 'relatedProcesses' field.
 * It also generates a 'toString' method and a no-argument constructor.
 * The 'toString' method includes a call to the superclass's 'toString' method.
 * The 'relatedProcesses' field is annotated with '@Valid' to enable objectvalidation of the child processes.
 *
 * @param <RP> the type of the child process, which must extend RelatedProcessAbstract.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedProcessContainerAbstract<ID extends Comparable<ID> & Serializable, RP extends RelatedProcessAbstract<ID>> implements BaseRelatedProcessContainer<ID, RP> {
    /**
     * A sorted set of child processes.
     * The set is initialized to an empty 'TreeSet'.
     */
    private SortedSet<RP> relatedProcesses = new TreeSet<>();
}