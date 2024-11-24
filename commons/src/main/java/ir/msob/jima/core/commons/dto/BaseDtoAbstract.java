package ir.msob.jima.core.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseIdModelAbstract;
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
public abstract class BaseDtoAbstract<ID extends Comparable<ID> & Serializable> extends BaseIdModelAbstract<ID> implements BaseDto<ID> {
    @Override
    public ID getDomainId() {
        return getId();
    }

    @Override
    public void setDomainId(ID domainId) {
        setId(domainId);
    }

    @Override
    public String getDomainIdName() {
        return BaseIdModelAbstract.FN.id.name();
    }
}