package ir.msob.jima.core.commons.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseDomainAbstract<ID extends Comparable<ID> & Serializable> implements BaseDomain<ID> {
    private ID id;

    @Override
    public ID getDomainId() {
        return id;
    }

    @Override
    public void setDomainId(ID id) {
        this.id = id;
    }

    @Override
    public String getDomainIdName() {
        return FN.id.name();
    }

    public enum FN {
        id
    }
}
