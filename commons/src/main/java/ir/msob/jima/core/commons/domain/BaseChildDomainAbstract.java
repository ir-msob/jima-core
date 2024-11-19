package ir.msob.jima.core.commons.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The {@code BaseChildDomainAbstract} class is an abstract implementation of the {@code BaseChildDomain} interface.
 * It extends the {@code BaseDomainAbstract} class with a generic type {@code BaseChildDomainAbstract<ID>},
 * enabling child domain models to be compared based on their IDs.
 * <p>
 * This class is generic, with the generic type {@code ID} extending {@code Comparable} and {@code Serializable}.
 * It means that the ID of the child domain model can be of any type that is comparable and serializable.
 * <p>
 * The class includes getter and setter methods for the parent domain ID.
 * Additionally, it contains a nested enum {@code FN} which represents the field names of the class.
 *
 * @param <ID> the type of the identifier for the domain model. It must be comparable and serializable.
 */

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class BaseChildDomainAbstract<ID extends Comparable<ID> & Serializable> extends BaseDomainAbstract<ID> implements BaseChildDomain<ID> {

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