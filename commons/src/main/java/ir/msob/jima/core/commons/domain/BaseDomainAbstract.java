package ir.msob.jima.core.commons.domain;

import ir.msob.jima.core.commons.element.BaseElementAbstract;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The 'BaseDomainAbstract' class is an abstract class that provides a basic implementation for domain models.
 * It implements the 'BaseDomain' interface with a generic type 'ID' that extends 'Comparable' and 'Serializable'.
 * This means that the ID of the domain model can be of any type that is comparable and serializable.
 * The class includes getter and setter methods for the domain ID and for the domain ID field name.
 * The class also includes a no-argument constructor.
 * The class includes an inner enum 'FN' which represents the field names of the domain model.
 *
 * @param <ID> The type of the ID of the domain model.
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class BaseDomainAbstract<ID extends Comparable<ID> & Serializable> extends BaseElementAbstract<ID> implements BaseDomain<ID> {

}