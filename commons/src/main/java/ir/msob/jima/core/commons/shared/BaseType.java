package ir.msob.jima.core.commons.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.msob.jima.core.commons.Constants;

/**
 * The 'BaseType' interface represents a basic class for types.
 * It extends the 'BaseModel' interface.
 * The interface is annotated with '@JsonTypeInfo', which includes type information in the JSON output.
 * The type information is included as a property with the name specified in 'Constants.TYPE_PROPERTY_NAME'.
 * The interface includes a 'getClassType' method that returns the simple name of the class of the type.
 * The '@JsonTypeInfo' annotation is set to use 'JsonTypeInfo.Id.NONE', which means that no type information will be included in the JSON output.
 * The 'getClassType' method is annotated with '@JsonProperty', which means that the returned value will be included in the JSON output with the property name specified in 'Constants.TYPE_PROPERTY_NAME'.
 */
@JsonIgnoreProperties(value = {Constants.TYPE_PROPERTY_NAME}, allowGetters = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE, property = Constants.TYPE_PROPERTY_NAME)
public interface BaseType extends BaseModel {

    /**
     * Returns the simple name of the class of the type.
     *
     * @return The simple name of the class.
     */
    @JsonProperty(Constants.TYPE_PROPERTY_NAME)
    default String getClassType() {
        return this.getClass().getSimpleName();
    }
}