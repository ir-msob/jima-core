package ir.msob.jima.core.commons.child.relatedobject.relatedintegration;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedIntegrationContainerAbstract' class provides a basic implementation of the 'BaseRelatedIntegrationContainer' interface.
 * It is an abstract class where 'RI' is a type parameter that extends 'RelatedIntegrationAbstract'.
 * This class includes a 'relatedIntegrations' field that holds a sorted set of child integrations.
 * The class uses the Lombok library to automatically generate getter and setter methods for the 'relatedIntegrations' field.
 * It also generates a 'toString' method and a no-argument constructor.
 * The 'toString' method includes a call to the superclass's 'toString' method.
 * The 'relatedIntegrations' field is annotated with '@Valid' to enable child integration of the child integrations.
 *
 * @param <RI> the type of the child integration, which must extend RelatedIntegrationAbstract.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedIntegrationContainerAbstract<ID extends Comparable<ID> & Serializable, RI extends RelatedIntegrationAbstract<ID>> implements BaseRelatedIntegrationContainer<ID, RI> {
    /**
     * A sorted set of child integrations.
     * The set is initialized to an empty 'TreeSet'.
     */
    private SortedSet<@Valid RI> relatedIntegrations = new TreeSet<>();
}