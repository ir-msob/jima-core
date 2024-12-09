package ir.msob.jima.core.commons.child;

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
public abstract class BaseChildAbstract<ID extends Comparable<ID> & Serializable> extends BaseElementAbstract<ID> implements BaseChild<ID> {
    /**
     * The parent domain ID of the model.
     */
    private ID parentId;

    /**
     * The 'FN' enum represents the field names of the 'BaseChildDomainAbstract' class.
     * It includes a single value 'parentId' which represents the parent domain ID field of the class.
     */
    public enum FN {
        parentId
    }
}
