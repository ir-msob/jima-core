package ir.msob.jima.core.commons.child;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseChildDtoAbstract<ID extends Comparable<ID> & Serializable> extends BaseChildAbstract<ID> implements BaseChildDto<ID> {

}
