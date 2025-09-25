package ir.msob.jima.core.ral.mongo.it.criteria;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.domain.BaseCriteria;

/**
 * Basic class for domains filter
 *
 * @author Yaqub Abdi
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = Constants.TYPE_PROPERTY_NAME)
public interface ProjectCriteria extends BaseCriteria<String> {

}
