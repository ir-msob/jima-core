package ir.msob.jima.core.commons.exception.validation;

import ir.msob.jima.core.commons.model.BaseModel;
import lombok.*;

import javax.validation.Path;
import java.io.Serializable;

/**
 * The 'InvalidData' class implements the 'BaseModel' interface and represents a specific type of model that is used to store information about invalid data associated with a validation exception.
 * It includes fields for the message associated with the invalid data, the property path of the invalid data, and the invalid value itself.
 * The class also provides several constructors for creating an instance of the model with different sets of parameters.
 * Additionally, it overrides the 'toString' method from the 'Object' class to return a string representation of the model.
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class InvalidData implements BaseModel {
    /**
     * The message associated with the invalid data.
     */
    private String message;
    /**
     * The property path of the invalid data.
     */
    private Path propertyPath;
    /**
     * The invalid value itself.
     */
    private Serializable invalidValue;
}