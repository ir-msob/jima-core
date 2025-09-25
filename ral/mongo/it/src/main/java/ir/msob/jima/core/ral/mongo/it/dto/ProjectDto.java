package ir.msob.jima.core.ral.mongo.it.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.ral.mongo.it.domain.ProjectDomain;

/**
 * @author Yaqub Abdi
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = Constants.TYPE_PROPERTY_NAME)
public interface ProjectDto extends ProjectDomain, BaseDto<String> {

}
