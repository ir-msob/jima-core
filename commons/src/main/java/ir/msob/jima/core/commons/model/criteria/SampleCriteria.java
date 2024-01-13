package ir.msob.jima.core.commons.model.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SampleCriteria<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> {
}
