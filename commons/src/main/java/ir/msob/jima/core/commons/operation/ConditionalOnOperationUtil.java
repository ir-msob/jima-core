package ir.msob.jima.core.commons.operation;

import ir.msob.jima.core.commons.properties.CrudProperties;
import ir.msob.jima.core.commons.resource.Resource;
import ir.msob.jima.core.commons.scope.Scope;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The {@code ConditionalOnOperationUtil} class provides utility methods for handling
 * {@code ConditionalOnOperation} annotations. It includes functionality to determine
 * if a specific operation is permitted for a given class based on its associated
 * scope and CRUD properties.
 * <p>
 * This utility class is designed to facilitate the evaluation of operations
 * in the context of defined scopes and CRUD properties, ensuring that
 * operations are only permitted when appropriate conditions are met.
 * </p>
 */
public class ConditionalOnOperationUtil {

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This constructor is private to prevent the instantiation of this utility class,
     * as it is intended to be used statically.
     * </p>
     */
    private ConditionalOnOperationUtil() {
        // Prevent instantiation of utility class
    }

    /**
     * Checks if a specific operation is permitted for a given class based on the
     * provided scope and CRUD properties.
     *
     * @param scope          The scope to evaluate against.
     * @param crudProperties The CRUD properties containing domain and element information.
     * @param clazz          The class to evaluate.
     * @return {@code true} if the operation is permitted for the class; {@code false} otherwise.
     */
    public static boolean hasOperation(Scope scope, CrudProperties crudProperties, Class<?> clazz) {
        Resource resource = Resource.info.getAnnotation(clazz);
        if (resource == null) {
            return true;
        }
        ConditionalOnOperation conditionalOnOperation = ConditionalOnOperation.info.getAnnotation(clazz);

        CrudProperties.Domain domain = crudProperties.getDomains().stream()
                .filter(d -> d.getName().equalsIgnoreCase(resource.value()))
                .findFirst()
                .orElse(null);

        // If no domain and no conditional operation, allow the operation
        if (domain == null && conditionalOnOperation == null) {
            return true;
        }

        // Check based on the scope element
        /* FIXME
        if (scope.element().equalsIgnoreCase(Elements.DOMAIN)) {
            return checkDomainOperations(scope, domain, conditionalOnOperation);
        } else {
            return checkRelatedOperations(scope, domain, conditionalOnOperation);
        }
         */
        return true;
    }

    /**
     * Validates domain-specific operations based on the provided scope and
     * conditional operation annotations.
     *
     * @param scope                  The scope to evaluate.
     * @param domain                 The domain containing operation definitions.
     * @param conditionalOnOperation The conditional operation annotation for the class.
     * @return {@code true} if the operation is allowed; {@code false} otherwise.
     */
    private static boolean checkDomainOperations(Scope scope, CrudProperties.Domain domain,
                                                 ConditionalOnOperation conditionalOnOperation) {
        if (domain != null) {
            return domain.getOperations().stream()
                    .flatMap(ConditionalOnOperationUtil::mapOperations)
                    .anyMatch(operationType -> operationType.equalsIgnoreCase(scope.operation()));
        } else if (conditionalOnOperation != null) {
            return Arrays.stream(ArrayUtils.addAll(conditionalOnOperation.operations()))
                    .flatMap(ConditionalOnOperationUtil::mapOperations)
                    .anyMatch(operationType -> operationType.equalsIgnoreCase(scope.operation()));
        }
        return true;
    }

    /**
     * Validates embeddeddomain operations based on the provided scope and
     * conditional operation annotations.
     *
     * @param scope                  The scope to evaluate.
     * @param domain                 The domain containing operation definitions.
     * @param conditionalOnOperation The conditional operation annotation for the class.
     * @return {@code true} if the operation is allowed; {@code false} otherwise.
     */
    private static boolean checkRelatedOperations(Scope scope, CrudProperties.Domain domain,
                                                  ConditionalOnOperation conditionalOnOperation) {
       /* FIXME
        Optional<Element> element = Arrays.stream(conditionalOnOperation.children())
                .filter(e -> e.element().equalsIgnoreCase(scope.element()))
                .findFirst();

        Optional<CrudProperties.Element> elementProperty = Optional.empty();
        if (domain != null) {
            elementProperty = domain.getElements().stream()
                    .filter(e -> e.getName().equalsIgnoreCase(scope.element()))
                    .findFirst();
        }

        if (elementProperty.isPresent()) {
            return elementProperty.get().getOperations().stream()
                    .flatMap(ConditionalOnOperationUtil::mapOperations)
                    .anyMatch(operationType -> operationType.equalsIgnoreCase(scope.operation()));
        } else if (element.isPresent()) {
            return Arrays.stream(ArrayUtils.addAll(element.get().operations()))
                    .flatMap(ConditionalOnOperationUtil::mapOperations)
                    .anyMatch(operationType -> operationType.equalsIgnoreCase(scope.operation()));
        }
        */
        return true;
    }

    /**
     * Maps a given operation to its corresponding operation types, expanding
     * READ and WRITE operations to their respective multiple types.
     *
     * @param operation The operation to map.
     * @return A stream of operation types corresponding to the input operation.
     */
    private static Stream<String> mapOperations(String operation) {
        if (Objects.equals(operation, Operations.READ)) {
            return Operations.READS.stream();
        } else if (Objects.equals(operation, Operations.WRITE)) {
            return Operations.WRITES.stream();
        } else {
            return Stream.of(operation);
        }
    }
}