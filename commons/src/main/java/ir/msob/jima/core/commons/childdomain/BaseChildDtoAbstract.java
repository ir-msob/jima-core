package ir.msob.jima.core.commons.childdomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseDomainAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseChildDtoAbstract<ID extends Comparable<ID> & Serializable> extends BaseChildDomainAbstract<ID> implements BaseChildDto<ID> {

    public BaseChildDtoAbstract(ID id, ID parentId) {
        super(id, parentId);
    }
}