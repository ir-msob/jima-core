package ir.msob.jima.core.commons.relatedobject.relatedintegration;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseRelatedIntegrationAbstract' class provides a basic implementation of the 'BaseRelatedIntegration' interface.
 * It is an abstract class where 'RI' is a type parameter that extends 'RelatedIntegration'.
 * This class includes a 'relatedIntegrations' field that holds a sorted set of related integrations.
 * The class uses the Lombok library to automatically generate getter and setter methods for the 'relatedIntegrations' field.
 * It also generates a 'toString' method and a no-argument constructor.
 * The 'toString' method includes a call to the superclass's 'toString' method.
 * The 'relatedIntegrations' field is annotated with '@Valid' to enable validation of the related integrations.
 *
 * @param <RI> the type of the related integration, which must extend RelatedIntegration.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseRelatedIntegrationAbstract<RI extends RelatedIntegration> implements BaseRelatedIntegration<RI> {
    /**
     * A sorted set of related integrations.
     * The set is initialized to an empty 'TreeSet'.
     */
    @Valid
    private SortedSet<RI> relatedIntegrations = new TreeSet<>();
}