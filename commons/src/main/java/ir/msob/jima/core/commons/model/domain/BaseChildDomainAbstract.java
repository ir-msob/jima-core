package ir.msob.jima.core.commons.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'BaseChildDomainAbstract' class is an abstract implementation of the 'BaseChildDomain' interface.
 * It extends the 'BaseDomainAbstract' class with a generic type 'BaseChildDomainAbstract<ID>'.
 * This means that the child domain models can be compared based on their IDs.
 * The class is a generic class, with the generic type 'ID' extending 'Comparable' and 'Serializable'.
 * This means that the ID of the child domain model can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the parent domain ID.
 * It also includes a nested enum 'FN' which represents the field names of the class.
 *
 * @param <ID> the type of the identifier for the domain model. It must be comparable and serializable.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class BaseChildDomainAbstract<ID extends Comparable<ID> & Serializable> extends BaseDomainAbstract<ID> implements BaseChildDomain<ID>{

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