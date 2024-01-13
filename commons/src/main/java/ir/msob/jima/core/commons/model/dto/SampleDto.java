package ir.msob.jima.core.commons.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.domain.SampleDomain;
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
public class SampleDto<ID extends Comparable<ID> & Serializable> extends SampleDomain<ID> implements BaseDto<ID> {
}
