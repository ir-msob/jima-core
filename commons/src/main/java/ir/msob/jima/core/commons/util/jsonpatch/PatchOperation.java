package ir.msob.jima.core.commons.util.jsonpatch;

import lombok.*;

/**
 * This class represents a patch operation for JSON objects.
 * It provides fields for the operation type, the path to the target field, and the value to be applied.
 * It provides methods to get and set these fields.
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PatchOperation {
    /**
     * The type of the patch operation.
     */
    private String operation;

    /**
     * The path to the target field in the JSON object.
     */
    private String path;

    /**
     * The value to be applied by the patch operation.
     */
    private Object value;
}