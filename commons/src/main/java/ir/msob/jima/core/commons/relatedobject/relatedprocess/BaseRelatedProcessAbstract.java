package ir.msob.jima.core.commons.relatedobject.relatedprocess;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedProcessAbstract' class provides a basic implementation of the 'BaseRelatedProcess' interface.
 * It is an abstract class where 'RP' is a type parameter that extends 'RelatedProcess'.
 * This class includes a 'relatedProcesses' field that holds a sorted set of related processes.
 * The class uses the Lombok library to automatically generate getter and setter methods for the 'relatedProcesses' field.
 * It also generates a 'toString' method and a no-argument constructor.
 * The 'toString' method includes a call to the superclass's 'toString' method.
 * The 'relatedProcesses' field is annotated with '@Valid' to enable validation of the related processes.
 *
 * @param <RP> the type of the related process, which must extend RelatedProcess.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedProcessAbstract<RP extends RelatedProcess> implements BaseRelatedProcess<RP> {
    /**
     * A sorted set of related processes.
     * The set is initialized to an empty 'TreeSet'.
     */
    @Valid
    private SortedSet<RP> relatedProcesses = new TreeSet<>();
}