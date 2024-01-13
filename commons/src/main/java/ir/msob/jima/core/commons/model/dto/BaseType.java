package ir.msob.jima.core.commons.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.model.BaseModel;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE, property = Constants.TYPE_PROPERTY_NAME)
public interface BaseType extends BaseModel {

    @JsonProperty(Constants.TYPE_PROPERTY_NAME)
    default String getClassType() {
        return this.getClass().getSimpleName();
    }
}
