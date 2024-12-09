package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code CrudProperties} class holds configuration properties for managing
 * domain-specific settings in a CRUD (Create, Read, Update, Delete) context.
 * <p>
 * This class encapsulates a collection of {@link Domain} objects, each representing
 * a specific domain with its associated elements and operations. It is designed to
 * facilitate the configuration of various operational parameters for different domains
 * within the application.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CrudProperties {
    private Collection<Domain> domains = new ArrayList<>();

    /**
     * The {@code Element} class represents a configurable element within a domain.
     * <p>
     * Each element has a name and a collection of operations that can be performed
     * on it. This class serves as a base for defining specific elements within
     * a domain's operational context.
     * </p>
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Element {
        private String name;
        private Collection<String> operations = new ArrayList<>();
    }

    /**
     * The {@code Domain} class extends {@link Element} to represent a specific
     * domain that can contain multiple elements.
     * <p>
     * Each domain can have its own collection of elements, allowing for a
     * hierarchical structure of operations and configurations within the
     * application.
     * </p>
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Domain extends Element {
        private Collection<Element> elements = new ArrayList<>();
    }
}
