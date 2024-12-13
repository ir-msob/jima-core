package ir.msob.jima.core.commons.dto;

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
public abstract class BaseDtoAbstract<ID extends Comparable<ID> & Serializable> extends BaseDomainAbstract<ID> implements BaseDto<ID> {

    public BaseDtoAbstract(ID id) {
        super(id);
    }
}