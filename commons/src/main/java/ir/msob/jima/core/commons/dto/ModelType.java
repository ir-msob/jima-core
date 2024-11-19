package ir.msob.jima.core.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.msob.jima.core.commons.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The 'ModelType' class is a concrete implementation of the 'BaseModel' and 'BaseType' interfaces.
 * The class includes getter and setter methods.
 * The class also includes a no-argument constructor.
 * The class is annotated with '@JsonInclude(JsonInclude.Include.NON_NULL)', which means that null fields will not be included in the JSON output.
 * The class is also annotated with '@JsonTypeInfo', which includes type information in the JSON output.
 * The type information is included as a property with the name specified in 'Constants.TYPE_PROPERTY_NAME'.
 * The '@JsonTypeInfo' annotation is set to use 'JsonTypeInfo.Id.NONE', which means that no type information will be included in the JSON output.
 */
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE, property = Constants.TYPE_PROPERTY_NAME)
public class ModelType implements BaseType {
}