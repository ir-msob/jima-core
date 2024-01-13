package ir.msob.jima.core.commons.exception.validation;

import ir.msob.jima.core.commons.model.BaseModel;
import lombok.*;

import javax.validation.Path;
import java.io.Serializable;

@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class InvalidData implements BaseModel {
    private String message;
    private Path propertyPath;
    private Serializable invalidValue;
}
