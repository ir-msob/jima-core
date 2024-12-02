package ir.msob.jima.core.commons.related.relatedobject.relatedprocess;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedProcessDtoAbstract' class provides a basic implementation of the 'BaseRelatedProcessDto' interface.
 * It is an abstract class where 'RP' is a type parameter that extends 'RelatedProcessAbstract'.
 * This class includes a 'relatedProcesses' field that holds a sorted set of related processes.
 * The class uses the Lombok library to automatically generate getter and setter methods for the 'relatedProcesses' field.
 * It also generates a 'toString' method and a no-argument constructor.
 * The 'toString' method includes a call to the superclass's 'toString' method.
 * The 'relatedProcesses' field is annotated with '@Valid' to enable objectvalidation of the related processes.
 *
 * @param <RP> the type of the related process, which must extend RelatedProcessAbstract.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedProcessDtoAbstract<ID extends Comparable<ID> & Serializable, RP extends RelatedProcessAbstract<ID>> implements BaseRelatedProcessDto<ID, RP> {
    /**
     * A sorted set of related processes.
     * The set is initialized to an empty 'TreeSet'.
     */
    @Valid
    private SortedSet<RP> relatedProcesses = new TreeSet<>();
}