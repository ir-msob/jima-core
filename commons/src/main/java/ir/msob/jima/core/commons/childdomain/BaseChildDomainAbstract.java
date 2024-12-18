package ir.msob.jima.core.commons.childdomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.element.BaseElementAbstract;
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
public abstract class BaseChildDomainAbstract<ID extends Comparable<ID> & Serializable> extends BaseElementAbstract<ID> implements BaseChildDomain<ID> {
    /**
     * The parent domain ID of the model.
     */
    private ID parentId;

    public BaseChildDomainAbstract(ID id, ID parentId) {
        super(id);
        this.parentId = parentId;
    }

    /**
     * The 'FN' enum represents the field names of the 'BaseChildDomainAbstract' class.
     * It includes a single value 'parentId' which represents the parent domain ID field of the class.
     */
    public enum FN {
        parentId
    }
}
