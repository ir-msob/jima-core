package ir.msob.jima.core.commons.model.operation;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class is used for interacting with CRUD operations on a given domain class.
 */
public class ConditionalOnOperationUtil {

    private ConditionalOnOperationUtil() {
        // The initial class cannot be instantiated by other classes.
    }

    /**
     * This method checks whether a specific CRUD operation is allowed for a given class.
     *
     * @param operation The desired CRUD operation.
     * @param clazz     The target class.
     * @return true if the specified class allows the CRUD operation, false otherwise.
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
