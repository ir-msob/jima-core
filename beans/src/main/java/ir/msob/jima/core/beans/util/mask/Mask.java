package ir.msob.jima.core.beans.util.mask;

import lombok.*;

/**
 * This class represents a mask for moving data from one part of a JSON object to another.
 * It provides fields for the from and to paths for the data movement.
 * It provides methods to get and set these fields.
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Mask {
    /**
     * The path in the JSON object from which to move the data.
     */
    private String from;

    /**
     * The path in the JSON object to which to move the data.
     */
    private String to;
}