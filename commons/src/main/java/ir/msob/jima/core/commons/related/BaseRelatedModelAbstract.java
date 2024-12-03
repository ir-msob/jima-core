package ir.msob.jima.core.commons.related;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.dto.BaseChildDtoAbstract;
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
public abstract class BaseRelatedModelAbstract<ID extends Comparable<ID> & Serializable> extends BaseChildDtoAbstract<ID> implements BaseRelatedModel<ID> {
}
