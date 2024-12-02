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
public abstract class BaseChildDtoAbstract<ID extends Comparable<ID> & Serializable> extends BaseIdModelAbstract<ID> implements BaseChildDto<ID> {
    private ID parentId;

    @Override
    public ID getParentId() {
        return this.parentId;
    }

    @Override
    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }

    /**
     * The 'FN' enum represents the field names of the model.
     */
    public enum FN {
        parentId
    }
}