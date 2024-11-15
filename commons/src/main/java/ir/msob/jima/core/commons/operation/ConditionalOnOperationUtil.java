package ir.msob.jima.core.commons.operation;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The 'ConditionalOnOperationUtil' class provides utility methods for working with 'ConditionalOnOperation' annotations.
 * The class includes a 'hasOperation' method that checks whether a specific operation is allowed for a given class.
 * The class also includes a private constructor to prevent instantiation.
 */
public class ConditionalOnOperationUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private ConditionalOnOperationUtil() {
        // The initial class cannot be instantiated by other classes.
    }

    /**
     * Checks whether a specific operation is allowed for a given class.
     * The method retrieves the 'ConditionalOnOperation' annotation from the class.
     * If the annotation is not present, the method returns true, indicating that the operation is allowed.
     * If the annotation is present, the method checks whether the operation is included in the operations specified in the annotation.
     * The method treats the 'READ' operation as equivalent to the 'READS' operation, and the 'WRITE' operation as equivalent to the 'WRITES' operation.
     *
     * @param operation The operation to check.
     * @param clazz     The class to check.
     * @return True if the operation is allowed for the class, false otherwise.
     */
    public static boolean hasOperation(String operation, Class<?> clazz) {
        ConditionalOnOperation conditionalOnOperation = ConditionalOnOperation.info.getAnnotation(clazz);
        if (conditionalOnOperation == null) {
            // If domain information is not found, the CRUD operation is allowed for the class.
            return true;
        }

        // Convert CRUD operations to a stream and check for a match with the input operation.
        return Arrays.stream(ArrayUtils.addAll(conditionalOnOperation.value(), conditionalOnOperation.operations()))
                .flatMap(co -> {
                    if (Objects.equals(co, Operations.READ)) {
                        return Stream.of(Operations.READS);
                    } else if (Objects.equals(co, Operations.WRITE)) {
                        return Stream.of(Operations.WRITES);
                    } else {
                        return Stream.of(co);
                    }
                })
                .anyMatch(operationType -> operationType.equals(operation));
    }
}