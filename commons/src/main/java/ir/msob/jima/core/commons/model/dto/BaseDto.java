package ir.msob.jima.core.commons.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.model.domain.BaseDomain;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = Constants.TYPE_PROPERTY_NAME)
public interface BaseDto<ID extends Comparable<ID> & Serializable> extends BaseDomain<ID> {

    @JsonProperty(Constants.TYPE_PROPERTY_NAME)
    default String getClassType() {
        return this.getClass().getSimpleName();
    }
}
